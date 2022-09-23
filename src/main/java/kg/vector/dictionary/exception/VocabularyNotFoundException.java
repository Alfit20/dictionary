package kg.vector.dictionary.exception;

public class VocabularyNotFoundException extends RuntimeException{
    public VocabularyNotFoundException(String message) {
        super(message);
    }
}
