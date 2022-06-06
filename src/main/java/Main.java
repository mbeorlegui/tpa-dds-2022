import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import domain.medicion.Medicion;
import domain.medicion.MedicionAdapter;
import domain.medicion.MedicionRead;
import domain.organizacion.Clasificacion;
import domain.organizacion.CsvHandler;
import domain.organizacion.Organizacion;
import domain.organizacion.Tipo;
import domain.ubicacion.Ubicacion;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException, CsvValidationException {

    FileReader file = new FileReader("data/Mediciones.csv");
    CSVReader csvReader = new CSVReader(file);

    List<MedicionRead> medicionesLeidas = new ArrayList<MedicionRead>(new CsvToBeanBuilder<MedicionRead>(csvReader)
        .withType(MedicionRead.class)
        .withSeparator(',')
        .withIgnoreLeadingWhiteSpace(true)
        .withIgnoreEmptyLine(true)
        .build()
        .parse());

    for (MedicionRead m : medicionesLeidas) {
      System.out.println(m);
    }


    List<Medicion> medicionesPiola = new ArrayList<Medicion>();
    MedicionAdapter medicionAdapter = new MedicionAdapter();
    for (MedicionRead m : medicionesLeidas) {
      Medicion a = medicionAdapter.adaptarMedicion(m);
      medicionesPiola.add(a);
    }

    System.out.println("----------------------");

    for (Medicion m : medicionesPiola) {
      System.out.println(m);
    }

    Ubicacion ubicacionUtn = new Ubicacion(-34.659488779869484, -58.4671460833512);
    Organizacion organizacion = new Organizacion("UTN", Tipo.GUBERNAMENTAL, ubicacionUtn, Clasificacion.UNIVERSIDAD);
    System.out.println(organizacion.getMediciones());
    organizacion.agregarMediciones();
    System.out.println(organizacion.getMediciones());

  }
  // TODO: Borrar main
}