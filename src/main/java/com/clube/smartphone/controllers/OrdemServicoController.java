package com.clube.smartphone.controllers;

import com.clube.smartphone.entities.Cliente;
import com.clube.smartphone.entities.OrdemServico;
import com.clube.smartphone.services.AparelhoService;
import com.clube.smartphone.services.ClienteService;
import com.clube.smartphone.services.OrdemServicoService;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Or;
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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ordens")
public class OrdemServicoController {

    private ClienteService clienteService;
    private AparelhoService aparelhoService;
    private OrdemServicoService ordemServicoService;

    public OrdemServicoController(ClienteService clienteService, AparelhoService aparelhoService, OrdemServicoService ordemServicoService) {
        this.clienteService = clienteService;
        this.aparelhoService = aparelhoService;
        this.ordemServicoService = ordemServicoService;
    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> listar() {

        List<Cliente> clientes = clienteService.listarTodos();

        for (Cliente cliente : clientes) {
            long id = cliente.getId();
            cliente.add(linkTo(methodOn(ClienteController.class).buscarPorId(id)).withSelfRel());
        }

        return ResponseEntity.ok(ordemServicoService.listar());
    }

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody @Valid OrdemServico ordem, BindingResult result) {
        ordem.setCliente(clienteService.buscarPorId(1L));

        if (result.hasErrors() && ordem.getCliente() == null) {

            List<String> errors = new ArrayList<>();

            for (FieldError error : result.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }

            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Erro de validação");
            response.put("errors", errors);

            return ResponseEntity.badRequest().body(response);
        }

        aparelhoService.salvar(ordem.getAparelho());

        return ResponseEntity.status(HttpStatus.CREATED).body(ordemServicoService.salvar(ordem));

    }

    @GetMapping("/cliente/{cpf}")
    public ResponseEntity<List<OrdemServico>> ordemServico(@PathVariable String cpf) {
        List<OrdemServico> ordens = ordemServicoService.ordemCliente(cpf);
        return ResponseEntity.ok().body(ordens);
    }

}
