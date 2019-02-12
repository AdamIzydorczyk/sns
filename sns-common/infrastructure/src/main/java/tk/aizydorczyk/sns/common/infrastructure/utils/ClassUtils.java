package tk.aizydorczyk.sns.common.infrastructure.utils;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

public final class ClassUtils {

    private ClassUtils() {
        throw new AssertionError();
    }

    public static Class<?> fetchGenericTypeOfClass(Class<?> classToFetchGenericType, Class<?> superClass, int position){
        Objects.requireNonNull(classToFetchGenericType);
        Objects.requireNonNull(superClass);

        final int genericArgCount = ClassUtils.getTypeArgumentsCount(superClass);
        final Class<?>[] generics = ClassUtils.resolveTypeArguments(classToFetchGenericType, superClass, genericArgCount);
        return generics[position];
    }

    private static int getTypeArgumentsCount(Class<?> clazz) {
        return ResolvableType.forClass(clazz).getGenerics().length;
    }

    private static Class<?>[] resolveTypeArguments(Class<?> clazz,
                                                  Class<?> genericIfc,
                                                  int genericCount) {
        Class<?>[] generics = resolveTypeArguments(clazz, genericIfc);
        if (isNull(generics)) {
            throw new IllegalStateException("Failed to resolve type arguments of non-generic/unresolvable class '" + clazz.getName() + "'.");
        }

        if (generics.length != genericCount) {
            throw new IllegalStateException("Failed to resolve type arguments. Expected: " + genericCount + ", found: " + generics.length + ".");
        }

        return generics;
    }

    private static Class<?>[] resolveTypeArguments(Class<?> clazz,
                                                  Class<?> genericIfc) {
        return GenericTypeResolver.resolveTypeArguments(clazz, genericIfc);
    }

    public static List<String> findAssignableTypes(String basePackage,
                                                   Class<?> targetType) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(targetType));
        scanner.addExcludeFilter(new NonConcreteTypeFilter());

        return scanner.findCandidateComponents(basePackage)
                .stream()
                .map(BeanDefinition::getBeanClassName)
                .distinct()
                .collect(toList());
    }

    private static class NonConcreteTypeFilter extends AbstractClassTestingTypeFilter {
        @Override
        protected boolean match(ClassMetadata metadata) {
            return !metadata.isConcrete();
        }
    }

    public static <TargetType> TargetType newInstance(String className,
                                                      Class<TargetType> targetType) {
        try {
            Class<?> clazz = ClassUtils.class.getClassLoader().loadClass(className);
            return targetType.cast(newInstance(clazz));
        } catch (ClassNotFoundException | ClassCastException e) {
            throw new IllegalStateException("Failed to create a new instance of the provided class", e);
        }
    }

    private static <TargetType> TargetType newInstance(Class<TargetType> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException("Failed to create a new instance", e);
        }
    }
}
