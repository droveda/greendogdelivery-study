package com.example.greendogdelivery.rest;

import com.example.greendogdelivery.domain.Cliente;
import com.example.greendogdelivery.domain.Item;
import com.example.greendogdelivery.domain.Pedido;
import com.example.greendogdelivery.dto.RespostaDTO;
import com.example.greendogdelivery.repository.ClienteRepository;
import com.example.greendogdelivery.repository.ItemRepository;
import com.example.greendogdelivery.util.EnviaNotificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class NovoPedidoController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private EnviaNotificacao enviaNotificacao;

    @GetMapping("/rest/pedido/novo/{clienteId}/{listaDeItens}")
    public RespostaDTO novo(@PathVariable("clienteId") Long clienteId,
                            @PathVariable("listaDeItens") String listaDeItens) {

        RespostaDTO respostaDTO = new RespostaDTO();

        try {
            Cliente c = clienteRepository.findOne(clienteId);
            String[] listaDeItensID = listaDeItens.split(",");

            Pedido pedido = new Pedido();
            double valorTotal = 0;
            List<Item> itensPeddo = new ArrayList<>();

            for (String itemId : listaDeItensID) {
                Item item = itemRepository.findOne(Long.parseLong(itemId));
                itensPeddo.add(item);
                valorTotal += item.getPreco();
            }

            pedido.setItens(itensPeddo);
            pedido.setValorTotal(valorTotal);
            pedido.setData(new Date());
            pedido.setCliente(c);
            c.getPedidos().add(pedido);

            clienteRepository.saveAndFlush(c);
            enviaNotificacaoEmail(c, pedido);

            List<Long> pedidosID = new ArrayList<>();
            for (Pedido p : c.getPedidos()) {
                pedidosID.add(p.getId());
            }
            Long ultimoPedido = Collections.max(pedidosID);
            respostaDTO.setPedido(ultimoPedido);
            respostaDTO.setValorTotal(valorTotal);
            respostaDTO.setMensagem("Pedido efetuado com sucesso!");
        } catch (Exception ex) {
            respostaDTO.setMensagem("Erro: " + ex.getMessage());
        }

        return respostaDTO;

    }

    private void enviaNotificacaoEmail(Cliente cliente, Pedido pedido) {
        enviaNotificacao.enviaEmail(cliente, pedido);
    }


}
