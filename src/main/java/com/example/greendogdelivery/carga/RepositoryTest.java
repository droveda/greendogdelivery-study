package com.example.greendogdelivery.carga;

import com.example.greendogdelivery.domain.Cliente;
import com.example.greendogdelivery.domain.Item;
import com.example.greendogdelivery.domain.Pedido;
import com.example.greendogdelivery.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class RepositoryTest implements ApplicationRunner {

    private static final long ID_CLIENTE_DIEGUES = 11l;
    private static final long ID_CLIENTE_ZE_PEQUENO = 22l;

    private static final long ID_ITEM1 = 100l;
    private static final long ID_ITEM2 = 101l;
    private static final long ID_ITEM3 = 102l;

    private static final long ID_PEDIDO1 = 1000l;
    private static final long ID_PEDIDO2 = 1001l;
    private static final long ID_PEDIDO3 = 1002l;

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(">>> Inicializando carga de dados...");
        Cliente clienteDiegues = new Cliente();
        clienteDiegues.setId(ID_CLIENTE_DIEGUES);
        clienteDiegues.setNome("Diegues Girotto Roveda");
        clienteDiegues.setEndereco("Rua Marques do Paraná 1114");

        Cliente clienteZePequeno = new Cliente();
        clienteZePequeno.setId(ID_CLIENTE_ZE_PEQUENO);
        clienteZePequeno.setNome("Zé Pequeno");
        clienteZePequeno.setEndereco("Cidade de Deus");

        Item dog1 = new Item();
        dog1.setId(ID_ITEM1);
        dog1.setNome("Green dog tradicional");
        dog1.setPreco(25d);

        Item dog2 = new Item();
        dog2.setId(ID_ITEM2);
        dog2.setNome("Green dog tradicional Picante");
        dog2.setPreco(27d);

        Item dog3 = new Item();
        dog3.setId(ID_ITEM3);
        dog3.setNome("Green dog max salada");
        dog3.setPreco(30d);

        List<Item> listaPedidoDiegues = new ArrayList<Item>();
        listaPedidoDiegues.add(dog1);

        List<Item> listaPedidoZePequeno = new ArrayList<Item>();
        listaPedidoZePequeno.add(dog2);
        listaPedidoZePequeno.add(dog3);

        Pedido pedidoDiegues = new Pedido();
        pedidoDiegues.setId(ID_PEDIDO1);
        pedidoDiegues.setCliente(clienteDiegues);
        pedidoDiegues.setData(new Date());
        pedidoDiegues.setItens(listaPedidoDiegues);
        pedidoDiegues.setValorTotal(dog1.getPreco());

        clienteDiegues.novoPedido(pedidoDiegues);

        Pedido pedidoZePequeno = new Pedido();
        pedidoZePequeno.setId(ID_PEDIDO2);
        pedidoZePequeno.setCliente(clienteZePequeno);
        pedidoZePequeno.setData(new Date());
        pedidoZePequeno.setItens(listaPedidoZePequeno);
        pedidoZePequeno.setValorTotal(dog2.getPreco() + dog3.getPreco());

        clienteZePequeno.novoPedido(pedidoZePequeno);

        System.out.println(">>> Pedido 1 -  Diegues : " + pedidoDiegues);
        System.out.println(">>> Pedido 2 -  Ze Pequeno : " + pedidoZePequeno);

        clienteRepository.saveAndFlush(clienteZePequeno);
        System.out.println(">>> Gravando cliente 2 : " + clienteZePequeno);

        List<Item> listaPedidoDiegues2 = new ArrayList<Item>();
        listaPedidoDiegues2.add(dog2);

        Pedido pedido2Diegues = new Pedido();
        pedido2Diegues.setId(ID_PEDIDO3);
        pedido2Diegues.setCliente(clienteDiegues);
        pedido2Diegues.setValorTotal(dog2.getPreco());
        pedido2Diegues.setData(new Date());
        pedido2Diegues.setItens(listaPedidoDiegues2);

        clienteDiegues.novoPedido(pedido2Diegues);

        clienteRepository.saveAndFlush(clienteDiegues);

        System.out.println(">>> Pedido 2 - Diegues : " + pedido2Diegues);
        System.out.println(">>> Gravando cliente 1 : " + clienteDiegues);

    }
}
