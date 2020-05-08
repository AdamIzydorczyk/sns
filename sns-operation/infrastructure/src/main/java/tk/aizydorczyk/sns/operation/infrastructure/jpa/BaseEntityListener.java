package tk.aizydorczyk.sns.operation.infrastructure.jpa;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class BaseEntityListener {
    @PrePersist
    public void preCreate(BaseEntity entity) {
        entity.auditCreate();
    }

    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        entity.auditUpdate();
    }
}
