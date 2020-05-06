package tk.aizydorczyk.sns.search.infrastructure.jpa;

import tk.aizydorczyk.sns.common.infrastructure.utils.ClassUtils;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EntityTypesProvider {

    private final List<? extends Class<?>> allEntityTypes;

    public EntityTypesProvider(String basePackage) {
        final List<? extends Class<?>> allEntityTypes = ClassUtils.findAssignableTypes(Objects.requireNonNull(basePackage), BaseSearchEntity.class).stream()
                .map(ClassUtils::loadClass).filter(clazz -> clazz.isAnnotationPresent(Entity.class) || clazz.isAnnotationPresent(MappedSuperclass.class))
                .collect(Collectors.toList());

        if (allEntityTypes.isEmpty()) {
            throw new IllegalStateException(String.format("No declared %s or %s annotated classes", Entity.class.getCanonicalName(), MappedSuperclass.class.getCanonicalName()));
        }

        this.allEntityTypes = allEntityTypes;
    }

    public List<? extends Class<?>> getAllEntityTypes() {
        return allEntityTypes;
    }
}

