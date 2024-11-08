package com.example.votacao.client;

import com.example.votacao.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CpfValidacaoCliente {
    private final Random random = new Random();

    public String verificarCpf(String cpf) {
        int randomValue = random.nextInt(3);

        if (randomValue == 0) {
            throw new NotFoundException("CPF inválido");
        }

        return (randomValue == 1) ? "ABLE_TO_VOTE" : "UNABLE_TO_VOTE";
    }
}
