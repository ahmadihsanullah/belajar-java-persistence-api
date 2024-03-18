package programmerzamannow.jpa.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static EntityManagerFactory entitiyManagerFactory = null;

    public static EntityManagerFactory getEntityManagerFactory(){
        if(entitiyManagerFactory == null){
            entitiyManagerFactory = Persistence.createEntityManagerFactory("BELAJAR");
        }
        return entitiyManagerFactory;
    }
}
