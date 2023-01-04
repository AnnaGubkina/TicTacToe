package tictactoe.exceptions;

public class NoSuchPlayerException extends RuntimeException{

    public NoSuchPlayerException(String message) {
        super(message);
    }
}
