package ru.otus.patterns.template.application.flow.exception;

public class OperationIsNotViable extends RuntimeException {

    public OperationIsNotViable(String message) {
        super(message);
    }
}
