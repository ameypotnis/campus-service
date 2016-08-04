package org.campus.web.helper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by amey on 4/8/16.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class BadRequestException extends RuntimeException {
    public BadRequestException(String field) {
        super(field);
    }
}

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException extends RuntimeException {
    ResourceNotFoundException() {
        super("resource not found");
    }
}

public class Preconditions {
    public static <T> T checkNotNull(final T resource, String field) {
        if (resource == null) {
            throw new BadRequestException(String.format("%s should not be null", field));
        }
        return resource;
    }

    public static <T> T checkFound(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException();
        }
        return resource;
    }
}
