package dizertatie.be.dizertatie.controller;

import dizertatie.be.dizertatie.controller.responses.ChallengeDto;
import dizertatie.be.dizertatie.enums.ChallengeTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("challenges")
public class ChallengeController {

    @GetMapping(path = "/byTemplate/{challengeTemplate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChallengeDto> getChallenge(@PathVariable("challengeTemplate") ChallengeTemplate challengeTemplate) {
        ChallengeDto challengeDto = ChallengeDto.builder().build();
        return ResponseEntity.ok(challengeDto);
    }
}
