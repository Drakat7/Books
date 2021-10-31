package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Ad Hoc Team")
public class Ad_hoc_team extends Authoring_entities{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ad_hoc_teams", cascade = CascadeType.PERSIST)
    private List<Ad_hoc_teams_member> ad_hoc_teams_members;

    public Ad_hoc_team() {}

    public Ad_hoc_team(String email, String authoring_entity_type, String name){
        super(email, authoring_entity_type, name);
    }
}
