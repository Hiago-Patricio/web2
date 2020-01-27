package cdi;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {

    @Produces
    public EntityManagerFactory createFactory() {
        EntityManagerFactory factory
                = Persistence
                        .createEntityManagerFactory("livrariaPU");
        return factory;
    }
}
