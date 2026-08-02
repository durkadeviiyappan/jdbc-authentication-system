package exceptionCollections;

public class DuplicateMailIdException extends Exception{
    public DuplicateMailIdException(String msg) {
    	super(msg);
    }
}
