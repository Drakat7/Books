package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Individual Author")
/**
 * An authoring_entity that is not a memeber of a writing group of ad hoc team
 * which extendes Authoring_entities
 */
public class Individual_author extends Authoring_entities{

    /**
     * instances of the Individual_author
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "individual_authors", cascade = CascadeType.PERSIST)
    private List<Ad_hoc_teams_member> ad_hoc_teams_members;

    /**
     * default constructor
     */
    public Individual_author() {}


    /**
     * parameterized constructor to set the individual authors instance variables
     * @param email                     the individual authors email address
     * @param authoring_entity_type     the type of authoring_entity (individual_author)
     * @param name                      the name of the author
     */
    public Individual_author(String email, String authoring_entity_type, String name){
        super(email, authoring_entity_type, name);
    }
}
