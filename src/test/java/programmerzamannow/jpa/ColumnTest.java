package programmerzamannow.jpa;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import programmerzamannow.jpa.entity.Customer;
import programmerzamannow.jpa.util.JpaUtil;

public class ColumnTest {
    
    @Test
    void testColumn(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("budi");
        customer.setPrimaryEmail("budi@contoh.com");
        
        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
