package kg.vector.dictionary.dto;

import kg.vector.dictionary.entity.Vocabulary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyDTO {
    private Long id;

    @NotNull
    @Size(min = 1, max = 64, message = "Word length must be between 1 and 64 characters")
    private String word;

    @NotNull
    @Size(min = 1, max = 64, message = "Translate length must be between 1 and 64 characters")
    private String translate;

    @NotNull
    private Integer rating;

    public static VocabularyDTO from(Vocabulary vocabulary) {
        return VocabularyDTO.builder()
                .id(vocabulary.getId())
                .word(vocabulary.getWord())
                .translate(vocabulary.getTranslate())
                .rating(vocabulary.getRating())
                .build();
    }
}
