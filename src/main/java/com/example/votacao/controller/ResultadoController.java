package com.example.votacao.controller;

import com.example.votacao.dto.ResultadoDTO;
import com.example.votacao.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resultados")
public class ResultadoController {

    @Autowired
    private VotoService votoService;

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoDTO> obterResultado(@PathVariable Long id) {
        ResultadoDTO resultadoDTO = votoService.calcularResultado(id);
        return ResponseEntity.ok(resultadoDTO);
    }
}
