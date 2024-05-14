package programmerzamannow.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import programmerzamannow.jpa.entity.Customer;
import programmerzamannow.jpa.util.JpaUtil;

public class CRUDTest {
    private EntityManagerFactory entitiyManagerFactory;

    @BeforeEach
    void setUp() {
        entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    @Test
    void insert() {
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("Ahmad");

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }


    @Test
    void read() {
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = entityManager.find(Customer.class, "1");
        Assertions.assertNotNull(customer);
        Assertions.assertEquals("1", customer.getId());
        Assertions.assertEquals("Ahmad Ihsanullah", customer.getName());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void update() {
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = entityManager.find(Customer.class, "1");
        customer.setName("Ahmad Ihsanullah");

        entityManager.merge(customer);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void remove() {
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = entityManager.find(Customer.class, "1");

        entityManager.remove(customer);

        entityTransaction.commit();
        entityManager.close();
    }

    
    

    
}
