package org.codejudge.sb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginSuccessResponse extends SuccessResponse {

    @JsonProperty("auth_token")
    private String authToken;

    public LoginSuccessResponse(String authToken) {
        super();
        this.authToken = authToken;
    }
}
