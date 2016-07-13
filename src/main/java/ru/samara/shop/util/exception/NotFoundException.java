package ru.samara.shop.util.exception;

/**
 * Created by user on 13.07.2016.
 */

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No data found") //494
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
