package Dao;

import java.util.List;

import Entity.Cliente;

public interface IClienteDao {
	public Cliente pesquisar(String cliente) ;
	public void inserir(Cliente cliente) ;
	public void remover (long id) ;
	public List<Cliente> lista(); 
	public List<Cliente> apenasUmCliente (String cpf);
}
