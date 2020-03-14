package com.example.greendogdelivery.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class RespostaDTO {

    private Double valorTotal;
    private Long pedido;
    private String mensagem;

    public RespostaDTO() {
    }

    public RespostaDTO(Double valorTotal, Long pedido, String mensagem) {
        this.valorTotal = valorTotal;
        this.pedido = pedido;
        this.mensagem = mensagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RespostaDTO that = (RespostaDTO) o;
        return valorTotal.equals(that.valorTotal) &&
                pedido.equals(that.pedido) &&
                mensagem.equals(that.mensagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valorTotal, pedido, mensagem);
    }

    @Override
    public String toString() {
        return "RespostaDTO{" +
                "valorTotal=" + valorTotal +
                ", pedido=" + pedido +
                ", mensagem='" + mensagem + '\'' +
                '}';
    }
}
