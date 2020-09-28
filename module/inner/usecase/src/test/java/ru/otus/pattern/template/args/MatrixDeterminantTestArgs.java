package ru.otus.pattern.template.args;

import org.junit.jupiter.params.provider.Arguments;
import ru.otus.patterns.template.domain.Matrix;

import java.util.stream.Stream;

public class MatrixDeterminantTestArgs {

    public static Stream<Arguments> matrixDeterminantData() {
        return Stream.of(
                Arguments.arguments(
                        Matrix.newInstance()
                                .addRow(0, 0)
                                .addRow(0, 0)
                                .create(),
                        0
                ),
                Arguments.arguments(
                        Matrix.newInstance()
                                .addRow(4, 2, 1)
                                .addRow(2, 4, 2)
                                .addRow(1, 2, 4)
                                .create(),
                        36
                ),
                Arguments.arguments(
                        Matrix.newInstance()
                                .addRow(1, 0, 2, 2)
                                .addRow(0, 1, 0, 0)
                                .addRow(0, 0, 1, 0)
                                .addRow(2, 2, 0, 1)
                                .create(),
                        -3
                )
        );
    }
}
