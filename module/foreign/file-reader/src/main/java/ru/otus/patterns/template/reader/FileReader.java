package ru.otus.patterns.template.reader;

import java.io.IOException;

public interface FileReader {

    byte[] read(String path) throws IOException;
}
