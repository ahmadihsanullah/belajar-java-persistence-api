package programmerzamannow.jpa.listener;

import java.time.LocalDateTime;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import programmerzamannow.jpa.entity.UpdateAtAware;


public class UpdatedAtListener {

    @PrePersist
    @PreUpdate
    public void setLastUpdatedAt(UpdateAtAware object) {
        object.setUpdatedAt(LocalDateTime.now());
    }
    
}
