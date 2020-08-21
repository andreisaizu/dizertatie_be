package dizertatie.be.dizertatie.controller.responses;

import lombok.Data;

@Data
public class LoginResponse {
    private String firstName;
    private String lastName;
    private String role;
    private boolean valid;
}
