package com.example.votacao.dto;

import lombok.Data;

@Data
public class ResultadoDTO {
    private Long pautaId;
    private int votosSim;
    private int votosNao;
    private String resultado;

    public ResultadoDTO(Long pautaId, int votosSim, int votosNao, String resultado) {
        this.pautaId = pautaId;
        this.votosSim = votosSim;
        this.votosNao = votosNao;
        this.resultado = resultado;
    }

    public ResultadoDTO(Long pautaId, Long votosSim, Long votosNao) {
        this.pautaId = pautaId;
        this.votosSim = votosSim.intValue();
        this.votosNao = votosNao.intValue();
        this.resultado = calcularResultado(votosSim, votosNao);
    }

    private String calcularResultado(Long votosSim, Long votosNao) {
        if (votosSim > votosNao) {
            return "Aprovado";
        } else if (votosSim < votosNao) {
            return "Reprovado";
        } else {
            return "Empate";
        }
    }
}
