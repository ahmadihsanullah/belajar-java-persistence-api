package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Brand;
import programmerzamannow.jpa.util.JpaUtil;

import java.time.LocalDateTime;

public class ManagedEntityTest {

    @Test
    void testManagedEntity() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        // unmanaged entity
        Brand brand = new Brand();
        brand.setId("apple");
        brand.setName("Apple");
        entityManager.persist(brand);//managed entity

        brand.setName("Apple International");// tetap keupdate karena sudah dimanaged oleh JPA

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void tesFindManagedEntity() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // managed entity
        Brand brand = entityManager.find(Brand.class,"apple");
        brand.setName("Apple Indonesia"); //tetap dimasukan datanya karena sudah dimanaged

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void tesFindUnmanagedEntity() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // managed entity
        Brand brand = entityManager.find(Brand.class,"apple");
        entityManager.detach(brand); //unmanaged entity
        brand.setName("Apple Indonesia 2"); //tidak dimasukan otomatis datanya karena sudah tidak managed

        entityTransaction.commit();
        entityManager.close();
    }

}
