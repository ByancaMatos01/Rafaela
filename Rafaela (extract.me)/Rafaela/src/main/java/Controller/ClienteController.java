package Controller;

import java.util.List;

import Dao.ClienteDao;
import Entity.Cliente;

public class ClienteController {

	private ClienteDao clienteDao = new ClienteDao();
	
	public void inserirCliente (Cliente cliente){
		clienteDao.inserir(cliente);
	}
	public List<Cliente> pesquisarTodos (){
		return clienteDao.lista();
	}
	public List<Cliente> pesquisarUmCliente (String nome){
		return clienteDao.apenasUmCliente(nome);
	}
	
	public void apagar (long id) {
		clienteDao.remover(id);
	}

}