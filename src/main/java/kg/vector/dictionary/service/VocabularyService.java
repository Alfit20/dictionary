package kg.vector.dictionary.service;

import kg.vector.dictionary.dto.VocabularyDTO;
import kg.vector.dictionary.entity.Vocabulary;
import kg.vector.dictionary.exception.VocabularyNotFoundException;
import kg.vector.dictionary.repository.VocabularyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VocabularyService {
    private final VocabularyRepository vocabularyRepository;
    private Vocabulary vocabulary;

    public VocabularyDTO getOneRandomWord() {
        List<VocabularyDTO> randomElement = vocabularyRepository.findAll().stream()
                .filter(e -> e.getRating() < 10)
                .map(VocabularyDTO::from)
                .collect(Collectors.toList());
        Collections.shuffle(randomElement);
        vocabulary = vocabularyRepository.findById(randomElement.get(0).getId())
                .orElseThrow(() -> new VocabularyNotFoundException("Didn't find that word"));
        log.info("Random word selected {}", vocabulary);
        return VocabularyDTO.from(vocabulary);
    }


    public int getSizeWords() {
        return vocabularyRepository.findAll().size();
    }


    public List<VocabularyDTO> getWords() {
        List<Vocabulary> findAll = vocabularyRepository.findAll();
        Collections.shuffle(findAll);
        List<VocabularyDTO> vocabularies = findAll.stream()
                .filter(e -> !e.getTranslate().equals(vocabulary.getTranslate()) && e.getRating() < 10)
                .map(VocabularyDTO::from)
                .limit(6)
                .collect(Collectors.toList());
        vocabularies.add(VocabularyDTO.from(vocabulary));
        log.info("7 random letters {}", vocabularies);
        Collections.shuffle(vocabularies);
        return vocabularies;
    }


    public long numberOfLearnedWords() {
        return vocabularyRepository.findAll().stream()
                .filter(e -> e.getRating() > 9)
                .count();
    }


    public void learnWord(Long id) {
        Vocabulary word = vocabularyRepository.findById(id).orElseThrow(() -> new VocabularyNotFoundException("Didn't find that word"));
        if (word.getWord().equals(vocabulary.getWord())) {
            word.setRating(word.getRating() + 1);
        } else {
            word.setRating(word.getRating() - 2);
        }
        if (word.getRating() < 0) {
            word.setRating(0);
        }
        vocabularyRepository.save(word);
        log.error("the word is learned {}", word);
    }

    public void uploadFile(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputStream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("vocabulary");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Vocabulary vocabularyToSave = new Vocabulary();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    vocabularyToSave.setWord(element.getElementsByTagName("word").item(0).getTextContent());
                    vocabularyToSave.setTranslate(element.getElementsByTagName("translate").item(0).getTextContent());
                    vocabularyToSave.setRating(Integer.valueOf(element.getElementsByTagName("rating").item(0).getTextContent()));
                }
                vocabularyRepository.save(vocabularyToSave);
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            log.error("файл download/import.xml не обнаружен");
        }
    }

    public List<VocabularyDTO> getAllWordInDictionaries() {
        return vocabularyRepository.findAll().stream()
                .map(VocabularyDTO::from)
                .collect(Collectors.toList());
    }
}

