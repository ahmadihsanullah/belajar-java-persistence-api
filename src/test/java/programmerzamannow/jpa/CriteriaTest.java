package programmerzamannow.jpa;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Brand;
import programmerzamannow.jpa.entity.Product;
import programmerzamannow.jpa.entity.SimpleBrand;
import programmerzamannow.jpa.util.JpaUtil;

import java.util.List;

public class CriteriaTest {

    @Test
    void criteriaQuery(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Brand> criteria = builder.createQuery(Brand.class);
        Root<Brand> b = criteria.from(Brand.class);
        criteria.select(b); //select b from Brand b

        TypedQuery<Brand> query = entityManager.createQuery(criteria);
        List<Brand> brands = query.getResultList();
        for (Brand brand : brands) {
            System.out.println(brand.getId() + " : " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaQueryNonEntity(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Brand> b = criteria.from(Brand.class);
        criteria.select(builder.array(b.get("id"), b.get("name")));

        TypedQuery<Object[]> query = entityManager.createQuery(criteria);
        List<Object[]> objects = query.getResultList();

        for (Object[] object : objects) {
            System.out.println("ID : " + object[0]);
            System.out.println("NAME : " + object[1]);
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaQueryNonEntityWithConstructorExpression(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<SimpleBrand> criteria = builder.createQuery(SimpleBrand.class);
        Root<Brand> b = criteria.from(Brand.class);
        criteria.select(builder.construct(SimpleBrand.class, b.get("id"), b.get("name")));

        TypedQuery<SimpleBrand> query = entityManager.createQuery(criteria);
        List<SimpleBrand> brands = query.getResultList();

        for (SimpleBrand brand : brands) {
            System.out.println("ID: " + brand.getId() + " name: " + brand.getName());
        }
        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaWhereClause(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brand> criteria = builder.createQuery(Brand.class);
        Root<Brand> b = criteria.from(Brand.class);
        criteria.select(b);

        criteria.where(
                builder.equal(b.get("name"), "xiomi"),
                builder.isNotNull(b.get("createdAt"))
        );

        TypedQuery<Brand> query = entityManager.createQuery(criteria);
        List<Brand> brands = query.getResultList();

        brands.forEach(brand -> System.out.println(brand.getId() + " : " + brand.getName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaWhereClauseUsingOrOperator(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brand> criteria = builder.createQuery(Brand.class);
        Root<Brand> b = criteria.from(Brand.class);
        criteria.select(b);

        criteria.where(
                builder.or(
                        builder.equal(b.get("name"), "xiomi"),
                        builder.equal(b.get("name"), "samsung")
                )
        );

        TypedQuery<Brand> query = entityManager.createQuery(criteria);
        List<Brand> brands = query.getResultList();

        brands.forEach(brand -> System.out.println(brand.getId() + " : " + brand.getName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaJoinClause(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> p = criteria.from(Product.class);
        Join<Product, Brand> b = p.join("brand");

        // select p from Product p join p.brand b
        criteria.select(p);
        criteria.where(
                builder.equal(b.get("name"), "samsung")
        );
        // select p from Product p join p.brand b where b.name = 'Samsung'

        TypedQuery<Product> query = entityManager.createQuery(criteria);
        List<Product> products = query.getResultList();
        products.forEach(product -> System.out.println(product.getId() + " : " + product.getName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaJoinClauseWithParameter(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> p = criteria.from(Product.class);
        Join<Product, Brand> b = p.join("brand");

        ParameterExpression<String> brandParameter = builder.parameter(String.class, "brand");

        // select p from Product p join p.brand b
        criteria.select(p);
        criteria.where(
                builder.equal(b.get("name"), brandParameter)
        );
        // select p from Product p join p.brand b where b.name = 'Samsung'

        TypedQuery<Product> query = entityManager.createQuery(criteria);
        query.setParameter(brandParameter, "xiomi");
        List<Product> products = query.getResultList();
        products.forEach(product -> System.out.println(product.getId() + " : " + product.getName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaAggregateQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Product> p = criteria.from(Product.class);
        Join<Product, Brand> b = p.join("brand");

        criteria.select(builder.array(
                b.get("id"),
                builder.min(p.get("price")),
                builder.max(p.get("price")),
                builder.avg(p.get("price"))
        ));
        // select b.id, min(p.price), max(p.price), avg(p.price) from Product p join p.brand b

        criteria.groupBy(b.get("id"));
        // select b.id, min(p.price), max(p.price), avg(p.price) from Product p join p.brand b group by b.id

        criteria.having(builder.greaterThan(builder.min(p.get("price")), 500_000L));
        // select b.id, min(p.price), max(p.price), avg(p.price) from Product p join p.brand b group by b.id having min(p.price) > 500000

        TypedQuery<Object[]> query = entityManager.createQuery(criteria);
        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects) {
            System.out.println("Brand : " + object[0]);
            System.out.println("Min : " + object[1]);
            System.out.println("Max : " + object[2]);
            System.out.println("Average : " + object[3]);
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaNonQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Brand> criteria = builder.createCriteriaUpdate(Brand.class);
        Root<Brand> b = criteria.from(Brand.class);

        criteria.set(b.get("name"), "Apple Updated");
        criteria.set(b.get("description"), "Apple Company");

        criteria.where(
                builder.equal(b.get("id"), "apple")
        );

        Query query = entityManager.createQuery(criteria);
        int impactedRecords = query.executeUpdate();
        System.out.println("Success update " + impactedRecords + " records");

        entityTransaction.commit();
        entityManager.close();
    }
}
