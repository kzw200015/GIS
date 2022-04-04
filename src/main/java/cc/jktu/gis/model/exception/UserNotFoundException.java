package cc.jktu.gis.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends UserException {

    public UserNotFoundException(Integer id) {
        super("未找到id为" + id + "的用户");
    }

}
