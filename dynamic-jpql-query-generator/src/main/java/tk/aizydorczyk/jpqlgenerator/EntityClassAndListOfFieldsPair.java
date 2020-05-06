package tk.aizydorczyk.jpqlgenerator;

import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class EntityClassAndListOfFieldsPair {
    private final Class<?> entityClass;
    private final List<Field> fields;

    private EntityClassAndListOfFieldsPair(Class<?> entityClass, List<Field> fields) {
        this.entityClass = entityClass;
        this.fields = fields;
    }

    EntityClassAndListOfFieldsPair(Class<?> entityClass) {
        final List<Field> fields = new ArrayList<>(List.of(entityClass.getDeclaredFields()));
        fetchSuperClassFields(entityClass.getSuperclass(), fields);
        this.entityClass = entityClass;
        this.fields = fields;
    }

    private void fetchSuperClassFields(Class<?> superclass, List<Field> fields) {
        if (superclass.isAnnotationPresent(MappedSuperclass.class)) {
            final Field[] declaredFields = superclass.getDeclaredFields();
            fields.addAll(List.of(declaredFields));
            fetchSuperClassFields(superclass.getSuperclass(), fields);
        }
    }

    Class<?> getEntityClass() {
        return entityClass;
    }

    List<Field> getFields() {
        return fields;
    }
}
