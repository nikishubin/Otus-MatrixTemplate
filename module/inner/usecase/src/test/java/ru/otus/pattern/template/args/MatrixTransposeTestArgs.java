package ru.otus.pattern.template.args;

import org.junit.jupiter.params.provider.Arguments;
import ru.otus.patterns.template.domain.Matrix;

import java.util.stream.Stream;

public class MatrixTransposeTestArgs {

    public static Stream<Arguments> matrixTransposeData() {
        return Stream.of(
                Arguments.arguments(
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
                                .addRow(1, 1, 1)
                                .addRow(2, 2, 2)
                                .create(),
                        Matrix.newInstance()
                                .addRow(1, 2)
                                .addRow(1, 2)
                                .addRow(1, 2)
                                .create()
                ),
                Arguments.arguments(
                        Matrix.newInstance()
                                .addRow(1, 2)
                                .addRow(1, 2)
                                .addRow(1, 2)
                                .create(),
                        Matrix.newInstance()
                                .addRow(1, 1, 1)
                                .addRow(2, 2, 2)
                                .create()
                ),
                Arguments.arguments(
                        Matrix.newInstance()
                                .addRow(-1)
                                .create(),
                        Matrix.newInstance()
                                .addRow(-1)
                                .create()
                )
        );
    }
}
