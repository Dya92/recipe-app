package diana.springframework.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
// table name is UNIT_OF_MEASURE because of camelcases
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

//    one direction from ingredient to unit of measure, so NO @OneToOne here
//    @OneToOne
//    private Ingredient ingredient;

}
