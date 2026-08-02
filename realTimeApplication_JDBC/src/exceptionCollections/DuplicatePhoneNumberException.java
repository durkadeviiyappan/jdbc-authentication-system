package exceptionCollections;

public class DuplicatePhoneNumberException extends Exception{
    public DuplicatePhoneNumberException(String msg) {
    	super(msg);
    }
}
