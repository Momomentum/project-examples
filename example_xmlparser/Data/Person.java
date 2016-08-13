package Data;

import java.io.Serializable;

/**
 * Created by regrau on 01.07.16.
 * Class which contains common data for people.
 */
public class Person implements Serializable {
    /**
     * Name of the person.
     */
    private String name;

    /**
     * Surname of the person.
     */
    private String surname;

    /**
     * Main address of the person.
     */
    private String address;

    /**
     * Zipcode of the person's main address.
     */
    private String zipcode;

    /**
     * City in which the person lives.
     */
    private String city;


    /**
     * Default constructor which initializes all attributes.
     */
    public Person(String name, String surname, String address, String zipcode, String city) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
    }

    /**
     * Getter method for person's name.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for person's surname
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Getter method for person's address.
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter method for person's zipcode
     * @return
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Getter method for person's city.
     * @return
     */
    public String getCity() {
        return city;
    }
}
