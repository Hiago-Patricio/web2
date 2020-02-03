package dao;

import entidades.Revista;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class RevistaDAOImpl implements RevistaDAO {

    @Inject
    private EntityManagerFactory emf;
    @Inject
    private MidiaDAO daoMidia;
    
    @Override
    public void save(Revista r) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Update
        if (r.getId() > 0) {
            em.merge(r);
        } // Save
        else {
            em.persist(r);
        }
        daoMidia.save(r.getMidia());

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Revista r) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Atualiza objeto a ser usado
        if (!em.contains(r)) {
            r = em.merge(r);
        }

        em.remove(r);
        em.getTransaction().commit();
        em.close();
        daoMidia.delete(r.getMidia());
    }

    @Override
    public Revista find(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Revista.class, id);
    }

    @Override
    public List<Revista> all() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT r FROM "
                + "Revista as r ORDER BY r.titulo");
        return q.getResultList();
    }

}
