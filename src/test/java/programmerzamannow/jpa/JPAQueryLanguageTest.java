package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Brand;
import programmerzamannow.jpa.entity.Member;
import programmerzamannow.jpa.util.JpaUtil;

import java.util.List;

public class JPAQueryLanguageTest {
    @Test
    void select(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b", Brand.class);
        List<Brand> brands = query.getResultList();

        for (Brand brand : brands) {
            System.out.println(brand.getId() + " : " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void whereClause(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("select m from Member m where " +
                "m.name.firstName = :firstName and m.name.lastName = :lastName", Member.class);
        query.setParameter("firstName", "ahmad");
        query.setParameter("lastName", "rabbani");

        List<Member> members = query.getResultList();
        for (Member member : members) {
            System.out.println(member.getId() + " : " + member.getFullname());
        }

        entityTransaction.commit();
        entityManager.close();
    }

}
