package cdi;

import entidades.Funcionario;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Utils {

    @Produces
    public EntityManagerFactory createFactory() {
        EntityManagerFactory factory
                = Persistence
                        .createEntityManagerFactory("livrariaPU");
        return factory;
    }
    
}
