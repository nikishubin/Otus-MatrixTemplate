package ru.otus.patterns.template.impl;

import ru.otus.patterns.template.MatrixOperations;
import ru.otus.patterns.template.domain.Matrix;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SyncMatrixOperations implements MatrixOperations {

    @Override
    public int determinant(Matrix target) {
        int result = 0;

        if (target.getRows().size() == 1) {
            result = target.getRows().get(0).get(0);
            return (result);
        }

        if (target.getRows().size() == 2) {
            result = ((target.getRows().get(0).get(0) * target.getRows().get(1).get(1)) - (target.getRows().get(0).get(1) * target.getRows().get(1).get(0)));
            return (result);
        }

        for (int i = 0; i < target.getRows().get(0).size(); i++) {
            var temporary = IntStream.range(0, target.getRows().size() - 1).boxed()
                    .map(element -> IntStream.range(0, target.getRows().get(0).size() - 1).boxed().collect(Collectors.toList()))
                    .collect(Collectors.toList());

            for (int rowCounter = 1; rowCounter < target.getRows().size(); rowCounter++) {
                for (int colCounter = 0; colCounter < target.getRows().get(0).size(); colCounter++) {
                    if (colCounter < i) {
                        temporary.get(rowCounter - 1).set(colCounter, target.getRows().get(rowCounter).get(colCounter));
                    } else if (colCounter > i) {
                        temporary.get(rowCounter - 1).set(colCounter - 1, target.getRows().get(rowCounter).get(colCounter));
                    }
                }
            }

            Matrix.Builder temporaryWrapper = Matrix.newInstance();
            temporary.forEach(promisedRow -> temporaryWrapper.addRow(promisedRow.toArray(Integer[]::new)));
            result += target.getRows().get(0).get(i) * Math.pow(-1, i) * determinant(temporaryWrapper.create());
        }

        return result;
    }

    @Override
    public Matrix transpose(Matrix target) {
        Matrix.Builder result = Matrix.newInstance();

        int rowCount = target.getRows().size();
        int colCount = target.getRows().get(0).size();

        for (int row = 0; row < colCount; row++) {
            List<Integer> next = IntStream.range(0, rowCount).boxed().collect(Collectors.toList());
            for (int col = 0; col < rowCount; col++) {
                Integer element = target.getRows().get(col).get(row);
                next.set(col, element);
            }
            result.addRow(next.toArray(Integer[]::new));
        }

        return result.create();
    }

    @Override
    public Matrix addition(Matrix first, Matrix second) {
        Matrix.Builder result = Matrix.newInstance();

        int rowCount = first.getRows().size();
        int colCount = first.getRows().get(0).size();

        for (int row = 0; row < rowCount; row++) {
            List<Integer> next = IntStream.range(0, colCount).boxed().collect(Collectors.toList());
            for (int col = 0; col < colCount; col++) {
                Integer firstElement = first.getRows().get(row).get(col);
                Integer secondElement = second.getRows().get(row).get(col);
                next.set(col, firstElement + secondElement);
            }
            result.addRow(next.toArray(Integer[]::new));
        }

        return result.create();
    }

}
