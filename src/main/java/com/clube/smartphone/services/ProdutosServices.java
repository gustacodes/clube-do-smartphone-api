package com.clube.smartphone.services;

import com.clube.smartphone.entities.Produtos;
import com.clube.smartphone.repositories.ProdutosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Produtos> produto = repository.modelo(modelo);
        return produto;
    }

//    public List<Produtos> buscarPorMarca(String marca) {
//        List<Produtos> produto = repository.marca(marca);
//        return produto;
//    }
}
