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

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public Pedido(List<Produto> produtos,double valor_total){
        this.produtos = produtos;
        this.valor_total = valor_total;
        this.createdAt = LocalDateTime.now();
    }

}
