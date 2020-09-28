package ru.otus.patterns.template.application.adapter.input.exeption;

public final class DelimiterIsNotValidException extends RuntimeException {

    public DelimiterIsNotValidException(String message) {
        super(message);
    }
}
