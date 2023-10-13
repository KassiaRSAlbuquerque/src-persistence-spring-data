package io.github.kassiarsalbuquerque.Cliente;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.kassiarsalbuquerque.Cliente.model.Cliente;
import io.github.kassiarsalbuquerque.Cliente.model.Pedido;
import io.github.kassiarsalbuquerque.Cliente.repository.IClienteRepository;
import io.github.kassiarsalbuquerque.Cliente.repository.IPedidoRepository;

@SpringBootApplication
public class ClienteApplication {

	@Autowired
	IClienteRepository clienteRepository;
	
	@Autowired
	IPedidoRepository pedidoRepository;
	
	@Bean
	public CommandLineRunner init(IClienteRepository clienteRepository) {
		
		return args ->{
			System.out.println("Salvando clientes.");
			this.clienteRepository.save(new Cliente("ABC","14589632187"));
			this.clienteRepository.save(new Cliente("ABC123","64425445442"));
			this.clienteRepository.save(new Cliente("Teste", "32569874521"));
			
			List<Cliente> todosClientes = this.clienteRepository.findAll();
			todosClientes.forEach(System.out::println);
			
			System.out.println("Atualizando clientes");
			todosClientes.forEach(cliente -> {
									cliente.setNome(cliente.getNome().concat(" atualizado"));
									this.clienteRepository.save(cliente);
			});
			this.clienteRepository.findAll().forEach(System.out::println);
			
			System.out.println("Buscando clientes por NOME.");
			this.clienteRepository.findByNomeLike("ABC%").forEach(System.out::println);
			
			System.out.println("Buscando clientes por NOME ou ID.");
			this.clienteRepository.findByNomeLikeOrIdOrderById("ABC123%", null).forEach(System.out::println);
			
			System.out.println("Buscando cliente por cpf.");
			System.out.println(this.clienteRepository.findOneByCpf("32569874521"));
			
			System.out.println("Buscando clientes por NOME e CPF com HQL");
			this.clienteRepository.encontrarPorNomeECpfHQL("ABC%", "14589632187").forEach(System.out::println);
			
			System.out.println("Buscando clientes por NOME e CPF com SQL NATIVO");
			this.clienteRepository.encontrarPorNomeECpfNativo("ABC%", "64425445442").forEach(System.out::println);
			
			System.out.println("Existe cliente com cpf 32569874521 ? " + this.clienteRepository.existsByCpf("32569874521"));
			
			System.out.println("Deletando clientes.");
			//this.clienteRepository.findAll().forEach(cliente -> {this.clienteRepository.delete(cliente);});
			this.clienteRepository.removendoPeloNomeHQL("ABC123 atualizado");
			this.clienteRepository.removendoPeloNomeNativo("Teste atualizado");
			
			todosClientes = this.clienteRepository.findAll();
			if (todosClientes.isEmpty()) {
				System.out.println("Nenhum cliente encontrado!");
			} else {
				todosClientes.forEach(System.out::println);
			}
			
			System.out.println("-----------------------------PEDIDO-----------------------------");
			System.out.println("Adicionando pedido.");
			
			Cliente clienteparaPedido = this.clienteRepository.save(new Cliente("Cliente para Pedido","96358741523"));
			Pedido pedido = new Pedido(clienteparaPedido, LocalDate.now(), BigDecimal.valueOf(100));
			pedidoRepository.save(pedido);

			System.out.println("Imprimindo a partir do Cliente.");
			Cliente clienteComPedido = this.clienteRepository.findById(clienteparaPedido.getId()).get();
			System.out.println(clienteComPedido);
			System.out.println(clienteComPedido.getPedidos());

			System.out.println("Adicionando novo pedido.");
			pedido = new Pedido(clienteparaPedido, LocalDate.now(), BigDecimal.valueOf(26));
			pedidoRepository.save(pedido);
			
			this.pedidoRepository.findByCliente(clienteparaPedido).forEach(System.out::println);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ClienteApplication.class, args);
	}
}