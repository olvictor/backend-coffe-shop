package com.example.coffe_shop.repository;

import com.example.coffe_shop.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, UUID> {
}
