package ru.otus.patterns.template.port;

import ru.otus.patterns.template.domain.Matrix;

import java.io.IOException;
import java.util.List;

public interface MatrixInput {

    Matrix read() throws IOException;

    List<Matrix> readAll() throws IOException;
}
