package tk.aizydorczyk.sns.common.infrastructure.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.aizydorczyk.sns.common.infrastructure.utils.ClassUtils;

import java.util.List;

@Configuration
public class MapperConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapperConfiguration.class);

    private List<Converter> converters;

    public MapperConfiguration(List<Converter> converters) {
        this.converters = converters;
    }

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        for (String assignableType : ClassUtils.findAssignableTypes("tk.aizydorczyk.sns.operation", MappedEntity.class)) {
            final MappedEntity mappedEntity = ClassUtils.newInstance(assignableType, MappedEntity.class);

            modelMapper.createTypeMap(mappedEntity.getClass(), mappedEntity.targetType())
                    .setCondition(Conditions.isNotNull())
                    .setPropertyCondition(Conditions.isNotNull());

            LOGGER.info("Registered type map: '{}'", mappedEntity.getClass().getName());
        }
        registerConverters(modelMapper);
        return modelMapper;
    }

    private void registerConverters(ModelMapper modelMapper) {
        for (Converter converter : converters) {
            registerConverter(modelMapper, converter);
        }
    }

    private void registerConverter(ModelMapper modelMapper, Converter converter) {
        modelMapper.addConverter(converter);
        LOGGER.info("Registered converter: '{}'", converter.getClass().getName());
    }

    @Bean
    public Mapper mapper(ModelMapper modelMapper) {
        return modelMapper::map;
    }

}
