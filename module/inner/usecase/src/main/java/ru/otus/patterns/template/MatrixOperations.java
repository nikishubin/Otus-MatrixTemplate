package ru.otus.patterns.template;

import ru.otus.patterns.template.domain.Matrix;

public interface MatrixOperations {

    int determinant(Matrix target);
    Matrix transpose(Matrix target);
    Matrix addition(Matrix first, Matrix second);
}
