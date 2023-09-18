package com.clube.smartphone.controllers;

import com.clube.smartphone.entities.Cliente;
import com.clube.smartphone.repositories.EnderecoRepository;
import com.clube.smartphone.services.AparelhoService;
import com.clube.smartphone.services.ClienteService;
import com.clube.smartphone.services.EnderecoSerivce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> salvar(@RequestBody Cliente cliente, BindingResult result) {
        serviceEndereco.save(cliente.getEndereco());
        serviceAparelho.save(cliente.getAparelho());
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceCliente.save(cliente));
    }

}
