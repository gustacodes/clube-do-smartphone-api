package com.clube.smartphone.controllers;

import com.clube.smartphone.entities.Cliente;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

        List<Cliente> cliente = serviceCliente.listarTodos();

        for (Cliente c : cliente) {
            long id = c.getId();
            c.add(linkTo(methodOn(ClienteController.class).buscarPorId(id)).withSelfRel());
        }

        return ResponseEntity.ok().body(serviceCliente.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        var cliente = serviceCliente.buscarPorId(id);
        cliente.add(linkTo(methodOn(ClienteController.class).listar()).withRel("Todos clientes"));
        return ResponseEntity.ok(cliente);
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
            serviceCliente.salvar(cliente);

            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);

        }

    }

}
