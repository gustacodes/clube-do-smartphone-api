package com.clube.smartphone.repositories;

import com.clube.smartphone.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRespository extends JpaRepository<Cliente, Long> {

}
