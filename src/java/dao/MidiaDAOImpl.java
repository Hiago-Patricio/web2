package dao;

import entidades.Midia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MidiaDAOImpl implements MidiaDAO{
    private EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("livrariaPU");

    @Override
    public void save(Midia m) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Update
        if (m.getId() > 0) {
            em.merge(m);
        } // Save
        else {
            em.persist(m);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Midia m) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Atualiza objeto a ser usado
        if (!em.contains(m)) {
            m = em.merge(m);
        }

        Query query = em.createNativeQuery(
                "DELETE FROM Compra C WHERE c.midia_id = " + m.getId());
        query.executeUpdate();

        em.remove(m);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Midia find(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Midia.class, id);
    }

    @Override
    public List<Midia> all() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT m FROM "
                + "Midia as m");
        return q.getResultList();
    }
}
