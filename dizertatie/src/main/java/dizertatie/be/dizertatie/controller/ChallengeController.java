package dizertatie.be.dizertatie.controller;

import dizertatie.be.dizertatie.controller.request.ChallengeAnswer;
import dizertatie.be.dizertatie.controller.request.ValidatedChallengeAnswer;
import dizertatie.be.dizertatie.controller.responses.ChallengeDto;
import dizertatie.be.dizertatie.domain.service.ChallengeAttemptDomainService;
import dizertatie.be.dizertatie.enums.ChallengeTemplate;
import dizertatie.be.dizertatie.service.ChallengeAttemptService;
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
    private final ChallengeAttemptService challengeAttemptService;

    @GetMapping(path = "/byTemplate/{challengeTemplate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChallengeDto> getChallenge(@PathVariable("challengeTemplate") ChallengeTemplate challengeTemplate) {
        ChallengeDto challengeDto = challengeService.getChallengeDtoByTemplate(challengeTemplate);
        return ResponseEntity.ok(challengeDto);
    }

    @PostMapping(path = "/validateAnswer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validateAnswer(@RequestBody ChallengeAnswer challengeAnswer) {
        ValidatedChallengeAnswer validatedAnswer = challengeService.validateAnswer(challengeAnswer);
        challengeAttemptService.storeChallengeAttemptAndAwardPoints(validatedAnswer);
        return ResponseEntity.ok(validatedAnswer);
    }
}
