package com.example.votacao.controller;

import com.example.votacao.model.Sessao;
import com.example.votacao.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;

    /**
     * Abre uma nova sessão de votação para a pauta especificada.
     *
     * @param pautaId ID da pauta para a qual a sessão será aberta.
     * @param duracaoMinutos Duração opcional para a sessão em minutos. Se não for fornecida, será usada a duração padrão de 1 minuto.
     * @return ResponseEntity contendo a sessão criada.
     */
    @PostMapping
    public ResponseEntity<?> abrirSessao(
            @RequestParam Long pautaId,
            @RequestParam(required = false) Long duracaoMinutos) {
        try {
            Duration duracao = (duracaoMinutos != null) ? Duration.ofMinutes(duracaoMinutos) : Duration.ofMinutes(1);
            Sessao sessao = sessaoService.abrirSessao(pautaId, duracao);
            return ResponseEntity.ok(sessao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao abrir a sessão.");
        }
    }
}