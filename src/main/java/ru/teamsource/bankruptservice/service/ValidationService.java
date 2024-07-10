package ru.teamsource.bankruptservice.service;

        import org.springframework.stereotype.Service;
import javax.validation.Valid;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ValidationService {
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    public <T> void validate(@Valid T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<T> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
            }
            throw new RuntimeException("Validation error: " + sb.toString());
        }
    }
}