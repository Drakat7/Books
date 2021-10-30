package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
/**
 *A person who is a member of an Ad Hoc team
 * EXTENDS Authoring-entities
 */
@DiscriminatorValue("1")
public class Ad_hoc_teams_member extends Authoring_entities {

    @Id
    @Column(nullable = false, length = 30)
    private String individual_authors_email;

    @Id
    @Column(nullable = false, length = 30)
    private String ad_hoc_teams_email;

    /**
     * Default constructor required by JPA
     */
    public Ad_hoc_teams_member() {}

    /**
     * A parameterized constructor to create the ad hoc member
     * @param email                     new member's email address
     * @param authoring_entity_type     what type of author is this member
     * @param name                      members name
     * @param head_writer               who's the head writer of their book
     * @param year_formed               what year did this entity begin
     * @param individual_authors_email  email's of every individual in a writing group member
     * @param ad_hoc_teams_email        email of an ad hoc team member
     */
    public Ad_hoc_teams_member(String email, String authoring_entity_type, String name, String head_writer,
                               int year_formed, String individual_authors_email, String ad_hoc_teams_email){
        super(email, authoring_entity_type, name, head_writer, year_formed);
        this.individual_authors_email = individual_authors_email;
        this.ad_hoc_teams_email = ad_hoc_teams_email;
    }

    /**
     * getter method for individuals email
     * @return String  individual_authors_email
     */
    public String getIndividual_authors_email() {
        return individual_authors_email;
    }

    /**
     * setter method for an individual_authors_email
     * @param individual_authors_email
     */
    public void setIndividual_authors_email(String individual_authors_email) {
        this.individual_authors_email = individual_authors_email;
    }

    /**
     * getter method for an ad_hoc_teams_email
     *  @return String  ad_hoc_teams_email
     */
    public String getAd_hoc_teams_email() {
        return ad_hoc_teams_email;
    }

    /**
     * setter method for an ad_hoc_teams_email
     * @param ad_hoc_teams_email
     */
    public void setAd_hoc_teams_email(String ad_hoc_teams_email) {
        this.ad_hoc_teams_email = ad_hoc_teams_email;
    }

    /**
     * toString method for an Authoring entity
     * @return String   An Authoring entity
     */
    @Override
    public String toString(){
        return "Authoring Entity Email: " + this.getEmail() + ", Type: " + this.getAuthoring_entity_type() + ", Name: " +
                this.getName() + ", Head Writer: " + this.getHead_writer() + ", Year Formed: " + this.getYear_formed() +
                ", Individual Authors Email: " + this.individual_authors_email + ", Ad Hoc Teams Email: " +
                this.ad_hoc_teams_email;
    }

    /**
     * equals method to check if two individuals have the same email
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        Ad_hoc_teams_member ad_hoc_teams_member = (Ad_hoc_teams_member) o;
        return (this.getIndividual_authors_email() == ad_hoc_teams_member.getIndividual_authors_email() &&
                this.getAd_hoc_teams_email() == ad_hoc_teams_member.getAd_hoc_teams_email());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getEmail(), this.getAuthoring_entity_type(), this.getEmail(), this.getHead_writer(),
                this.getYear_formed(), this.getIndividual_authors_email(), this.getAd_hoc_teams_email());
    }
}
