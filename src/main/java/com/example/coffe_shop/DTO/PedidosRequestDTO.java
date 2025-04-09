package com.example.coffe_shop.DTO;

import com.example.coffe_shop.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record PedidosRequestDTO(@JsonProperty("produtos") List<Produto> produtos) {
}
