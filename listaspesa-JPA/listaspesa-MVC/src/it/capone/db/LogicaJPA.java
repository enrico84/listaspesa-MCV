package it.capone.db;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

//Classe atta a realizzare le operazioni CRUD
public class LogicaJPA {
	
	@PersistenceUnit  //Per ottenere la factory si usa l'annotazione @PersistenceUnit
	private EntityManagerFactory factory;
	private EntityManager em;
	
	@Resource
	private EJBContext context;
	
	
	/* Oggetto che ci permette di gestire la persistenza -> EntityManager
     * (nomeOggetto==attributo name del tag<persistence-unit>)
     */
	public LogicaJPA(String persistenceUnitName)
	{
		factory = Persistence.createEntityManagerFactory(persistenceUnitName);
		em = factory.createEntityManager();
		//settando il FlushMode a COMMIT si effettua la sincronizzazione col DB solo al Commit della transazione
		em.setFlushMode(FlushModeType.COMMIT); 
		
	}
	
	public void create(Object ob)
	{
		try {
			em.getTransaction().begin();
			em.persist(ob);
			em.getTransaction().commit();
		    //em.detach(ob);
		}
		catch(Exception e) {
			if(em.getTransaction().isActive()) {
				//em.getTransaction().rollback();
				this.context.setRollbackOnly();  //In questo modo la transazione verrà marcata come adibita al Rollback
				System.out.println("Transazione non riuscita..." +e.getMessage());
			}
		}
		
		
	}
	
	public Query readSimpleQuery(String query)
	{
		Query q = null;
		try {
			
			q = em.createQuery(query);
		}
		catch(Exception e) {
			if(em.getTransaction().isActive()) {
				//em.getTransaction().rollback();
				this.context.setRollbackOnly();  //In questo modo la transazione verrà marcata come adibita al Rollback
				System.out.println("Transazione non riuscita..." +e.getMessage());
			}
		}
		
		
		return q;
	}
	
	public Query readNameQuery(String query) {
		
		Query q = null;
		try {
			em.getTransaction().begin();
			q = em.createNamedQuery(query);
			em.getTransaction().commit();
		}
		catch(Exception e) {
			if(em.getTransaction().isActive()) {
				//em.getTransaction().rollback();
				this.context.setRollbackOnly();  //In questo modo la transazione verrà marcata come adibita al Rollback
				
				System.out.println("Transazione non riuscita..." +e.getMessage());
			}
		}
		
		
		return q;
	}
	
	public boolean update(Object ob)
	{
		boolean update = false;
		try {
			em.getTransaction().begin();
			em.merge(ob);
			em.getTransaction().commit();
			update = true;
			//em.detach(ob);
		}
		catch(Exception e) {
			update = false;
			if(em.getTransaction().isActive()) {
				//em.getTransaction().rollback();  
				this.context.setRollbackOnly();  //In questo modo la transazione verrà marcata come adibita al Rollback
				System.out.println("Transazione non riuscita..." +e.getMessage());
			}
		}
		
		return update;
		
		
	}
	
	public boolean delete(Object ob)
	{
		boolean deleted = false;
		try {
			em.getTransaction().begin();
			em.remove(em.merge(ob));
			em.getTransaction().commit();
			deleted = true;
		}
		catch(Exception e) {
			if(em.getTransaction().isActive()) {
				//em.getTransaction().rollback();
				this.context.setRollbackOnly();  //In questo modo la transazione verrà marcata come adibita al Rollback
				System.out.println("Transazione non riuscita..." +e.getMessage());
			}
		}
		
		return deleted;
		
	}
	
	public boolean truncate(String table) {

		boolean truncate = false;
		try {
			em.getTransaction().begin();
			em.createNativeQuery("Truncate Table " +table).executeUpdate();
			em.getTransaction().commit();
			truncate = true;
		}
		catch(Exception e) {
			if(em.getTransaction().isActive()) {
				//em.getTransaction().rollback();
				this.context.setRollbackOnly();  //In questo modo la transazione verrà marcata come adibita al Rollback
				System.out.println("Transazione non riuscita..." +e.getMessage());
			}
		}
		
		return truncate;

	}
	
	
	public Object find(Object obj, Integer id) {
		
		Object object = em.find(obj.getClass(), id);
		
		return object;
		
	}
	
	
	//OPERAZIONE DA EFFETTUARE SEMPRE ALLA FINE PER CHIUDERE L'ENTITY MANAGER
	public void closeLogicaJPA()
	{
		//if(factory.isOpen())
			factory.close();
		
		//if(em.isOpen())
			
			em.close();
	}
	
}
