package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Ad Hoc Team")
/**
 * An ad hoc team which is a type of authoring entity and extends from Authoring_entities
 */
public class Ad_hoc_team extends Authoring_entities{

    /**
     *Ad_hoc_teams_memebers_ instances in the database
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ad_hoc_teams", cascade = CascadeType.PERSIST)
    private List<Ad_hoc_teams_member> ad_hoc_teams_members;

    /**
     * Default constructor required by JPA
     */
    public Ad_hoc_team() {}

    /**
     * The parameterized/overloaded constructor for an Ad hoc team
     * @param email                     The teams email address
     * @param authoring_entity_type     The type of authoring_entities it is
     * @param name                      The name of the team itself
     */
    public Ad_hoc_team(String email, String authoring_entity_type, String name){
        super(email, authoring_entity_type, name);
    }
}
