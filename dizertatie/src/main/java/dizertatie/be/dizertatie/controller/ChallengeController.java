package dizertatie.be.dizertatie.controller;

import dizertatie.be.dizertatie.controller.responses.ChallengeDto;
import dizertatie.be.dizertatie.enums.ChallengeTemplate;
import dizertatie.be.dizertatie.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("challenges")
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;

    @GetMapping(path = "/byTemplate/{challengeTemplate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChallengeDto> getChallenge(@PathVariable("challengeTemplate") ChallengeTemplate challengeTemplate) {
        ChallengeDto challengeDto = challengeService.getChallengeDtoByTemplate(challengeTemplate);
        return ResponseEntity.ok(challengeDto);
    }
}
