package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import programmerzamannow.jpa.entity.Brand;
import programmerzamannow.jpa.entity.Employee;
import programmerzamannow.jpa.entity.Manager;
import programmerzamannow.jpa.entity.Payment;
import programmerzamannow.jpa.entity.PaymentCreditCard;
import programmerzamannow.jpa.entity.PaymentGopay;
import programmerzamannow.jpa.entity.Transaction;
import programmerzamannow.jpa.entity.TransactionCredit;
import programmerzamannow.jpa.entity.TransactionDebit;
import programmerzamannow.jpa.entity.VicePresident;
import programmerzamannow.jpa.util.JpaUtil;

public class InheritanceTest {


    @Test
    void testSingleTable(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Employee employee = new Employee();
        employee.setId("rina");
        employee.setName("Rina Wati");
        entityManager.persist(employee);

        Manager manager = new Manager();
        manager.setId("joko");
        manager.setName("Joko Moro");
        manager.setTotalEmployee(10);
        entityManager.persist(manager);

        VicePresident vp = new VicePresident();
        vp.setId("budi");
        vp.setName("Budi Nugraha");
        vp.setTotalManager(15);
        entityManager.persist(vp);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void testSingleTableFind(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Employee employee = entityManager.find(Employee.class, "budi");
        VicePresident vp = (VicePresident) employee;
        Assertions.assertEquals("Budi Nugraha", vp.getName());
       
        entityTransaction.commit();
        entityManager.close();
    }


    @Test
    void testJoinedTable(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        PaymentGopay paymentGopay = new PaymentGopay();
        paymentGopay.setId("gopay1");
        paymentGopay.setAmount(1_000_000L);
        paymentGopay.setGopayId("0808201310");
        entityManager.persist(paymentGopay);

        PaymentCreditCard paymentCreditCard = new PaymentCreditCard();
        paymentCreditCard.setId("cc1");
        paymentCreditCard.setMaskedCard("4555-5555");
        paymentCreditCard.setBank("BCA");
        paymentCreditCard.setAmount(5_000_000L);
        entityManager.persist(paymentCreditCard);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void testFindJoinedTableChild(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        PaymentGopay payment = entityManager.find(PaymentGopay.class, "gopay1");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void testFindJoinedTableParent(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Payment payment = entityManager.find(Payment.class, "gopay1");
        PaymentGopay paymentGopay = (PaymentGopay) payment;

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void testTransactionInsert(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Transaction transaction = new Transaction();
        transaction.setId("t1");
        transaction.setBalance(1_000_000L);
        transaction.setCreatedAt(LocalDateTime.now());
        entityManager.persist(transaction);

        TransactionDebit transactionDebit = new TransactionDebit();
        transactionDebit.setId("t2");
        transactionDebit.setBalance(2_000_000L);
        transactionDebit.setCreatedAt(LocalDateTime.now());
        transactionDebit.setDebitAmount(1_000_000L);
        entityManager.persist(transactionDebit);

        TransactionCredit transactionCredit = new TransactionCredit();
        transactionCredit.setId("t3");
        transactionCredit.setBalance(1_000_000L);
        transactionCredit.setCreatedAt(LocalDateTime.now());
        transactionCredit.setCreditAmount(1_000_000L);
        entityManager.persist(transactionCredit);

        entityTransaction.commit();
        entityManager.close();
    }


    @Test
    void testTransactionFInd(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Transaction transaction = entityManager.find(Transaction.class, "t2");
        TransactionDebit transactionDebit = (TransactionDebit) transaction;

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void testBrandInserWithMappedSuperClass(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = new Brand();
        brand.setId("xiomi");
        brand.setName("Xiomi");
        brand.setDescription("Xiomi Global");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        entityManager.persist(brand);

        entityTransaction.commit();
        entityManager.close();
    }
}
