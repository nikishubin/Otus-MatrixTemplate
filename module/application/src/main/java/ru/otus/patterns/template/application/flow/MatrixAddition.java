package ru.otus.patterns.template.application.flow;

import ru.otus.patterns.template.MatrixOperations;
import ru.otus.patterns.template.application.adapter.output.MatrixToFile;
import ru.otus.patterns.template.application.flow.exception.OperationIsNotViable;
import ru.otus.patterns.template.domain.Matrix;
import ru.otus.patterns.template.writer.impl.OutputStreamWriter;

import java.io.IOException;

public class MatrixAddition extends OperationFlow {

    private Matrix result;

    public MatrixAddition(MatrixOperations operations) {
        super(operations);
    }

    @Override
    public void write(String pathToFile) throws IOException {
        MatrixToFile adapter = new MatrixToFile(new OutputStreamWriter(pathToFile));
        adapter.write(result);
    }

    @Override
    public void process() {
        result = super.onProcess.get(0);

        for (int i = 1; i < super.onProcess.size(); i++) {
            result = super.operations.addition(result, super.onProcess.get(i));
        }
    }

    @Override
    public void checkOperationAvailability() {
        super.checkOperationAvailability();

        Matrix first = super.onProcess.get(0);
        for (int i = 1; i < super.onProcess.size(); i++) {
            Matrix next = super.onProcess.get(i);

            if (first.getRows().size() != next.getRows().size() || first.getRows().get(0).size() != next.getRows().get(0).size()) {
                throw new OperationIsNotViable("Addition elements must be of the same dimension!");
            }
        }
    }
}
