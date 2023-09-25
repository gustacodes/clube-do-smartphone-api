package com.clube.smartphone.services;

import com.clube.smartphone.entities.Cliente;
import com.clube.smartphone.repositories.ClienteRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private ClienteRespository respository;

    public ClienteService(ClienteRespository respository) {
        this.respository = respository;
    }

    public Cliente save(Cliente cliente) {
        return respository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return respository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        var cliente = respository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return cliente;
    }
}
