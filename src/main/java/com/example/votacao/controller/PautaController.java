package com.example.votacao.controller;

import com.example.votacao.dto.ResultadoVotacaoDTO;
import com.example.votacao.model.Pauta;
import com.example.votacao.service.PautaService;
import com.example.votacao.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pautas")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @Autowired
    private VotoService votoService;

    @PostMapping
    public ResponseEntity<Pauta> criarPauta(@RequestBody Pauta pauta) {
        return ResponseEntity.ok(pautaService.criarPauta(pauta));
    }

    @GetMapping("/{pautaId}/resultado")
    public ResponseEntity<ResultadoVotacaoDTO> obterResultadoVotacao(@PathVariable Long pautaId) {
        ResultadoVotacaoDTO resultado = votoService.contabilizarVotos(pautaId);
        return ResponseEntity.ok(resultado);
    }
}
