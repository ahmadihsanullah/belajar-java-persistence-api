package programmerzamannow.jpa.listener;

import java.time.LocalDateTime;

import jakarta.persistence.PrePersist;
import programmerzamannow.jpa.entity.CreatedAtAware;

public class CreatedAtListener {
    @PrePersist
    void setCreatedAt(CreatedAtAware object){
        object.setCreatedAt(LocalDateTime.now());
    }
}
