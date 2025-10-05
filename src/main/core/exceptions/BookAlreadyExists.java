package main.core.exceptions;

public class BookAlreadyExists extends Exception{
    public BookAlreadyExists(String message) {
        super(message);
    }
}
