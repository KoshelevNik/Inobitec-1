package main;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class Patient implements Comparable<Patient> {

    public enum Gender {
        MALE, FEMALE
    }

    private String firstName, middleName, lastName, phone;
    private Calendar birthday;
    private Gender gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        LocalDate birthdayDate = LocalDate.of(
                birthday.get(Calendar.YEAR),
                birthday.get(Calendar.MONTH),
                birthday.get(Calendar.DAY_OF_MONTH)
        );
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthdayDate, now);
        return period.getYears();
    }

    @Override
    public int compareTo(Patient o) {
        return lastName.compareTo(o.lastName);
    }

    public int compareTo(Patient o, SortMethod sortMethod) {
        if (sortMethod.equals(SortMethod.NAME)) {
            return compareTo(o);
        } else {
            return o.birthday.compareTo(birthday);
        }
    }
}
