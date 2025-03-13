package com.example.coffe_shop.repository;

import com.example.coffe_shop.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository  extends JpaRepository<Pedido, UUID> {
}
