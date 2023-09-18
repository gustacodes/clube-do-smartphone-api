package com.clube.smartphone.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Insira o nome do cliente")
    private String nome;
    @NotBlank(message = "Insira o contato do cliente")
    private String telefone;
    @NotEmpty(message = "Insira o endereço")
    @ManyToOne
    private Endereco endereco;
    @NotEmpty(message = "Insira os dados do aparelho")
    @ManyToOne
    private Aparelho aparelho;

}
