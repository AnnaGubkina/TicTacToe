package tictactoe.exceptions.handler;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tictactoe.exceptions.ExceptionWebResponse;
import tictactoe.exceptions.NoSuchPlayerException;
import tictactoe.exceptions.NotFoundException;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionWebResponse> handleNotFoundExceptionException(@NonNull final NotFoundException exc) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionWebResponse(exc.getMessage()));
    }

    @ExceptionHandler(NoSuchPlayerException.class)
    public ResponseEntity<ExceptionWebResponse> handleNoSuchPlayerException(@NonNull final NoSuchPlayerException exc) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionWebResponse(exc.getMessage()));
    }

}

