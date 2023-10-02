package com.clube.smartphone.controllers;

import com.clube.smartphone.entities.Produtos;
import com.clube.smartphone.services.ProdutosServices;
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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/produtos")
public class ProdutosController {

    private ProdutosServices service;

    public ProdutosController(ProdutosServices service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Produtos>> listar() {

        List<Produtos> produtos = service.listar();

        for (Produtos produto : produtos) {
            long id = produto.getId();
            produto.add(linkTo(methodOn(ProdutosController.class).buscarPorId(id)).withSelfRel());
        }
        return ResponseEntity.ok().body(service.listar());
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid Produtos produto, BindingResult result) {

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
            response.put("message", "Produto cadastrado com sucesso");

            produto.add(linkTo(methodOn(ProdutosController.class).listar()).withRel("Todos produtos"));
            return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(produto));

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtos> buscarPorId(@PathVariable Long id) {

        Produtos produto = service.buscarPorId(id);
        produto.add(linkTo(methodOn(ProdutosController.class).listar()).withRel("Lista de produtos"));
        return ResponseEntity.ok().body(produto);

    }

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<List<Produtos>> buscarPorModelo(@PathVariable String modelo) {

        List<Produtos> produto = service.buscarPorModelo(modelo);
        return ResponseEntity.ok().body(produto);

    }

    @PutMapping("/comprar/{modelo}/{quantidade}")
    public ResponseEntity<Produtos> compra(@PathVariable String modelo, @PathVariable Integer quantidade) {

        Produtos produto = service.compra(modelo, quantidade);
        return ResponseEntity.ok().body(produto);

    }

}
