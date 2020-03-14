package com.example.greendogdelivery.repository;

import com.example.greendogdelivery.domain.Cliente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository repository;

    @Test
    public void buscaClientesCadastrados() {
        Page<Cliente> clientes = this.repository.findAll(new PageRequest(0, 10));
        assertThat(clientes.getTotalElements()).isGreaterThan(1L);
    }

    @Test
    public void buscaClienteDiegues() {
        Cliente clienteNaoEncontrado = repository.findByNome("Someone");
        assertThat(clienteNaoEncontrado).isNull();

        Cliente cliente = this.repository.findByNome("Diegues Girotto Roveda");
        assertThat(cliente).isNotNull();
        assertThat(cliente.getNome()).isEqualTo("Diegues Girotto Roveda");
        assertThat(cliente.getEndereco()).isEqualTo("Rua Marques do Paraná 1114");
    }

}
