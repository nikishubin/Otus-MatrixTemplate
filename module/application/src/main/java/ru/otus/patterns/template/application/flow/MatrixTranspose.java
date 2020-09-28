package ru.otus.patterns.template.application.flow;

import ru.otus.patterns.template.MatrixOperations;
import ru.otus.patterns.template.application.adapter.output.MatrixToFile;
import ru.otus.patterns.template.domain.Matrix;
import ru.otus.patterns.template.writer.impl.OutputStreamWriter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixTranspose extends OperationFlow {

    private List<Matrix> result;

    public MatrixTranspose(MatrixOperations operations) {
        super(operations);
    }

    @Override
    public void write(String pathToFile) throws IOException {
        MatrixToFile adapter = new MatrixToFile(new OutputStreamWriter(pathToFile));
        adapter.write(result);
    }

    @Override
    public void process() {
        result = super.onProcess.stream().map(super.operations::transpose).collect(Collectors.toList());
    }
}
