package programmerzamannow.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import programmerzamannow.jpa.entity.Category;
import programmerzamannow.jpa.util.JpaUtil;

public class GeneratedValueTest {
    @Test
    void testGeneratedValue(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Category category = new Category();
        category.setName("Gadget");
        category.setDescription("Gadget Murah");
        entityManager.persist(category);
        Assertions.assertNotNull(category.getId());

        entityTransaction.commit();
        entityManager.close();
    }
}
