package dao;

import entidades.Compra;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class CompraDAOImpl implements CompraDAO {

    @Inject
    private EntityManagerFactory emf;
    
    @Override
    public void save(Compra c) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        if(c.getId() > 0){
            em.merge(c);
        }else{
            em.persist(c);
        }
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Compra c) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Atualiza objeto a ser usado
        if (!em.contains(c)) {
            c = em.merge(c);
        }
        
        em.remove(c);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Compra find(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Compra.class, id);
    }

    @Override
    public List<Compra> all() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT c FROM "
                + "Compra as c ORDER BY c.id");
        return q.getResultList();
    }

}
