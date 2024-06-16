package exception;

public class RecordNotFoundException extends RuntimeException {
    
    public RecordNotFoundException(String entidade, Long id) {
        super(entidade + " não encontrada para o id " + id);
    }
}
