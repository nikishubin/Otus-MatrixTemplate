package ru.otus.pattern.template.args;

import org.junit.jupiter.params.provider.Arguments;
import ru.otus.patterns.template.domain.Matrix;

import java.util.stream.Stream;

public class MatrixAdditionTestArgs {

    public static Stream<Arguments> matrixAdditionData() {
        return Stream.of(
                Arguments.arguments(
                        Matrix.newInstance()
                                .addRow(0, 0)
                                .addRow(0, 0)
                                .create(),
                        Matrix.newInstance()
                                .addRow(0, 0)
                                .addRow(0, 0)
                                .create(),
                        Matrix.newInstance()
                                .addRow(0, 0)
                                .addRow(0, 0)
                                .create()
                ),
                Arguments.arguments(
                        Matrix.newInstance()
                                .addRow(1, 1)
                                .addRow(2, 2)
                                .create(),
                        Matrix.newInstance()
                                .addRow(2, 2)
                                .addRow(1, 1)
                                .create(),
                        Matrix.newInstance()
                                .addRow(3, 3)
                                .addRow(3, 3)
                                .create()
                ),
                Arguments.arguments(
                        Matrix.newInstance()
                                .addRow(1, 0)
                                .addRow(0, 1)
                                .create(),
                        Matrix.newInstance()
                                .addRow(0, 1)
                                .addRow(1, 0)
                                .create(),
                        Matrix.newInstance()
                                .addRow(1, 1)
                                .addRow(1, 1)
                                .create()
                ),
                Arguments.arguments(
                        Matrix.newInstance()
                                .addRow(-1, 2)
                                .addRow(2, -1)
                                .create(),
                        Matrix.newInstance()
                                .addRow(1, -2)
                                .addRow(-2, 1)
                                .create(),
                        Matrix.newInstance()
                                .addRow(0, 0)
                                .addRow(0, 0)
                                .create()
                )
        );
    }
}
