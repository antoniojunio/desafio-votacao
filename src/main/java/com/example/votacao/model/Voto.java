
package com.example.votacao.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long associadoId;
    private boolean voto;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @ManyToOne
    private Sessao sessao;
}
