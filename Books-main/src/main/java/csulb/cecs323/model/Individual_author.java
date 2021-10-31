package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Individual Author")
public class Individual_author extends Authoring_entities{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "individual_authors", cascade = CascadeType.PERSIST)
    private List<Ad_hoc_teams_member> ad_hoc_teams_members;

    public Individual_author() {}

    public Individual_author(String email, String authoring_entity_type, String name){
        super(email, authoring_entity_type, name);
    }
}
