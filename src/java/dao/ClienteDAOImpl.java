package dao;

import entidades.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ClienteDAOImpl implements ClienteDAO {

    private EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("livrariaPU");

    @Override
    public void save(Cliente c) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Update
        if (c.getId() > 0) {
            em.merge(c);
        } // Save
        else {
            em.persist(c);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Cliente c) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Atualiza objeto a ser usado
        if (!em.contains(c)) {
            c = em.merge(c);
        }

        Query query = em.createNativeQuery(
                "DELETE FROM Compra c WHERE c.cliente_id = " + c.getId());
        query.executeUpdate();

        em.remove(c);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Cliente find(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Cliente.class, id);
    }

    @Override
    public List<Cliente> all() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT c FROM "
                + "Cliente as c ORDER BY c.nome");
        return q.getResultList();
    }

}
