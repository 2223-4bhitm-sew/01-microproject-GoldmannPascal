package at.htl.entity;

import javax.persistence.*;

@NamedQuery(
        name = "Customer.findAll",
        query = "select c from Customer c"
)

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_ID")
    private Long id;

    @Column(name = "C_FIRST_NAME")
    private String firstName;

    @Column(name="C_LAST_NAME")
    private String lastName;

    @Column(name="C_PHONENUMBER")
    private String phoneNumber;

    @Column(name="C_EMAIL")
    private String email;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //</editor-fold>


    @Override
    public String toString() {
        return String.format("%s %s, Email: %s, Phonenumber: %s", firstName, lastName, email, phoneNumber);
    }
}
