package spring.core.csv.reader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class CsvFileReader<T> {
    private static final Logger LOGGER = LogManager.getLogger(CsvFileReader.class);

    public List<T> readCsvFile(InputStream inputStream) {
        Reader fileReader = null;
        CSVParser csvFileParser = null;
        CSVFormat csvFileFormat = CSVFormat.newFormat(getCSVDelimiter()).withHeader(getCSVHeaderArray());
        try {
            fileReader = new InputStreamReader(inputStream);
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
            List<CSVRecord> csvRecords = csvFileParser.getRecords();
            return readInternal(csvRecords);
        } catch (Exception e) {
            LOGGER.error("Error in CsvFileReader !!!", e);
        } finally {
            try {
                fileReader.close();
                csvFileParser.close();
            } catch (IOException e) {
                LOGGER.error("Error while closing fileReader/csvFileParser !!!", e);
            }
        }

        return Collections.emptyList();
    }

    protected abstract List<T> readInternal(List<CSVRecord> csvRecords);

    protected abstract String[] getCSVHeaderArray();

    protected abstract char getCSVDelimiter();

    protected abstract String getCSVArrayDelimiter();


}