package io.github.kassiarsalbuquerque.Cliente.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cliente")
@Getter
@Setter
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "nome", length = 100)
	private String nome;
	
	@Column(name = "cpf" , length = 11)
	private String cpf;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
	private List<Pedido> pedidos;
	
	public Cliente() {
		super();
	}

	public Cliente(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}

	
	@Override
	public String toString() {
		//Para evitar um loop no hibernate na hora de imprimir os campos da entidade Cliente, 
		//evitar colocar no toString o Objeto que simboliza o relacionamento com outra entidade (Ex: pedidos)
		return "Cliente [" + (id != null ? "id=" + id + ", " : "") + (nome != null ? "nome=" + nome + ", " : "")
				+ (cpf != null ? "cpf=" + cpf : "") + "]";
	}

}