package br.com.abasteceai.exception;

public class AddressException extends Exception {

    private static final long serialVersionUID = 3028883219789015806L;

    public AddressException(String message) {
        super(message);
    }

    public AddressException(Throwable throwable) {
    }
}
