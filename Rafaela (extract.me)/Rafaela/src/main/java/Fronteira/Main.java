package Fronteira;

import java.util.List;
import java.util.Scanner;

import Controller.ClienteController;
import Entity.Cliente;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String cpfCliente;
		Cliente c1 = new Cliente ();
		ClienteController cControl = new ClienteController();
		int escolha;

		do {
			System.out.println("Menu:");
			System.out.println("1. Inserir Cliente");
			System.out.println("2. Pequisar um cliente");
			System.out.println("3. Pesquisar Todos");
			System.out.println("4. Excluir cliente");
			System.out.println("5. Sair");

			System.out.print("Escolha uma opção: ");
			escolha = scanner.nextInt();

			switch (escolha) {
			case 1:
				
				System.out.println("Inserir Cliente");
				c1.setId(0);
				System.out.println("Insere o nome Cliente:");
				c1.setNome("Cristina");
				c1.setTelefone("119823456");
				c1.setEndereco("av.Bragança França");
				c1.setCep("222222222");
				c1.setCpf("33333333333");
				cControl.inserirCliente(c1);		
				break;
				
			case 2:
				
				System.out.println("Pesquisar um cliente");
				cpfCliente = scanner.next();
				List<Cliente> clientes = cControl.pesquisarUmCliente(cpfCliente);	
				if (!(clientes.isEmpty())) {
					for (Cliente cliente : clientes ) {
						System.out.println("CPF: " + cliente.getCpf() + " Id= " + cliente.getId()+ " Nome: " + cliente.getNome()+ "Telefone"+ cliente.getTelefone()+ "Endereço"+ cliente.getEndereco()+ "cep"+ cliente.getCep());
					}
				}
				else System.out.println("Não encontrou o Cliente");
				scanner.nextLine();
				break;
				
			case 3:
				
				System.out.println("Listando Todos os Clientes");
				clientes = cControl.pesquisarTodos();	
				if (!(clientes.isEmpty())) {
					for (Cliente cliente : clientes ) {
						System.out.println("CPF: " + cliente.getCpf() + " Id= " + cliente.getId()+ " Nome: " + cliente.getNome()+ "Telefone"+ cliente.getTelefone()+ "Endereço"+ cliente.getEndereco()+ "cep"+ cliente.getCep());					}
				}
				else System.out.println("Não encontrou o Cliente desejado");
				break;
				
			case 4:
				System.out.println("Escolha um cliente para remover do Buffet");
				long id = scanner.nextLong();
				cControl.apagar(id);
				break;
				
			case 5:
			
				System.out.println("Saindo do menu.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (escolha != 5);

		scanner.close();
	}
}