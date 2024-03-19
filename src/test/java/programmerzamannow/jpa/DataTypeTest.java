package programmerzamannow.jpa;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import programmerzamannow.jpa.entity.Customer;
import programmerzamannow.jpa.util.JpaUtil;

public class DataTypeTest {
    @Test
    void testDataType(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("2");
        customer.setName("udin");
        customer.setAge((byte) 30);
        customer.setMarried(true);
        
        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
