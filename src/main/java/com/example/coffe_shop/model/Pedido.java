package com.example.coffe_shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "pedidos")
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",  // Nome da tabela intermediária
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDoPedido status_do_pedido;


    @Column(nullable = false)
    private double valor_total;

    private LocalDateTime createdAt;

    public Pedido() {
        this.status_do_pedido = StatusDoPedido.PENDENTE;
    }

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Pedido(List<Produto> produtos, double valorTotal, Usuario usuario){
        this.produtos = produtos;
        this.valor_total = valorTotal;
        this.status_do_pedido = StatusDoPedido.PENDENTE;
        this.createdAt = LocalDateTime.now();
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", produtos=" + produtos +
                ", status_do_pedido=" + status_do_pedido +
                ", valor_total=" + valor_total +
                ", createdAt=" + createdAt +
                '}';
    }

}
