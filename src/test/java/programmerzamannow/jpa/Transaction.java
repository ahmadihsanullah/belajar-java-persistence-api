package programmerzamannow.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.persistence.*;
import programmerzamannow.jpa.util.JpaUtil;

public class Transaction {
    @Test
    void create(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();

        EntityManager entityManager = entitiyManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        Assertions.assertNotNull(entityTransaction);
        try{
            entityTransaction.begin();
            //manipulasi database
            entityTransaction.commit();
        }catch(Throwable throwable){
            entityTransaction.rollback();
        }
        entityManager.close();
    }
}
