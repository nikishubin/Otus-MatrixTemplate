package ru.otus.patterns.template.writer;

import java.io.IOException;

public interface FileWriter {

    void write(byte[] target) throws IOException;
}
