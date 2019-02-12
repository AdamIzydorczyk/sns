package tk.aizydorczyk.sns.common.infrastructure.mapper;

@FunctionalInterface
public interface Mapper {
    <DestinationType> DestinationType map(Object source, Class<DestinationType> destinationType);
}
