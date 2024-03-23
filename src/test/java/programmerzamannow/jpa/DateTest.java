package programmerzamannow.jpa;

import java.time.LocalDateTime;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import programmerzamannow.jpa.entity.Category;
import programmerzamannow.jpa.util.JpaUtil;

public class DateTest {

    @Test
    void testDate() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Category category = new Category();
        category.setName("Food");
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        entityManager.persist(category);

        entityTransaction.commit();
        entityManager.close();
    }
}
