package kg.vector.dictionary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "vocabulary")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vocabulary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 64, message = "Word length must be between 1 and 64 characters")
    private String word;

    @NotNull
    @Size(min = 1, max = 64, message = "Translate length must be between 1 and 64 characters")
    private String translate;

    @NotNull
    private Integer rating;
}
