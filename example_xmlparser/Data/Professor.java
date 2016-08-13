package Data;


/**
 * Created by regrau on 28.06.16.
 * A Data Class which holds the data of a professor.
 * For simplicity purposes all data is handled as a String.
 */
public class Professor extends Person {
    /**
     * Holds the professor's birtday date.
     */
    private String birthday = "DD.MM.YYYY";

    /**
     * Holds the professor's personalId that is used at the professor's university.
     */
    private String personalId;

    /**
     * Holds the subject of the professor.
     */
    private String subject;

    /**
     * The constructor which, when called, initializes all data fields.
     * @param birthday The birthday of the professor.
     * @param personalId The internal unique id which identifies the professor.
     * @param subject The subject which the professor is teaching.
     */
    public Professor(String name, String surName, String birthday, String address, String zipCode, String personalId, String city, String subject) {
        super(name, surName, address, zipCode, city);
        this.birthday = birthday;
        this.personalId = personalId;
        this.subject = subject;
    }


    /**
     * Getter method for the professors birthday date.
     * @return
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Getter method for the professor's personal id.
     * @return
     */
    public String getPersonalId() {
        return personalId;
    }

    /**
     * Getter method for the professor's subject.
     * @return
     */
    public String getSubject() {
        return subject;
    }

}
