package com.example.votacao.controller;

import com.example.votacao.dto.VotoDTO;
import com.example.votacao.model.Voto;
import com.example.votacao.service.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votos")
@Tag(name = "Votos", description = "Endpoints para gerenciar votos")
public class VotoController {
    @Autowired
    private VotoService votoService;

    @PostMapping
    @Operation(summary = "Registrar um novo voto", description = "Registra o voto de um associado em uma pauta.")
    public ResponseEntity<Voto> registrarVoto(@RequestBody VotoDTO votoDTO) {
        System.out.println("Registrando voto: " + votoDTO);
        Voto novoVoto = votoService.registrarVoto(
                votoDTO.getAssociadoId(),
                votoDTO.getSessaoId(),
                votoDTO.isVoto(),
                votoDTO.getCpf()
        );
        return ResponseEntity.ok(novoVoto);
    }
}
