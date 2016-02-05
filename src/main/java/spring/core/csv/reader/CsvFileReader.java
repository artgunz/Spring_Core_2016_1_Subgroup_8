package spring.core.csv.reader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public abstract class CsvFileReader<T> {

    public List<T> readCsvFile(File file) {
        FileReader fileReader = null;
        CSVParser csvFileParser = null;
        CSVFormat csvFileFormat = CSVFormat.newFormat(getCSVDelimiter()).withHeader(getCSVHeaderArray());
        try {
            fileReader = new FileReader(file);
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
            List<CSVRecord> csvRecords = csvFileParser.getRecords();
            return readInternal(csvRecords);
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
        } finally {
            try {
                fileReader.close();
                csvFileParser.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader/csvFileParser !!!");
            }
        }

        return Collections.emptyList();
    }

    protected abstract List<T> readInternal(List<CSVRecord> csvRecords);

    protected abstract String[] getCSVHeaderArray();

    protected abstract char getCSVDelimiter();

    protected abstract String getCSVArrayDelimiter();


}