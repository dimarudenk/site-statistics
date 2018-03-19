package ru.drudenko.sitestatistics.util;

import javax.ws.rs.*;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;

/**
 * @author drudenko
 */
@Provider
public final class LocalDateTimeConverterProvider implements ParamConverterProvider {

    private final DateTimeFormatter dateTimeFormatter;

    public LocalDateTimeConverterProvider(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = requireNonNull(dateTimeFormatter, "Date formatter is required.");
    }

    public LocalDateTimeConverterProvider(String pattern) {
        this(DateTimeFormatter.ofPattern(pattern));
    }

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawClass, Type genericType, Annotation[] annotations) {
        if (rawClass.isAssignableFrom(LocalDateTime.class)) {
            String paramName = null;
            for (Annotation annotation : annotations) {
                if (annotation instanceof PathParam) {
                    paramName = ((PathParam) annotation).value();
                } else if (annotation instanceof FormParam) {
                    paramName = ((FormParam) annotation).value();
                } else if (annotation instanceof HeaderParam) {
                    paramName = ((HeaderParam) annotation).value();
                } else if (annotation instanceof QueryParam) {
                    paramName = ((QueryParam) annotation).value();
                } else if (annotation instanceof MatrixParam) {
                    paramName = ((MatrixParam) annotation).value();
                } else if (annotation instanceof CookieParam) {
                    paramName = ((CookieParam) annotation).value();
                }
                if (paramName != null) {
                    break;
                }
            }
            return (ParamConverter<T>) new LocalDateTimeConverter(dateTimeFormatter, paramName);
        } else {
            return null;
        }
    }

    /**
     * @author drudenko
     */
    static final class LocalDateTimeConverter implements ParamConverter<LocalDateTime> {
        private final DateTimeFormatter dateTimeFormatter;
        private final String paramName;

        LocalDateTimeConverter(DateTimeFormatter dateTimeFormatter, String paramName) {
            this.dateTimeFormatter = dateTimeFormatter;
            this.paramName = paramName;
        }

        @Override
        public LocalDateTime fromString(String dateString) {
            try {
                return dateTimeFormatter.parse(dateString, LocalDateTime::from);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Can't convert parameter: '" + paramName + "'. Reason: " + e.getMessage(), e);
            }
        }

        @Override
        public String toString(LocalDateTime dateToConvert) {
            return dateToConvert.format(dateTimeFormatter);
        }
    }
}
