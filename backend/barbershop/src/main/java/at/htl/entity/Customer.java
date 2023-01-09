package at.htl.entity;

import javax.persistence.*;

@Entity
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String sex;

    public Customer(String firstName, String lastName, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
    }

    public Customer() {

    }

    //<editor-fold desc="Getter&Setter">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    //</editor-fold>

    @Override
    public String toString() {
        return String.format("%s %s, Sex: %s", firstName, lastName, sex);
    }
}
