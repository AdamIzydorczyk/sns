package tk.aizydorczyk.sns.common.converter;

import org.modelmapper.AbstractConverter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

public class LongToLocalDateTimeConverter extends AbstractConverter<Long, LocalDateTime> {

    @Override
    protected LocalDateTime convert(Long source) {
        return Optional.ofNullable(source)
                .map(number -> LocalDateTime.ofEpochSecond(number, 0, ZoneOffset.UTC))
                .orElse(null);
    }
}