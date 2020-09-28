package ru.otus.patterns.template.application.adapter.input;

import ru.otus.patterns.template.application.adapter.input.validator.InputFileValidator;
import ru.otus.patterns.template.domain.Matrix;
import ru.otus.patterns.template.port.MatrixInput;
import ru.otus.patterns.template.reader.FileReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileToMatrix implements MatrixInput {
    private static final String DEFAULT_ELEMENT_DELIMITER = "([\\s,.;]+)";
    private static final String DEFAULT_ROW_DELIMITER = "(\\n)";
    private static final String DEFAULT_OBJECT_DELIMITER = "\\n\\s*\\n";

    private final String pathToFile;
    private final FileReader fileReader;

    private final String elementDelimiter;
    private final String rowDelimiter;
    private final String objectDelimiter;

    public FileToMatrix(String pathToFile, FileReader fileReader) {
        this.pathToFile = pathToFile;
        this.fileReader = fileReader;
        this.elementDelimiter = DEFAULT_ELEMENT_DELIMITER;
        this.rowDelimiter = DEFAULT_ROW_DELIMITER;
        this.objectDelimiter = DEFAULT_OBJECT_DELIMITER;
    }

    public FileToMatrix(String pathToFile, String elementDelimiterRegex, String rowDelimiterRegex, String objectDelimiterRegex, FileReader fileReader) {
        this.pathToFile = pathToFile;
        this.elementDelimiter = elementDelimiterRegex;
        this.rowDelimiter = rowDelimiterRegex;
        this.objectDelimiter = objectDelimiterRegex;
        this.fileReader = fileReader;
    }

    @Override
    public Matrix read() throws IOException {
        String source = getSourceFromFile();
        InputFileValidator.checkDelimiterIsCorrect(source, objectDelimiter);

        return getMatrixFromUnparsedString(source.split(objectDelimiter)[0]);
    }

    @Override
    public List<Matrix> readAll() throws IOException {
        String source = getSourceFromFile();
        InputFileValidator.checkDelimiterIsCorrect(source, objectDelimiter);

        return Arrays.stream(source.split(objectDelimiter))
                .map(this::getMatrixFromUnparsedString)
                .collect(Collectors.toList());
    }

    private String getSourceFromFile() throws IOException {
        String source = new String(fileReader.read(pathToFile), Charset.defaultCharset());
        InputFileValidator.checkFileIsEmpty(source);
        return source;
    }

    private Matrix getMatrixFromUnparsedString(String source) {
        Matrix.Builder builder = Matrix.newInstance();
        Arrays.stream(source.split(rowDelimiter)).forEach(row -> {
            Integer[] elements = Arrays.stream(row.split(elementDelimiter)).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
            builder.addRow(elements);
        });
        return builder.create();
    }
}
