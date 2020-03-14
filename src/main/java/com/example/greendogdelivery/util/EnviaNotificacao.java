package com.example.greendogdelivery.util;

import com.example.greendogdelivery.domain.Cliente;
import com.example.greendogdelivery.domain.Pedido;
import com.example.greendogdelivery.dto.Notificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnviaNotificacao {

    @Autowired
    private Notificacao notificacao;

    public void enviaEmail(Cliente cliente, Pedido pedido) {
        if (notificacao.envioAtivo()) {
            System.out.println("Notificacao enviada!");
        } else {
            System.out.println("Notificacao desligada!");
        }
    }

}
