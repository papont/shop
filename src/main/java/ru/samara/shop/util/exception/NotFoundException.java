package ru.samara.shop.util.exception;

/**
 * @author papont
 * @date 13.11.16.
 */
//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No data found") //494
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
