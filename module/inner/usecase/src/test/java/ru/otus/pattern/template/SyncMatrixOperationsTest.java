package ru.otus.pattern.template;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.patterns.template.MatrixOperations;
import ru.otus.patterns.template.domain.Matrix;
import ru.otus.patterns.template.impl.SyncMatrixOperations;

import java.util.Collection;

class SyncMatrixOperationsTest {

    private MatrixOperations matrixOperations;

    @BeforeEach
    void setUp() {
        matrixOperations = new SyncMatrixOperations();
    }

    @ParameterizedTest
    @MethodSource("ru.otus.pattern.template.args.MatrixDeterminantTestArgs#matrixDeterminantData")
    void whenMatrixDeterminantIsAvailableThenGetResult(Matrix target, int expected) {
        int actual = matrixOperations.determinant(target);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("ru.otus.pattern.template.args.MatrixTransposeTestArgs#matrixTransposeData")
    void whenMatrixTransposeIsAvailableThenGetResult(Matrix target, Matrix expected) {
        Matrix actual = matrixOperations.transpose(target);

        Assertions.assertThat(actual.getRows().stream().flatMap(Collection::stream).toArray())
                .containsExactly(expected.getRows().stream().flatMap(Collection::stream).toArray());
    }

    @ParameterizedTest
    @MethodSource("ru.otus.pattern.template.args.MatrixAdditionTestArgs#matrixAdditionData")
    void whenMatrixAdditionIsAvailableThenGetResult(Matrix first, Matrix second, Matrix expected) {
        Matrix actual = matrixOperations.addition(first, second);

        Assertions.assertThat(actual.getRows().stream().flatMap(Collection::stream).toArray())
                .containsExactly(expected.getRows().stream().flatMap(Collection::stream).toArray());
    }
}
