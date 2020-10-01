package diana.springframework.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // no cascade because we want to delete the notes only if the recipe is deleted, not vice versa
    @OneToOne
    private Recipe recipe;

    // 256 characters is the maximum of string in jpa and hibernate
    // using @Lob to store a CLOB field
    @Lob
    private String recipeNotes;

}
