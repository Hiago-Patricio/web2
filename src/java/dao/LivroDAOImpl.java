package dao;

import entidades.Livro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class LivroDAOImpl implements LivroDAO {

    private EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("livrariaPU");

    @Override
    public void save(Livro l) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Update
        if (l.getId() > 0) {
            em.merge(l);
        } // Save
        else {
            em.persist(l);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Livro l) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Atualiza objeto a ser usado
        if (!em.contains(l)) {
            l = em.merge(l);
        }

        em.remove(l);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Livro find(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Livro.class, id);
    }

    @Override
    public List<Livro> all() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT l FROM "
                + "Livro as l ORDER BY l.nome");
        return q.getResultList();
    }

}
