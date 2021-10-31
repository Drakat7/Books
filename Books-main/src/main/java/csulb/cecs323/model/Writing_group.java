package csulb.cecs323.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;

@Entity
@DiscriminatorValue("Writing Group")
public class Writing_group extends Authoring_entities{

    public Writing_group() {}

    public Writing_group(String email, String authoring_entity_type, String name, String head_writer, int year_formed) {
        super(email, authoring_entity_type, name, head_writer, year_formed);
    }
}
