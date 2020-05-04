package tk.aizydorczyk.sns.operation.infrastructure.jpa;

import tk.aizydorczyk.sns.common.infrastructure.mapper.MappedEntity;
import tk.aizydorczyk.sns.common.infrastructure.mapper.Mapper;
import tk.aizydorczyk.sns.common.infrastructure.utils.ClassUtils;
import tk.aizydorczyk.sns.operation.infrastructure.rest.BaseDto;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.Objects.isNull;

@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
@SequenceGenerator(name = "default_generator", sequenceName = BaseEntity.SEQUENCE_GENERATOR)
public abstract class BaseEntity<TargetDtoType extends BaseDto> implements MappedEntity {

    static final String SEQUENCE_GENERATOR = "DefaultSeqGen";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    private Long id;

    @Column(nullable = false)
    private boolean deleted = false;

    @Version
    @Column(columnDefinition = "bigint NOT NULL DEFAULT 0", nullable = false)
    private long version = 0L;

    @NotNull
    private UUID createdBy;

    @NotNull
    private LocalDateTime createdAt;

    private UUID modifiedBy;

    private LocalDateTime modifiedAt;

    @Transient
    private LocalDateTime time;

    @Transient
    private UUID userUuid;

    protected BaseEntity() {
    }

    protected BaseEntity(TargetDtoType dto, Mapper mapper) {
        applyDto(dto, mapper);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<TargetDtoType> targetType() {
        return (Class<TargetDtoType>) ClassUtils.fetchGenericTypeOfClass(getClass(), BaseEntity.class, 0);
    }

    public abstract void applyDto(TargetDtoType postDto, Mapper mapper);

    void auditCreate() {
        validateTimeAndUser();

        this.createdBy = userUuid;
        this.createdAt = time;
    }

    void auditUpdate() {
        validateTimeAndUser();

        this.modifiedBy = userUuid;
        this.modifiedAt = time;
    }

    private void validateTimeAndUser() {
        if (isNull(time)) {
            throw new IllegalStateException("Time must be initialized");
        }

        if (isNull(userUuid)) {
            throw new IllegalStateException("User must be initialized");
        }
    }

    public void applyTimeAndUser(LocalDateTime time,
                                 UUID userUuid) {
        this.time = time;
        this.userUuid = userUuid;
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public UUID getModifiedBy() {
        return modifiedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
