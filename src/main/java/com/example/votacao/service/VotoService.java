package com.example.votacao.service;

import com.example.votacao.client.CpfValidacaoCliente;
import com.example.votacao.dto.ResultadoDTO;
import com.example.votacao.dto.ResultadoVotacaoDTO;
import com.example.votacao.exception.CustomException;
import com.example.votacao.model.Sessao;
import com.example.votacao.model.Voto;
import com.example.votacao.repository.SessaoRepository;
import com.example.votacao.repository.VotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotoService {

    private static final Logger logger = LoggerFactory.getLogger(VotoService.class);

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private CpfValidacaoCliente cpfValidationClient;

    public Voto registrarVoto(Long associadoId, Long sessaoId, boolean votoValue, String cpf) {
        String status = cpfValidationClient.verificarCpf(cpf);
        if ("UNABLE_TO_VOTE".equals(status)) {
            throw new CustomException("O associado não está apto para votar");
        }

        Optional<Sessao> sessaoOptional = sessaoRepository.findById(sessaoId);
        if (sessaoOptional.isEmpty()) {
            throw new IllegalArgumentException("Sessão não encontrada com ID: " + sessaoId);
        }

        Sessao sessao = sessaoOptional.get();

        boolean jaVotou = votoRepository.existsByAssociadoIdAndSessao(associadoId, sessao);
        if (jaVotou) {
            throw new IllegalArgumentException("Associado já votou nesta sessão.");
        }

        Voto voto = new Voto();
        voto.setAssociadoId(associadoId);
        voto.setSessao(sessao);
        voto.setVoto(votoValue);

        logger.info("Voto registrado para associado {} na sessão {}", associadoId, sessaoId);
        return votoRepository.save(voto);
    }

    public ResultadoDTO calcularResultado(Long pautaId) {
        Long votosSim = votoRepository.countBySessao_Pauta_IdAndVoto(pautaId, true);
        Long votosNao = votoRepository.countBySessao_Pauta_IdAndVoto(pautaId, false);

        logger.info("Resultado calculado para a pauta {} - Votos Sim: {}, Votos Não: {}", pautaId, votosSim, votosNao);
        return new ResultadoDTO(pautaId, votosSim, votosNao);
    }

    public ResultadoVotacaoDTO contabilizarVotos(Long pautaId) {
        logger.info("Contabilizando votos para a pauta: {}", pautaId);

        long votosSim = votoRepository.countBySessao_Pauta_IdAndVoto(pautaId, true);
        long votosNao = votoRepository.countBySessao_Pauta_IdAndVoto(pautaId, false);

        logger.info("Contabilização concluída para a pauta {} - Votos Sim: {}, Votos Não: {}", pautaId, votosSim, votosNao);

        return new ResultadoVotacaoDTO(votosSim, votosNao);
    }
}
