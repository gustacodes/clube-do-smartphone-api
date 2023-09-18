package com.clube.smartphone.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Preencha todos os dados do cliente")
    private Cliente cliente;
    @NotBlank(message = "Preencha todos os dados do aparelho")
    private Aparelho aparelho;
    @NotBlank(message = "Insira o problema do aparelho")
    private String problemaRelatado;
    @NotBlank
    private LocalDateTime data;
    @NotBlank
    private Status status;


}
