package domain.organizacion;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import domain.medicion.Medicion;
import domain.medicion.MedicionAdapter;
import domain.medicion.MedicionRead;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvHandler {
  static final String FILE_PATH = "data/Mediciones.csv";
  // TODO: Analizar refactor de Path segun instancias de Organizacion
  //  Podria tomarse el path concatenado al nombre de la organizacion


  public List<Medicion> getMediciones() throws IOException {
    MedicionAdapter medicionAdapter = new MedicionAdapter();
    List<Medicion> mediciones = new ArrayList<>();
    List<MedicionRead> medicionesLeidas = this.getMedicionesRead();

    for (MedicionRead m : medicionesLeidas) {
      mediciones.add(medicionAdapter.adaptarMedicion(m));
    }
    return mediciones;
  }


  private List<MedicionRead> getMedicionesRead() throws IOException {
    CSVReader csvReader = openFile();
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

  private CSVReader openFile() throws FileNotFoundException {
    FileReader file = new FileReader(FILE_PATH);
    return new CSVReader(file);
  }
}
