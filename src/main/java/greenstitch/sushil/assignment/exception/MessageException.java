package greenstitch.sushil.assignment.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class MessageException extends RuntimeException{
    private String resourceName;
    private String fieldName;

    public MessageException(String resourceName, String fieldName) {
        super(String.format("%s, %s", resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

}
