package programmerzamannow.jpa;

import jakarta.persistence.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.*;
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

    @Test
    void joinClause() {
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p join p.brand b where b.name = :brand", Product.class);
        query.setParameter("brand", "samsung");

        List<Product> products = query.getResultList();
        products.forEach(product -> System.out.println(product.getName() + " : " + product.getBrand().getName()));
        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinFetchClause() {
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<User> query = entityManager.createQuery("select u from User u join fetch u.likes p where p.name = :product", User.class);
        query.setParameter("product", "Samsung Galaxy X1");

        List<User> users = query.getResultList();
        for (User user : users) {
            System.out.println("user" + user.getName());
            for (Product product : user.getLikes()) {
                System.out.println("product" + product.getName());
            }
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinFetchClause2() {
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<User> query = entityManager.createQuery("select u from User u join fetch u.likes p where u.id = :user", User.class);
        query.setParameter("user","ahmad");

        List<User> users = query.getResultList();

        for (User user : users) {
            System.out.println("User : " + user.getName());
            System.out.println("=============");
            for (Product product : user.getLikes()) {
                System.out.println(product.getName());
            }
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void orderByClause(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.name desc", Brand.class);

        List<Brand> brands = query.getResultList();
        brands.forEach(brand-> System.out.println(brand.getName()));
        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void limitClause(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.name desc", Brand.class);
        query.setFirstResult(2);
        query.setMaxResults(2);

//        Xiomi
//        Samsung
//        NOKIA             -> ini keluar
//        Apple Indonesia   -> ini keluar

        List<Brand> brands = query.getResultList();
        brands.forEach(brand-> System.out.println(brand.getName()));
        entityTransaction.commit();
        entityManager.close();
    }

    //    Select Some Fields
    @Test
    void selectSomeFields(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select b.id, b.name from Brand b where b.name= :name", Object[].class);
        query.setParameter("name","xiomi");

        List<Object[]> objects = query.getResultList();

        objects.forEach(object -> System.out.println(object[0] + " : " + object[1]));
        entityTransaction.commit();
        entityManager.close();
    }

//    Constructor Expression
    @Test
    void namedQuery(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createNamedQuery("Brand.findAllByName", Brand.class);
        query.setParameter("name","xiomi");

        List<Brand> brands = query.getResultList();
        brands.forEach(brand-> System.out.println(brand.getName()));
        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void contructorExpression(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<SimpleBrand> query = entityManager.createQuery("select new programmerzamannow.jpa.entity.SimpleBrand(b.id, b.name) " +
                "from Brand b where b.name = :name", SimpleBrand.class);

        query.setParameter("name","xiomi");

        List<SimpleBrand> simpleBrands = query.getResultList();
        simpleBrands.forEach(simpleBrand -> System.out.println(simpleBrand.getId() + ":" + simpleBrand.getName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void aggregateFunction(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery(
                "select min(p.price), max(p.price), avg(p.price) from Product p", Object[].class);

        Object[] result = query.getSingleResult();
        System.out.println("MIN : " + result[0]);
        System.out.println("MAX : " + result[1]);
        System.out.println("AVERAGE : " + result[2]);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void aggregateFunctionGroupByAndHaving(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery(
                "select b.id, min(p.price), max(p.price), avg(p.price) from Product p " +
                        "join p.brand b group by b.id having min(p.price) > :min", Object[].class);
        query.setParameter("min", 500_000L);

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
    void nativeQuery(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query nativeQuery = entityManager.createNativeQuery("select * from brands", Brand.class);

        List<Brand> brands = nativeQuery.getResultList();

        for (Brand brand : brands) {
            System.out.println(brand.getId()+ " : " + brand.getName());
        }


        entityTransaction.commit();
        entityManager.close();
    }
    @Test
    void nativeNamedQuery(){
        EntityManagerFactory entitiyManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entitiyManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createNamedQuery("Brand.native.findAll", Brand.class);
        List<Brand> brands = query.getResultList();

        for (Brand brand : brands) {
            System.out.println(brand.getId() +" : " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

}




















