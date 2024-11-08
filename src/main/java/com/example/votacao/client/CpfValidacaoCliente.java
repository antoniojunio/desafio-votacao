package com.example.votacao.client;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CpfValidacaoCliente {
    private final Random random = new Random();

    public String verificarCpf(String cpf) {
        if (isCpfValid(cpf)) {
            return "ABLE_TO_VOTE";
        } else {
            return "UNABLE_TO_VOTE";
        }
    }

    private boolean isCpfValid(String cpf) {
        return random.nextBoolean();
    }

}
