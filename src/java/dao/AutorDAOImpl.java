package dao;

import entidades.Autor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AutorDAOImpl implements AutorDAO{

    private EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("livrariaPU");
    
    public void save(Autor a) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        if(a.getId() > 0){
            em.merge(a);
        }else{
            em.persist(a);
        }
        
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Autor a) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
                
        if(!em.contains(a)){
            a = em.merge(a);
        }
        
        em.remove(a);
        em.getTransaction().commit();
        em.close();
    }

    public Autor find(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Autor.class, id);
    }

    public List<Autor> all() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT a FROM Autor a");
        return q.getResultList();
    }

}
