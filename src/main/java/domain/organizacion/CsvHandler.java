package domain.organizacion;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import domain.medicion.Medicion;
import domain.medicion.MedicionRead;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
public class CsvHandler {
  static final String FILE_PATH = "data/Mediciones.csv";

  private List<Medicion> getMediciones() {
    // TODO: Completar definicion
    return null;
  }

  private Medicion leerMedicion() {
    return null;
  }

  private List<MedicionRead> getMedicionesRead(String FILE_PATH) throws IOException {
    CSVReader csvReader = openFile(FILE_PATH);
    return getParse(csvReader);
  }

  private List<MedicionRead> getParse(CSVReader csvReader) {
    return new CsvToBeanBuilder<MedicionRead>(csvReader)
        .withType(MedicionRead.class)
        .withSeparator(',')
        .withIgnoreLeadingWhiteSpace(true)
        .withIgnoreEmptyLine(true)
        .build()
        .parse();
  }

  private CSVReader openFile(String FILE_PATH) throws FileNotFoundException {
    FileReader file = new FileReader(FILE_PATH);
    return new CSVReader(file);
  }
}
