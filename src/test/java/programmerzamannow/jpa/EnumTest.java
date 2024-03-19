package programmerzamannow.jpa;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import programmerzamannow.jpa.entity.Customer;
import programmerzamannow.jpa.entity.CustomerType;
import programmerzamannow.jpa.util.JpaUtil;

public class EnumTest {
    @Test
    void testEnum(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("3");
        customer.setName("joko");
        customer.setPrimaryEmail("joko@contoh.com");
        customer.setMarried(true);
        customer.setType(CustomerType.REGULAR);
        
        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
