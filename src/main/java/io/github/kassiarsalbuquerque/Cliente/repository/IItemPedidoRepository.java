package io.github.kassiarsalbuquerque.Cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kassiarsalbuquerque.Cliente.model.ItemPedido;

public interface IItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{

}
