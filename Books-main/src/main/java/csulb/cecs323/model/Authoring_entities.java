package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames =
        {"email"})})
public class Authoring_entities {
    @Id
    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false, length = 31)
    private String authoring_entity_type;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 80)
    private String head_writer;

    @Column(nullable = false, length = 64)
    private int year_formed;

    public Authoring_entities() {}

    public Authoring_entities(String email, String authoring_entity_type, String name, String head_writer, int year_formed){
        this.email = email;
        this.authoring_entity_type = authoring_entity_type;
        this.name = name;
        this.head_writer = head_writer;
        this.year_formed = year_formed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthoring_entity_type() {
        return authoring_entity_type;
    }

    public void setAuthoring_entity_type(String authoring_entity_type) {
        this.authoring_entity_type = authoring_entity_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead_writer() {
        return head_writer;
    }

    public void setHead_writer(String head_writer) {
        this.head_writer = head_writer;
    }

    public int getYear_formed() {
        return year_formed;
    }

    public void setYear_formed(int year_formed) {
        this.year_formed = year_formed;
    }

    @Override
    public String toString(){
        return "Authoring Entity Email: " + this.email + ", Type: " + this.authoring_entity_type + ", Name: " +
                this.name + ", Head Writer: " + this.head_writer + ", Year Formed: " + this.year_formed;
    }

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
