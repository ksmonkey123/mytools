package ch.awae.mytools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

public class ResourceNotFoundException extends ResponseStatusException {

    public ResourceNotFoundException(String resource, String key, String value) {
        super(HttpStatus.NOT_FOUND, resource + "not found for " + key + "=" + value);
    }

    public static Supplier<ResourceNotFoundException> byKey(String resource, String key, String value) {
        return () -> new ResourceNotFoundException(resource, key, value);
    }

    public static Supplier<ResourceNotFoundException> byId(String resource, long value) {
        return () -> new ResourceNotFoundException(resource, "id", Long.toString(value));
    }

}
