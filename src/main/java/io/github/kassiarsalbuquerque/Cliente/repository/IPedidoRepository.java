package io.github.kassiarsalbuquerque.Cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kassiarsalbuquerque.Cliente.model.Cliente;
import io.github.kassiarsalbuquerque.Cliente.model.Pedido;

public interface IPedidoRepository extends JpaRepository<Pedido, Integer>{

	List<Pedido> findByCliente(Cliente cliente);
}
