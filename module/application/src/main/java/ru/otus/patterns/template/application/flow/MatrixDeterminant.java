package ru.otus.patterns.template.application.flow;

import ru.otus.patterns.template.MatrixOperations;
import ru.otus.patterns.template.application.flow.exception.OperationIsNotViable;
import ru.otus.patterns.template.domain.Matrix;
import ru.otus.patterns.template.writer.impl.OutputStreamWriter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixDeterminant extends OperationFlow {
    private static final String ELEMENT_DELIMITER = ", ";

    private List<Integer> result;

    public MatrixDeterminant(MatrixOperations operations) {
        super(operations);
    }

    @Override
    public void write(String pathToFile) throws IOException {
        OutputStreamWriter streamWriter = new OutputStreamWriter(pathToFile);
        streamWriter.write(result.stream().map(String::valueOf).collect(Collectors.joining(ELEMENT_DELIMITER)).getBytes());
    }

    @Override
    public void process() {
        result = super.onProcess.stream().map(super.operations::determinant).collect(Collectors.toList());
    }

    @Override
    public void checkOperationAvailability() {
        super.checkOperationAvailability();

        for (int i = 0; i < onProcess.size(); i++) {
            Matrix next = onProcess.get(i);

            if (next.getRows().size() != next.getRows().get(0).size()) {
                throw new OperationIsNotViable(String.format("Matrix row count must be equal column count! Matrix position: %d ; Row count: %d ; Column count: %d",
                        i, next.getRows().size(), next.getRows().get(0).size()));
            }
        }
    }
}
