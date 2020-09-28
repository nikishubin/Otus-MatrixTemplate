module matrix.template.application {

    requires org.apache.logging.log4j;

    requires matrix.template.domain;
    requires matrix.template.usecase;
    requires external.file.writer;
    requires external.file.reader;
}