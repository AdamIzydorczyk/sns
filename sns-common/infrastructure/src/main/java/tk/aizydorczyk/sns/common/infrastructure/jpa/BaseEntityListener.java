package tk.aizydorczyk.sns.common.infrastructure.jpa;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class BaseEntityListener {
    @PrePersist
    private void publishCreateEvent(BaseEntity entity) {
        entity.auditCreate();
    }

    @PreUpdate
    private void publishUpdateEvent(BaseEntity entity) {
        entity.auditUpdate();
    }
}
