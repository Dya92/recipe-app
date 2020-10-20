package diana.springframework.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
//excluding equals and hashcode for recipes because lombok has a problem with bidirectional relationships
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
