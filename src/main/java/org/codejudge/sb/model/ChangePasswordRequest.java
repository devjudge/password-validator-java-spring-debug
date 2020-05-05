package org.codejudge.sb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChangePasswordRequest {

    private String email;
    private String password;
    @JsonProperty("confirm_password")
    private String confirmPassword;

    public void validateForChangePassword() {
        validateEmail();
        validatePassword();
        validateConfirmPassword();
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

    private void validateConfirmPassword() {
        if (StringUtils.isEmpty(confirmPassword)) {
            throw new IllegalArgumentException("password cannot be empty");
        }
    }

}
