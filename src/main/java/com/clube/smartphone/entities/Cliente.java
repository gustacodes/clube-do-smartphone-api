package com.clube.smartphone.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Data
public class Cliente extends RepresentationModel<Cliente> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Insira o nome do cliente")
    private String nome;
    @NotBlank(message = "Insira o contato do cliente")
    private String telefone;
    @NotNull(message = "Preencha o endereço")
    @ManyToOne
    private Endereco endereco;

}
