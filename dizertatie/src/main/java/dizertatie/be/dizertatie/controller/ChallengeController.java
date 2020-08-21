package dizertatie.be.dizertatie.controller;

import dizertatie.be.dizertatie.controller.request.ChallengeAnswer;
import dizertatie.be.dizertatie.controller.request.EditChallengeDto;
import dizertatie.be.dizertatie.controller.request.ValidatedChallengeAnswer;
import dizertatie.be.dizertatie.controller.responses.ChallengeAttemptDto;
import dizertatie.be.dizertatie.controller.responses.ChallengeDto;
import dizertatie.be.dizertatie.domain.bean.Account;
import dizertatie.be.dizertatie.domain.bean.ChallengeAttempt;
import dizertatie.be.dizertatie.domain.service.AccountDomainService;
import dizertatie.be.dizertatie.domain.service.ChallengeAttemptDomainService;
import dizertatie.be.dizertatie.enums.ChallengeTemplate;
import dizertatie.be.dizertatie.service.ChallengeAttemptService;
import dizertatie.be.dizertatie.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("challenges")
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;
    private final ChallengeAttemptService challengeAttemptService;
    private final ChallengeAttemptDomainService challengeAttemptDomainService;
    private final AccountDomainService accountDomainService;

    @GetMapping(path = "/byTemplate/{challengeTemplate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChallengeDto> getChallenge(@RequestHeader String Authorization, @PathVariable("challengeTemplate") ChallengeTemplate challengeTemplate) {


        ChallengeDto challengeDto = challengeService.getChallengeDtoByTemplate(challengeTemplate);
        return ResponseEntity.ok(challengeDto);
    }

    @GetMapping(path = "/attempts/{challengeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChallengeAttemptDto> getChallengeAtempts(@RequestHeader String Authorization, @PathVariable("challengeId") Long challengeId) {
        List<ChallengeAttempt> challengeAttemptList = challengeAttemptDomainService.findByUsernameAndChallengeId(Authorization, challengeId);
        ChallengeAttemptDto challengeAttemptDto = new ChallengeAttemptDto();
        challengeAttemptDto.setChallengeAttemptList(challengeAttemptList);
        return ResponseEntity.ok(challengeAttemptDto);
    }

    @GetMapping(path = "/{challengeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChallengeDto> getChallenge(@RequestHeader String Authorization, @PathVariable("challengeId") Long challengeId) {


        ChallengeDto challengeDto = challengeService.getChallengeDtoById(challengeId);
        return ResponseEntity.ok(challengeDto);
    }

    @PostMapping(path = "/validateAnswer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validateAnswer(@RequestBody ChallengeAnswer challengeAnswer) {
        ValidatedChallengeAnswer validatedAnswer = challengeService.validateAnswer(challengeAnswer);
        ChallengeAttempt challengeAttempt = challengeAttemptService.storeChallengeAttemptAndAwardPoints(validatedAnswer);
        validatedAnswer.setTotalScore(challengeAttempt.getScore());
        return ResponseEntity.ok(validatedAnswer);
    }

    @PostMapping(path = "/updateChallenge", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateChallenge(@RequestBody EditChallengeDto challengeDto) {
        int x = 0;
        return ResponseEntity.ok().build();
    }
}
