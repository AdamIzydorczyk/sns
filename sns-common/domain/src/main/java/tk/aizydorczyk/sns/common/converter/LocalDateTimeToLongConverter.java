package tk.aizydorczyk.sns.common.converter;

import org.modelmapper.AbstractConverter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

public class LocalDateTimeToLongConverter extends AbstractConverter<LocalDateTime, Long> {
    @Override
    protected Long convert(LocalDateTime source) {
        return Optional.ofNullable(source)
                .map(time -> time.toEpochSecond(ZoneOffset.UTC))
                .orElse(null);
    }
}
