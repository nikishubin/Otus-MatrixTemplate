package ru.otus.patterns.template.application.adapter.input.exeption;

public final class FileIsEmptyException extends RuntimeException {

    public FileIsEmptyException(String message) {
        super(message);
    }
}
