package tk.aizydorczyk.sns.common.converter;

import org.modelmapper.AbstractConverter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.function.Function;

public class StringToLocalDateTimeConverter extends AbstractConverter<String, LocalDateTime> {

    private final Function<String, Long> mapStringToLong;

    StringToLocalDateTimeConverter(Function<String, Long> mapStringToLong) {
        this.mapStringToLong = mapStringToLong;
    }

    @Override
    protected LocalDateTime convert(String source) {
        return Optional.ofNullable(source)
                .map(mapStringToLong)
                .map(number -> LocalDateTime.ofEpochSecond(number, 0, ZoneOffset.UTC))
                .orElse(null);
    }
}
