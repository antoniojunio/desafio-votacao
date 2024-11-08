package com.example.votacao.service;

import com.example.votacao.model.Pauta;
import com.example.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {
    @Autowired
    private PautaRepository pautaRepository;

    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }
}
