package programmerzamannow.jpa.entity;

import java.time.LocalDateTime;

public interface CreatedAtAware {
    void setCreatedAt(LocalDateTime localDateTime);
}
