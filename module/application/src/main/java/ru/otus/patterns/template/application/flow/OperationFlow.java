package ru.otus.patterns.template.application.flow;

import ru.otus.patterns.template.MatrixOperations;
import ru.otus.patterns.template.application.adapter.input.FileToMatrix;
import ru.otus.patterns.template.application.flow.exception.OperationIsNotViable;
import ru.otus.patterns.template.domain.Matrix;
import ru.otus.patterns.template.reader.impl.FileStreamReader;

import java.io.IOException;
import java.util.List;

public abstract class OperationFlow {

    protected List<Matrix> onProcess;

    protected final MatrixOperations operations;

    public OperationFlow(MatrixOperations operations) {
        this.operations = operations;
    }

    public void read(String pathToFile) throws IOException {
        FileToMatrix adapter = new FileToMatrix(pathToFile, new FileStreamReader());
        onProcess = adapter.readAll();
    }

    public void checkOperationAvailability() {
        if (onProcess.isEmpty()) {
            throw new OperationIsNotViable("Input is empty!");
        }
    }

    public abstract void write(String pathToFile) throws IOException;

    public abstract void process();
}
