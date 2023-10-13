package io.github.kassiarsalbuquerque.Cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kassiarsalbuquerque.Cliente.model.Produto;

public interface IProdutoRepository extends JpaRepository<Produto, Integer> {

}
