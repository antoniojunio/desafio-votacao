
package com.example.votacao.service;

import com.example.votacao.model.Pauta;
import com.example.votacao.model.Sessao;
import com.example.votacao.repository.PautaRepository;
import com.example.votacao.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SessaoService {
    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    PautaRepository pautaRepository;

    public Sessao abrirSessao(Long pautaId, Duration duracao) {
        Optional<Pauta> pautaOptional = pautaRepository.findById(pautaId);
        if (!pautaOptional.isPresent()) {
            throw new IllegalArgumentException("Pauta não encontrada com ID: " + pautaId);
        }
        Pauta pauta = pautaOptional.get();
        Duration duracaoSessao = (duracao != null) ? duracao : Duration.ofMinutes(1);
        Sessao sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setDataInicio(LocalDateTime.now());
        sessao.setDataFim(LocalDateTime.now().plus(duracaoSessao));
        sessao.setAtiva(true);
        return sessaoRepository.save(sessao);
    }

    public boolean isSessaoAtiva(Long sessaoId) {
        Sessao sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada com ID: " + sessaoId));
        return sessao.isAtiva();
    }
}
