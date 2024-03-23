package programmerzamannow.jpa;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import programmerzamannow.jpa.entity.Member;
import programmerzamannow.jpa.entity.Name;
import programmerzamannow.jpa.util.JpaUtil;

public class CollectionTest {
    
    @Test
    void create(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        
        Name name = new Name();
        name.setFirstName("ahmad");
        name.setMiddleName("ihsanullah");
        name.setLastName("rabbani");

        Member member = new Member();
        member.setEmail("ahmad@gmail.com"); 
        member.setName(name);

        member.setHobbies(new ArrayList<>());
        member.getHobbies().add("Coding");
        member.getHobbies().add("Reading");

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void update() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = entityManager.find(Member.class, 2);

        member.getHobbies().add("Traveling");

        entityManager.merge(member);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void updateSkills() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = entityManager.find(Member.class, 2);
        member.setSkills(new HashMap<>());
        member.getSkills().put("Java", 90);
        member.getSkills().put("Golang", 90);
        member.getSkills().put("PHP", 85);

        entityManager.merge(member);

        entityTransaction.commit();
        entityManager.close();
    }
}
