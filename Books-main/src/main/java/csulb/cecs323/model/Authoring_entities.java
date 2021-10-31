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
@NamedNativeQuery(
        name = "CheckAuthoringEntitiesEmail",
        query = "SELECT * " +
                "FROM  AUTHORING_ENTITIES " +
                "WHERE EMAIL = ? ",
        resultClass = Authoring_entities.class
)
@NamedNativeQuery(
        name = "CheckAuthoringEntitiesName",
        query = "SELECT * " +
                "FROM  AUTHORING_ENTITIES " +
                "WHERE NAME = ? ",
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
     * toString for the authoring entity
     * @return String   All of the entities information
     */
    @Override
    public String toString(){
        return "Authoring Entity Email: " + this.email + ", Type: " + this.authoring_entity_type + ", Name: " +
                this.name;
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
                this.getEmail());
    }
}
