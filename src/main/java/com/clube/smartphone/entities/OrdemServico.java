package com.clube.smartphone.entities;

import com.clube.smartphone.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Preencha todos os dados do cliente")
    @ManyToOne
    private Cliente cliente;
    @NotBlank(message = "Insira o problema do aparelho")
    private String problemaRelatado;
    @NotNull
    private LocalDateTime data;
    @NotBlank
    private Status status;

}
