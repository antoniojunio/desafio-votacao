package com.example.votacao.dto;

import lombok.Data;

@Data
public class VotoDTO {
    private Long associadoId;
    private Long sessaoId;
    private boolean voto;
    private String cpf;
}
