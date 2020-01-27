package dao;

import cdi.FuncionarioDAOQualifier;
import entidades.Funcionario;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

@FuncionarioDAOQualifier
public class FuncionarioDAOImpl implements FuncionarioDAO {

    @Inject
    private EntityManagerFactory factory;
    
    public void save(Funcionario f) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        if(f.getId() > 0){
            em.merge(f);
        }else{
            em.persist(f);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Funcionario f) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        if(!em.contains(f)){
            f = em.merge(f);
        }
        em.remove(f);
        em.getTransaction().commit();
        em.close();
    }

    public Funcionario find(Long id) {
        EntityManager em = factory.createEntityManager();
        return em.find(Funcionario.class, id);
    }

    public List<Funcionario> all() {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("SELECT f FROM Funcionario as f");
        return q.getResultList();
    }

}
