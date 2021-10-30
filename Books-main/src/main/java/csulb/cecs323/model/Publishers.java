package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}),
        @UniqueConstraint(columnNames = {"phone"}), @UniqueConstraint(columnNames = {"email"})})

/**
 * An entity which could release many or no books
 */
public class Publishers {

    @Id
    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 80)
    private String phone;

    @Column(nullable = false, length = 24)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publishers", cascade = CascadeType.PERSIST)
    private List<Books> books;

    /**
     * public constructor for the Publishers entity
     */
    public Publishers(){}

    /**
     * Parameterized constructor for the Publishers entity
     * @param name     Publishers name
     * @param phone    Publishers phone number
     * @param email    Publishers email address
     */
    public Publishers(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * getter for the publishers name
     * @return String   name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for the publishers name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for the publishers phone number
     * @return String   phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * setter for the publishers phone number
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * getter for the publishers email address
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for the publishers email address
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * toString for the publishers entity
     * @return String   publishers info
     */
    @Override
    public String toString(){
        return "Publisher Name: " + this.name + ", Phone: " + this.phone + ", Email: " +
                this.email;
    }

    /**
     * equals method which compares two publishers names
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        Publishers publisher = (Publishers) o;
        return this.getName() == publisher.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPhone(), this.getEmail());
    }
}
