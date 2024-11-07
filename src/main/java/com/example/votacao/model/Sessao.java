
package com.example.votacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "A pauta é obrigatória para criar uma sessão.")
    private Pauta pauta;
    private LocalDateTime dataInicio;

    @Future(message = "A data de término deve estar no futuro.")
    private LocalDateTime dataFim;
    private boolean ativa;

    @PrePersist
    private void definirDataFimPadrao() {
        if (this.dataInicio == null) {
            this.dataInicio = LocalDateTime.now();
        }
        if (this.dataFim == null) {
            this.dataFim = this.dataInicio.plusMinutes(1);
        }
        this.ativa = true;
    }

    public boolean isAtiva() {
        return ativa && LocalDateTime.now().isBefore(dataFim);
    }

    public void encerrarSessao() {
        this.ativa = false;
    }
}
