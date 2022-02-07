package main;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        if (args.length >= 1 && new File(args[0]).isFile() && FileUtils.getFileExtension(new File(args[0])).equals("xml")) {
            List<Patient> patients = PatientsIOUtils.getPatientsListFromXMLFile(args[0]);
            if (args.length >= 2) {
                if (args[1].equals("age")) patients.sort((o1, o2) -> o1.compareTo(o2, SortMethod.AGE));
                else if (args[1].equals("name")) patients.sort((o1, o2) -> o1.compareTo(o2, SortMethod.NAME));
                else System.err.println("Неверно указан второй параметр");
            }
            PatientsIOUtils.printPatientsList(patients);
        } else {
            System.err.println("Не указан .xml файл");
        }
    }
}
