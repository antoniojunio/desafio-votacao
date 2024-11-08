package com.example.votacao.repository;

import com.example.votacao.model.Sessao;
import com.example.votacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByAssociadoIdAndSessao(Long associadoId, Sessao sessao);

    Long countBySessao_Pauta_IdAndVoto(Long pautaId, boolean voto);
}
