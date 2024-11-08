package com.example.votacao.controller;

import com.example.votacao.dto.VotoDTO;
import com.example.votacao.model.Voto;
import com.example.votacao.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votos")
public class VotoController {
    @Autowired
    private VotoService votoService;

    @PostMapping
    public ResponseEntity<Voto> registrarVoto(@RequestBody VotoDTO votoDTO) {
        Voto novoVoto = votoService.registrarVoto(
                votoDTO.getAssociadoId(),
                votoDTO.getSessaoId(),
                votoDTO.isVoto(),
                votoDTO.getCpf()
        );
        return ResponseEntity.ok(novoVoto);
    }
}
