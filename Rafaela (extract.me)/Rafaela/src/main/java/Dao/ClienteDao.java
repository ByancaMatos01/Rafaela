package Dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entity.Cliente;

	public class ClienteDao implements IClienteDao{

		EntityManagerFactory mf = Persistence.createEntityManagerFactory ("HibJPA");


		public Cliente pesquisar(String cliente) {
			return null;
		}

		public List<Cliente> lista() {
			EntityManager em = mf.createEntityManager();
			List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
			em.close();
			return clientes;
		}
		public List<Cliente> apenasUmCliente (String cpf){
			EntityManager em = mf.createEntityManager();
			List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c WHERE c.cpf LIKE :cpf", Cliente.class)
					.setParameter("cpf", "%" + cpf + "%")
					.getResultList();
			em.close();
			return clientes;
		}

		public void inserir(Cliente cliente) {
			EntityManager em = mf.createEntityManager();
			em.getTransaction().begin();
			em.persist(cliente);
			em.getTransaction().commit();
			em.close();
		}
		public void remover(long id) {
			EntityManager em = mf.createEntityManager();
			em.getTransaction().begin();
			Cliente cliente = em.find(Cliente.class, id);
			if (cliente != null) {
				em.remove(cliente);
			}
			em.getTransaction().commit();
			em.close();
		}

	}