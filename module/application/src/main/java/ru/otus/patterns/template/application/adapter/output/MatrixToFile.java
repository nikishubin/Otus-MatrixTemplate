package ru.otus.patterns.template.application.adapter.output;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.patterns.template.domain.Matrix;
import ru.otus.patterns.template.port.MatrixOutput;
import ru.otus.patterns.template.writer.FileWriter;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class MatrixToFile implements MatrixOutput {
    private final Logger log = LogManager.getLogger(MatrixToFile.class);

    private static final String ELEMENT_DELIMITER = "\n\n";

    private final FileWriter fileWriter;

    public MatrixToFile(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    @Override
    public void write(Matrix matrix) throws IOException {
        fileWriter.write(matrix.toString().getBytes());
        log.info("Matrix is successfully written to file!");
    }

    @Override
    public void write(Collection<Matrix> matrix) throws IOException {
        fileWriter.write(matrix.stream().map(Matrix::toString).collect(Collectors.joining(ELEMENT_DELIMITER)).getBytes());
        log.info("Matrix is successfully written to file!");
    }
}
