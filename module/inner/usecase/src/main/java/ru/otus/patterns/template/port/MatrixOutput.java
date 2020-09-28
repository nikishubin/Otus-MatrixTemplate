package ru.otus.patterns.template.port;

import ru.otus.patterns.template.domain.Matrix;

import java.io.IOException;
import java.util.Collection;

public interface MatrixOutput {

    void write(Matrix matrix) throws IOException;

    void write(Collection<Matrix> matrix) throws IOException;
}
