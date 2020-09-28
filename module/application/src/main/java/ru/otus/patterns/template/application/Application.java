package ru.otus.patterns.template.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.patterns.template.MatrixOperations;
import ru.otus.patterns.template.application.flow.MatrixAddition;
import ru.otus.patterns.template.application.flow.MatrixDeterminant;
import ru.otus.patterns.template.application.flow.MatrixTranspose;
import ru.otus.patterns.template.application.flow.OperationFlow;
import ru.otus.patterns.template.application.flow.OperationType;
import ru.otus.patterns.template.impl.SyncMatrixOperations;

import java.io.IOException;

public class Application {
    private static final Logger log = LogManager.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        OperationType type = OperationType.getOperation(args[0]);
        String inputPath = args[1];
        String outputPath = args[2];

        run(type, inputPath, outputPath);
    }

    private static void run(OperationType type, String pathToInput, String pathToOutput) throws IOException {
        log.info("Selected operation: {}; Input path: {}; Output path: {}", type.name(), pathToInput, pathToOutput);

        OperationFlow flow = getFlowByOperationType(type);
        flow.read(pathToInput);
        flow.checkOperationAvailability();
        flow.process();
        flow.write(pathToOutput);
    }

    private static OperationFlow getFlowByOperationType(OperationType type) {
        MatrixOperations matrixOperations = new SyncMatrixOperations();
        return switch (type) {
            case ADDITION -> new MatrixAddition(matrixOperations);
            case TRANSPOSE -> new MatrixTranspose(matrixOperations);
            case DETERMINANT -> new MatrixDeterminant(matrixOperations);
        };
    }
}
