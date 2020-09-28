# Otus-MatrixTemplate

###Project Modules:
- application
- domain
- usecase
- file-reader
- file-writer

---

Flow:
1. Get matrix operation type from arg[0];
2. Read input data from a file passed to arg[1];
3. Write result to a file passed to arg[2].

Supported matrix operation types:
1. addition;
2. transpose;
3. determinant.

Command:

`gradle clean application:run --args="determinant D:\\Projects\\Otus\\template_input.txt D:\\Projects\\Otus\\template_output.txt"`

---

Used technologies:
- Java 14 modular without experimental functionality
- Gradle 6.5
- jUnit 5 (Jupiter)