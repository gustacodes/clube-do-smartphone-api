package com.clube.smartphone.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Entity
@Data
public class Produtos extends RepresentationModel<Produtos> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Informe a marca")
    private String marca;
    @NotNull(message = "Informe o modelo")
    private String modelo;
    @NotNull(message = "Insira a cor do produto")
    private String cor;
    @NotNull(message = "Defina uma quantidade")
    @Size(min = 1)
    private long quantidade;

}
