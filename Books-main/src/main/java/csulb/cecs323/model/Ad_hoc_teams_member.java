package csulb.cecs323.model;

import javax.persistence.*;

@Entity
@IdClass(Ad_hoc_teams_member_pk.class)
public class Ad_hoc_teams_member {
    @Id
    @OneToOne
    private String individual_authors_email;

    @Id
    @Column(nullable = false, length = 30)
    private String ad_hoc_teams_email;
}
