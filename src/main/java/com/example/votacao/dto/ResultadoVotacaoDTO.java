package com.example.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultadoVotacaoDTO {
    private long votosSim;
    private long votosNao;
}
