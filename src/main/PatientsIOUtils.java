package main;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.SimpleDateFormat;
import java.util.*;

public class PatientsIOUtils {

    public static void printPatientsList(List<Patient> patients) {
        String border = "*" + "-".repeat(35) + "*" + "-".repeat(7) + "*" + "-".repeat(3) + "*" + "-".repeat(15) + "*";
        System.out.println(border);
        System.out.format("|%35s|%7s|%3s|%15s|\n", "ФИО", "Возраст", "Пол", "Телефон");
        String x = "|" + "-".repeat(35) + "|" + "-".repeat(7) + "|" + "-".repeat(3) + "|" + "-".repeat(15) + "|";
        System.out.println(x);
        for (int i = 0; i < patients.size(); i++) {
            Patient p = patients.get(i);
            String fio = p.getFirstName() + " " + p.getMiddleName() + " " + p.getLastName();
            String gender = (p.getGender().equals(Patient.Gender.MALE)) ? "М" : "Ж";
            System.out.format("|%35s|%7s|%3s|%15s|\n", fio, p.getAge(), gender, p.getPhone());
            if (i != patients.size() - 1) System.out.println(x);
        }
        System.out.println(border);
    }

    public static List<Patient> getPatientsListFromXMLFile(String path) {
        List<Patient> patientsList = new ArrayList<>();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(path);
            Node root = document.getDocumentElement();
            NodeList patients = root.getChildNodes();
            for (int i = 0; i < patients.getLength(); i++) {
                if (patients.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    NodeList patientData = patients.item(i).getChildNodes();
                    Patient patient = new Patient();
                    for (int j = 0; j < patientData.getLength(); j++) {
                        if (patientData.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            String param = patientData.item(j).getTextContent();
                            switch (patientData.item(j).getNodeName()) {
                                case "first_name" -> patient.setFirstName(param);
                                case "middle_name" -> patient.setMiddleName(param);
                                case "last_name" -> patient.setLastName(param);
                                case "birthday" -> {
                                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(param);
                                    Calendar calendar = new GregorianCalendar();
                                    calendar.setTime(date);
                                    patient.setBirthday(calendar);
                                }
                                case "gender" -> patient.setGender(
                                        (param.equals("male")) ? Patient.Gender.MALE : Patient.Gender.FEMALE
                                );
                                case "phone" -> patient.setPhone(param);
                            }
                        }
                    }
                    patientsList.add(patient);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patientsList;
    }
}
