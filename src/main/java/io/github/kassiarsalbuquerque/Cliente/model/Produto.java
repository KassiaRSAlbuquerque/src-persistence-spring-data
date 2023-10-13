package io.github.kassiarsalbuquerque.Cliente.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="produto")
@Getter
@Setter
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "descricao" , length = 100)
	private String descricao;
	
	@Column(name="preco_unitario", scale = 2 , precision = 20)
	private BigDecimal preco;
	
 	public Produto() {
		super();
	}

	public Produto(String descricao, BigDecimal preco) {
		super();
		this.descricao = descricao;
		this.preco = preco;
	}
}