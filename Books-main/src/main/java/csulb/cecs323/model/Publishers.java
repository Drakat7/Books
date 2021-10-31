package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedNativeQuery(
        name = "ReturnPublishers",
        query = "SELECT * " +
                "FROM PUBLISHERS",
        resultClass = Publishers.class
)

@NamedNativeQuery(
        name = "CheckPublishersName",
        query = "SELECT * " +
                "FROM PUBLISHERS " +
                "WHERE NAME = ?",
        resultClass = Publishers.class
)
@NamedNativeQuery(
        name = "CheckPublishersEmail",
        query = "SELECT * " +
                "FROM PUBLISHERS " +
                "WHERE EMAIL = ?",
        resultClass = Publishers.class
)
@NamedNativeQuery(
        name = "CheckPublishersPhone",
        query = "SELECT * " +
                "FROM PUBLISHERS " +
                "WHERE PHONE = ?",
        resultClass = Publishers.class
)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}),
        @UniqueConstraint(columnNames = {"phone"}), @UniqueConstraint(columnNames = {"email"})})
/**
 * An entity which could release many or no books
 */
public class Publishers {

    @Id
    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 24)
    private String email;

    @Column(nullable = false, length = 80)
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publishers", cascade = CascadeType.PERSIST)
    private List<Books> books;

    /**
     * public constructor for the Publishers entity
     */
    public Publishers(){}

    /**
     * Parameterized constructor for the Publishers entity
     * @param name     Publishers name
     * @param email    Publishers email address
     * @param phone    Publishers phone number
     */
    public Publishers(String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
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
     * toString for the publishers entity
     * @return String   publishers info
     */
    @Override
    public String toString(){
        return "Publisher Name: " + this.name +", Email: " + this.email + ", Phone: " + this.phone;
    }

    /**
     * equals method which compares two publishers names
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        Publishers publisher = (Publishers) o;
        return this.getName().equals(publisher.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getEmail(), this.getPhone());
    }
}
