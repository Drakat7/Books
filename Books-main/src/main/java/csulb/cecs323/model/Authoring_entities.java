package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "authoring_entities")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "authoring_entity_type",
        discriminatorType = DiscriminatorType.STRING)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames =
        {"email"})})
@NamedNativeQuery(
        name = "ReturnAuthoringEntities",
        query = "SELECT * " +
                "FROM AUTHORING_ENTITIES ",
        resultClass = Authoring_entities.class
)
/**
 * A person or group who author books
 */
public class Authoring_entities {
    @Id
    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false, length = 31)
    private String authoring_entity_type;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(length = 80)
    private String head_writer;

    @Column(length = 64)
    private int year_formed;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authoring_entities", cascade = CascadeType.PERSIST)
    private List<Books> books;

    /**
     * default constructor
     */
    public Authoring_entities() {}

    /**
     * Parameterized constructor for the Authoring_entities
     * @param email                     new member's email address
     * @param authoring_entity_type     what type of author is this member
     * @param name                      members name
     */
    public Authoring_entities(String email, String authoring_entity_type, String name) {
        this.email = email;
        this.authoring_entity_type = authoring_entity_type;
        this.name = name;
    }

    /**
     * Parameterized constructor for the Authoring_entities
     * @param email                     new member's email address
     * @param authoring_entity_type     what type of author is this member
     * @param name                      members name
     * @param head_writer               who's the head writer of their book
     * @param year_formed               what year did this entity begin
     */
    public Authoring_entities(String email, String authoring_entity_type, String name, String head_writer, int year_formed){
        this.email = email;
        this.authoring_entity_type = authoring_entity_type;
        this.name = name;
        this.head_writer = head_writer;
        this.year_formed = year_formed;
    }

    /**
     * getter for a members email
     * @return String   email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for a members email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter for the type of author the member is
     * @return String authoring_entity_type
     */
    public String getAuthoring_entity_type() {
        return authoring_entity_type;
    }

    /**
     * setter for the type of author a member is
     * @param authoring_entity_type
     */
    public void setAuthoring_entity_type(String authoring_entity_type) {
        this.authoring_entity_type = authoring_entity_type;
    }

    /**
     * getter for the members name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for the members name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for the name of the head writer for a writing group
     * @return String   head_writer
     */
    public String getHead_writer() {
        return head_writer;
    }

    /**
     * setter for the name of the head writer for a writing group
     * @param head_writer
     */
    public void setHead_writer(String head_writer) {
        this.head_writer = head_writer;
    }

    /**
     * getter for the year the writing group was formed
     * @return int  year_formed
     */
    public int getYear_formed() {
        return year_formed;
    }

    /**
     * setter  for the year the writing group was formed
     * @param year_formed
     */
    public void setYear_formed(int year_formed) {
        this.year_formed = year_formed;
    }

    /**
     * toString for the authoring entity
     * @return String   All of the entities information
     */
    @Override
    public String toString(){
        return "Authoring Entity Email: " + this.email + ", Type: " + this.authoring_entity_type + ", Name: " +
                this.name + ", Head Writer: " + this.head_writer + ", Year Formed: " + this.year_formed;
    }

    /**
     * equals methods which checks if two entities have the same email
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        Authoring_entities authoring_entities = (Authoring_entities) o;
        return this.getEmail() == authoring_entities.getEmail();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getEmail(), this.getAuthoring_entity_type(),
                this.getEmail(), this.getHead_writer(), this.getYear_formed());
    }
}
