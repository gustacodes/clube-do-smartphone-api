package com.clube.smartphone.repositories;

import com.clube.smartphone.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

}
