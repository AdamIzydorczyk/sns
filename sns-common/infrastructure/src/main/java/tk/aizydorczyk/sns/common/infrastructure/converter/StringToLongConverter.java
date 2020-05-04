package tk.aizydorczyk.sns.common.infrastructure.converter;

import org.modelmapper.AbstractConverter;

import java.util.Optional;

public class StringToLongConverter extends AbstractConverter<String, Long> {
    @Override
    protected Long convert(String source) {
        return Optional.ofNullable(source)
                .map(Long::valueOf)
                .orElse(null);
    }
}
