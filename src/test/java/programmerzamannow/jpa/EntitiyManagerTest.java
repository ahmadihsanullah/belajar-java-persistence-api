package programmerzamannow.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import programmerzamannow.jpa.util.JpaUtil;

public class EntitiyManagerTest {
    
    @Test
    void create(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();

        EntityManager entityManager = entitiyManagerFactory.createEntityManager();

        // sql ke database

        Assertions.assertNotNull(entityManager);
        entityManager.close();
    }
}
