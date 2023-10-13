package io.github.kassiarsalbuquerque.Cliente.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="pedido")
@Getter
@Setter
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Column(name = "data_pedido")
	private LocalDate dataPedido;
	
	@Column(name = "total" , precision = 20 , scale = 2)
	private BigDecimal total;
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
	private List<ItemPedido> itens;
	
	public Pedido() {
		super();
	}

	public Pedido(Cliente cliente, LocalDate dataPedido, BigDecimal total) {
		super();
		this.cliente = cliente;
		this.dataPedido = dataPedido;
		this.total = total;
	}

	@Override
	public String toString() {
		//Para evitar um loop no hibernate na hora de imprimir os campos da entidade Pedido, 
		//evitar colocar no toString o Objeto que simboliza o relacionamento com outra entidade (Ex: cliente, itens)
		return "Pedido [" + (id != null ? "id=" + id + ", " : "")
				+ (dataPedido != null ? "dataPedido=" + dataPedido + ", " : "")
				+ (total != null ? "total=" + total : "") + "]";
	}
	
}