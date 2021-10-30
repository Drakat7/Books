package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@DiscriminatorValue("1")
public class Ad_hoc_teams_member extends Authoring_entities {

    @Id
    @Column(nullable = false, length = 30)
    private String individual_authors_email;

    @Id
    @Column(nullable = false, length = 30)
    private String ad_hoc_teams_email;

    public Ad_hoc_teams_member() {}

    public Ad_hoc_teams_member(String email, String authoring_entity_type, String name, String head_writer,
                               int year_formed, String individual_authors_email, String ad_hoc_teams_email){
        super(email, authoring_entity_type, name, head_writer, year_formed);
        this.individual_authors_email = individual_authors_email;
        this.ad_hoc_teams_email = ad_hoc_teams_email;
    }

    public String getIndividual_authors_email() {
        return individual_authors_email;
    }

    public void setIndividual_authors_email(String individual_authors_email) {
        this.individual_authors_email = individual_authors_email;
    }

    public String getAd_hoc_teams_email() {
        return ad_hoc_teams_email;
    }

    public void setAd_hoc_teams_email(String ad_hoc_teams_email) {
        this.ad_hoc_teams_email = ad_hoc_teams_email;
    }

    @Override
    public String toString(){
        return "Authoring Entity Email: " + this.getEmail() + ", Type: " + this.getAuthoring_entity_type() + ", Name: " +
                this.getName() + ", Head Writer: " + this.getHead_writer() + ", Year Formed: " + this.getYear_formed() +
                ", Individual Authors Email: " + this.individual_authors_email + ", Ad Hoc Teams Email: " +
                this.ad_hoc_teams_email;
    }

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
