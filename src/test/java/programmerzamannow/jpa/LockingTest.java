package programmerzamannow.jpa;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.Local;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import programmerzamannow.jpa.entity.Brand;
import programmerzamannow.jpa.util.JpaUtil;

public class LockingTest {
    
    @Test
    void testOptimisticLockingInsert(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        
        Brand brand = new Brand();
        brand.setId("nokia");
        brand.setName("NOKIA");
        brand.setDescription("NOKIA STAR");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        entityManager.persist(brand);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void testOptimisticLockingUpdatedDemo1() throws InterruptedException{
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        
        Brand brand = entityManager.find(Brand.class, "nokia");
        brand.setDescription("NOKIA 1 STAR");
        brand.setUpdatedAt(LocalDateTime.now());

        Thread.sleep(10 * 1000L);
        entityManager.merge(brand);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void testOptimisticLockingUpdatedDemo2(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        
        Brand brand = entityManager.find(Brand.class, "nokia");
        brand.setDescription("NOKIA 2STAR");
        brand.setUpdatedAt(LocalDateTime.now());
        entityManager.merge(brand);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void testPessimisticLockingUpdatedDemo1() throws InterruptedException{
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        
        // LockModeType.PESSIMISTIC_WRITE -> SELECT FOR UPDATE
        Brand brand = entityManager.find(Brand.class, "nokia", LockModeType.PESSIMISTIC_WRITE);
        brand.setDescription("NOKIA 1 STAR Pessimistic");
        brand.setUpdatedAt(LocalDateTime.now());

        Thread.sleep(10 * 1000L);
        entityManager.merge(brand);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void testPessimisticLockingUpdatedDemo2(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // LockModeType.PESSIMISTIC_WRITE -> SELECT FOR UPDATE
        Brand brand = entityManager.find(Brand.class, "nokia", LockModeType.PESSIMISTIC_WRITE);
        brand.setDescription("NOKIA 2 STAR Pessimistic ");
        brand.setUpdatedAt(LocalDateTime.now());
        entityManager.merge(brand);

        entityTransaction.commit();
        entityManager.close();
    }

}
