package Data;

/**
 * Created by regrau on 28.06.16.
 * Data Class which holds data for a Student.
 */
public class Student extends Person {

    /**
     * The kind of studies the student is at the moment studying.
     */
    private String subject;

    /**
     * The current semester that the student is in (only for subject)
     */
    private String currentSemester;

    /**
     * Constructor. Creates a student.
     * @param name
     * @param surName
     * @param address
     * @param zipcode
     * @param city
     * @param subject
     * @param currentSemester
     */
    public Student(String name, String surName, String address, String zipcode, String city, String subject, String currentSemester) {
        super(name, surName, address, zipcode, city);
        this.subject = subject;
        this.currentSemester = currentSemester;
    }

    /**
     * Getter for the student's subject.
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Getter for the student's current semester.
     * @return
     */
    public String getCurrentSemester() {
        return currentSemester;
    }
}
