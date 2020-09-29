package diana.springframework.model;

import javax.persistence.*;

@Entity
// table name is UNIT_OF_MEASURE because of camelcases
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

//    one direction from ingredient to unit of measure, so no @OneToOne here
//    @OneToOne
//    private Ingredient ingredient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String uom) {
        this.description = uom;
    }

}
