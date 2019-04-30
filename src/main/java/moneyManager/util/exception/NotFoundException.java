package moneyManager.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Vladimir on 09.08.2018.
 */
//  http://stackoverflow.com/a/22358422/548473
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "No data found")  // 422
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
