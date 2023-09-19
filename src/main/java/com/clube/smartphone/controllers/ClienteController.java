package com.clube.smartphone.controllers;

import com.clube.smartphone.entities.Cliente;
import com.clube.smartphone.repositories.EnderecoRepository;
import com.clube.smartphone.services.AparelhoService;
import com.clube.smartphone.services.ClienteService;
import com.clube.smartphone.services.EnderecoSerivce;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService serviceCliente;
    private EnderecoSerivce serviceEndereco;
    private AparelhoService serviceAparelho;

    public ClienteController(ClienteService serviceCliente, EnderecoSerivce enderecoSerivce, AparelhoService aparelhoService) {
        this.serviceCliente = serviceCliente;
        this.serviceEndereco = enderecoSerivce;
        this.serviceAparelho = aparelhoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok().body(serviceCliente.listarTodos());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> salvar(@RequestBody @Valid Cliente cliente, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }

            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Erro de validação");
            response.put("errors", errors);

            return ResponseEntity.badRequest().body(response);

        } else {

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cliente cadastrado com sucesso");

            serviceEndereco.save(cliente.getEndereco());
            serviceAparelho.save(cliente.getAparelho());
            serviceCliente.save(cliente);

            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);

        }

    }

}
