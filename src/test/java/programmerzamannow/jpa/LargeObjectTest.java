package programmerzamannow.jpa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import programmerzamannow.jpa.entity.Image;
import programmerzamannow.jpa.util.JpaUtil;

public class LargeObjectTest {
    
    @Test
    void testLargeObject() throws IOException{
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Image image = new Image();
        image.setName("teems");
        image.setDescription("zoom kelas pagi");
        byte[] bytes = Files.readAllBytes(Path.of(getClass().getResource("/images/img.png").getPath()));
        image.setImage(bytes);

        entityManager.persist(image);

        entityTransaction.commit();
        entityManager.close();
    }
}
