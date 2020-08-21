package dizertatie.be.dizertatie.controller;

import dizertatie.be.dizertatie.controller.request.LoginDto;
import dizertatie.be.dizertatie.controller.responses.LoginResponse;
import dizertatie.be.dizertatie.domain.bean.Account;
import dizertatie.be.dizertatie.domain.service.AccountDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AccountDomainService accountDomainService;

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        LoginResponse loginResponse = new LoginResponse();
        Account account = accountDomainService.findByUserNameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if (account == null) {
            loginResponse.setValid(false);
        }
        else {
            loginResponse.setValid(true);
            loginResponse.setFirstName(account.getFirstName());
            loginResponse.setLastName(account.getLastName());
            loginResponse.setRole(account.getRole());
        }

        return ResponseEntity.ok(loginResponse);
    }
}
