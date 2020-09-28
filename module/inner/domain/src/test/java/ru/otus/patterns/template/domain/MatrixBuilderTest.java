package ru.otus.patterns.template.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.stream.Collectors;

class MatrixBuilderTest {

    @Test
    void whenMatrixRowIsEmptyThenThrowIllegalArgumentException() {
        Matrix.Builder builder = Matrix.newInstance();
        Assertions.assertThatThrownBy(builder::addRow).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMatrixRowsSizeIsDifferentThenThrowIllegalArgumentException() {
        Matrix.Builder builder = Matrix.newInstance();
        builder.addRow(1, 1);
        Assertions.assertThatThrownBy(() -> builder.addRow(1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMatrixCreationCorrectThenReturn() {
        Matrix expected = Matrix.newInstance()
                .addRow(1, 1)
                .addRow(1, 1)
                .create();

        Matrix current = Matrix.newInstance()
                .addRow(1, 1)
                .addRow(1, 1)
                .create();

        Assertions.assertThat(expected.getRows().stream().flatMap(Collection::stream).collect(Collectors.toList()))
                .containsExactly(current.getRows().stream().flatMap(Collection::stream).toArray(Integer[]::new));
    }
}
