package programmerzamannow.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManagerFactory;
import programmerzamannow.jpa.util.JpaUtil;

public class EntityManagerFactoryTest {
    
    @Test
    void create(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();

        Assertions.assertNotNull(entitiyManagerFactory);
    }
}
