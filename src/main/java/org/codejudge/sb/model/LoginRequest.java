package org.codejudge.sb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginRequest {

    private String email;
    private String password;

    public void validateForLogin() {
        validateEmail();
        validatePassword();
    }

    private void validatePassword() {
        if (StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("password cannot be empty");
        }
    }

    private void validateEmail() {
        if (StringUtils.isEmpty(email)) {
            throw new IllegalArgumentException("email cannot be empty");
        }
    }
}
