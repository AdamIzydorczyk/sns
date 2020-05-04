package tk.aizydorczyk.sns.common.infrastructure.converter;

import org.modelmapper.AbstractConverter;

import java.util.Optional;
import java.util.UUID;

public class StringToUuidConverter extends AbstractConverter<String, UUID> {
    @Override
    protected UUID convert(String source) {
        return Optional.ofNullable(source)
                .map(UUID::fromString)
                .orElse(null);
    }
}
