package ru.otus.patterns.template.application.flow;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum OperationType {

    ADDITION,
    TRANSPOSE,
    DETERMINANT;

    private static final Map<String, OperationType> operations;

    static {
        operations = Arrays.stream(values()).collect(Collectors.toUnmodifiableMap(
                next -> next.name().toLowerCase(), Function.identity()
        ));
    }

    public static OperationType getOperation(String name) {
        return Optional.ofNullable(operations.get(name.toLowerCase()))
                .orElseThrow(() -> new NoSuchElementException("Selected operation is not supported!"));
    }
}
