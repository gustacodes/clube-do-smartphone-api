package com.clube.smartphone.services;

import com.clube.smartphone.controllers.ProdutosController;
import com.clube.smartphone.entities.Produtos;
import com.clube.smartphone.repositories.ProdutosRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProdutosServices {

    private ProdutosRepository repository;

    public ProdutosServices(ProdutosRepository repository) {
        this.repository = repository;
    }

    public Produtos salvar(Produtos produtos) {
        return repository.save(produtos);
    }

    public List<Produtos> listar() {
        return repository.findAll();
    }

    public Produtos buscarPorId(Long id) {
        Produtos produto = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        return produto;
    }

    public List<Produtos> buscarPorModelo(String modelo) {
        List<Produtos> produtos = repository.findAll();
        List<Produtos> produto = new ArrayList<>();

        for (Produtos p : produtos) {
            if (p.getModelo().equalsIgnoreCase(modelo)) {
                long id = p.getId();
                p.add(linkTo(methodOn(ProdutosController.class).buscarPorId(id)).withSelfRel());
                produto.add(p);
            }
        }

        return produto;
    }

    public Produtos compra(String modelo, Integer quantidade) {

        Produtos produto = repository.findBymodelo(modelo);
        long venda = produto.getQuantidade() - quantidade;
        produto.setQuantidade(venda);
        repository.save(produto);

        return produto;
    }

}
