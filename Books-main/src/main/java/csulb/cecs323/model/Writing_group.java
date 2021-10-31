package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import java.util.Objects;

@Entity
@DiscriminatorValue("Writing Group")
public class Writing_group extends Authoring_entities{

    @Column(nullable = false, length = 80)
    private String head_writer;

    @Column(nullable = false)
    private int year_formed;

    public Writing_group() {}

    public Writing_group(String email, String authoring_entity_type, String name, String head_writer, int year_formed) {
        super(email, authoring_entity_type, name);
        this.head_writer = head_writer;
        this.year_formed = year_formed;
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

    /**
     * toString for the authoring entity
     * @return String   All of the entities information
     */
    @Override
    public String toString(){
        return "Authoring Entity Email: " + this.getEmail() + ", Type: " + this.getAuthoring_entity_type() + ", Name: " +
                this.getName() + ", Head Writer: " + this.head_writer + ", Year Formed: " + this.year_formed;
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
