package kg.vector.dictionary.service;

import kg.vector.dictionary.repository.VocabularyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VocabularyService {
    private final VocabularyRepository vocabularyRepository;
}
