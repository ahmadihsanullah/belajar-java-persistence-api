package programmerzamannow.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import programmerzamannow.jpa.entity.Department;
import programmerzamannow.jpa.entity.DepartmentId;
import programmerzamannow.jpa.util.JpaUtil;

public class EmbeddedTest {
    
    @Test
    void testEmbedded(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        DepartmentId departmentId = new DepartmentId();
        departmentId.setCompanyId("pzn");
        departmentId.setDepartmentId("tech");

        Department department = new Department();
        department.setId(departmentId);
        department.setName("Teknologi");
        entityManager.persist(department);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void testEmbeddedFind(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        DepartmentId departmentId = new DepartmentId();
        departmentId.setCompanyId("pzn");
        departmentId.setDepartmentId("tech");

        Department department = entityManager.find(Department.class, departmentId);
        assertEquals("Teknologi", department.getName());

        entityTransaction.commit();
        entityManager.close();
    }
}
