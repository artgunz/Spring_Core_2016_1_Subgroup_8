package spring.core.csv.reader.auditorium;

import spring.core.csv.reader.CsvFileReader;
import spring.core.data.Auditorium;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class AuditoriumCsvFileReader extends CsvFileReader<Auditorium> {
    private static final String[] FILE_HEADER_MAPPING = {"NAME", "SEATS", "VIP_SEATS"};

    protected List<Auditorium> readInternal(List<CSVRecord> csvRecords) {
        List<Auditorium> students = new ArrayList<>();

        for (int i = 1; i < csvRecords.size(); i++) {
            CSVRecord record = csvRecords.get(i);
            //Create a new student object and fill his data

            Auditorium student = new Auditorium(
                    record.get(FILE_HEADER_MAPPING[0]),
                    Integer.valueOf(record.get(FILE_HEADER_MAPPING[1])));

            List<Integer> vipSeatsList = readVipSeats(record);

            student.setVipSeats(vipSeatsList);

            students.add(student);
        }

        return students;
    }

    private List<Integer> readVipSeats(CSVRecord record) {
        String vipSeatsAsString = record.get(FILE_HEADER_MAPPING[2]);
        String vipSeatsAsStringA[] = vipSeatsAsString.split(",");
        List<Integer> vipSeatsList = new ArrayList<>();

        for (int i = 0; i < vipSeatsAsStringA.length; i++) {
            String seat = vipSeatsAsStringA[i];
            vipSeatsList.add(Integer.valueOf(seat.trim()));
        }

        return vipSeatsList;
    }

    @Override
    protected String[] getCSVHeaderArray() {
        return AuditoriumCsvFileReader.FILE_HEADER_MAPPING;
    }

    @Override
    protected char getCSVDelimiter() {
        return ';';
    }

    @Override
    protected String getCSVArrayDelimiter() {
        return ",";
    }

}
