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

    public Cliente salvar(Cliente cliente) {
        validaCliente(cliente);
        return respository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return respository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        var cliente = respository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return cliente;
    }

    public void validaCliente(Cliente cliente) {

        if (respository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado no sistema");
        } else if (respository.existsByTelefone(cliente.getTelefone())) {
            throw new RuntimeException("Telefone já cadastrado no sistema");
        }
    }

    public Cliente cpf(String cpf) {
        return respository.cpf(cpf);
    }

}
