package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedNativeQuery(
        name = "ReturnAdHocTeamsMembers",
        query = "SELECT * " +
                "FROM AD_HOC_TEAMS_MEMBER ",
        resultClass = Ad_hoc_teams_member.class
)
@NamedNativeQuery(
        name = "ReturnAdHocTeamsMember",
        query = "SELECT * " +
                "FROM AD_HOC_TEAMS_MEMBER " +
                "WHERE INDIVIDUAL_AUTHORS_EMAIL = ? AND " +
                "AD_HOC_TEAMS_EMAIL = ? ",
        resultClass = Ad_hoc_teams_member.class
)
/**
 *A person who is a member of an Ad Hoc team
 */
public class Ad_hoc_teams_member{

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "individual_authors_email", referencedColumnName = "email", nullable = false)
    private Authoring_entities individual_authors;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_hoc_teams_email", referencedColumnName = "email", nullable = false)
    private Authoring_entities ad_hoc_teams;

    /**
     * Default constructor required by JPA
     */
    public Ad_hoc_teams_member() {}

    /**
     * A parameterized constructor to create the ad hoc team member
     * @param individual_authors  an individual author
     * @param ad_hoc_teams        an ad hoc team
     */
    public Ad_hoc_teams_member(Authoring_entities individual_authors, Authoring_entities ad_hoc_teams){
        this.individual_authors = individual_authors;
        this.ad_hoc_teams = ad_hoc_teams;
    }

    public Authoring_entities getIndividual_authors() {
        return individual_authors;
    }

    public void setIndividual_authors(Authoring_entities individual_authors) {
        this.individual_authors = individual_authors;
    }

    public Authoring_entities getAd_hoc_teams() {
        return ad_hoc_teams;
    }

    public void setAd_hoc_teams(Authoring_entities ad_hoc_teams) {
        this.ad_hoc_teams = ad_hoc_teams;
    }

    /**
     * toString method for an Authoring entity
     * @return String   An Authoring entity
     */
    @Override
    public String toString(){
        return "Authors Email: " + this.individual_authors.getEmail() + ", Ad Hoc Teams Email: " +
                this.ad_hoc_teams.getEmail();
    }

    /**
     * equals method to check if two individuals have the same email
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        Ad_hoc_teams_member ad_hoc_teams_member = (Ad_hoc_teams_member) o;
        return (this.getIndividual_authors() == ad_hoc_teams_member.getIndividual_authors() &&
                this.getAd_hoc_teams() == ad_hoc_teams_member.getAd_hoc_teams());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getIndividual_authors(), this.getAd_hoc_teams());
    }
}
