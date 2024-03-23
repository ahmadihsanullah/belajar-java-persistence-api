package programmerzamannow.jpa;


import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import programmerzamannow.jpa.entity.Category;
import programmerzamannow.jpa.entity.Member;
import programmerzamannow.jpa.util.JpaUtil;

public class EntityListener {
     @Test
    void create(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        
        Category category = new Category();
        category.setName("bola");

        entityManager.persist(category);

        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void update(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        
        Category category = entityManager.find(Category.class, 4);
        category.setName("bola kecil");

        entityManager.merge(category);
        
        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void testPostLoad(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        
       Member member = entityManager.find(Member.class, 1);

       Assertions.assertEquals("Mr. Ahmad Ihsanullah Rabbani", member.getFullname());
        entityTransaction.commit();
        entityManager.close();
    }
}
