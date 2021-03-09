package ch.awae.mytools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {

    public ResourceNotFoundException(String resource, String key, String value) {
        super(HttpStatus.NOT_FOUND, resource + "not found for " + key + "=" + value);
    }

}
