package dizertatie.be.dizertatie.service;

import dizertatie.be.dizertatie.controller.request.*;
import dizertatie.be.dizertatie.domain.bean.*;
import dizertatie.be.dizertatie.domain.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeAttemptService {
    private final ChallengeAttemptDomainService challengeAttemptDomainService;
    private final ChallengeItemTaskDomainService challengeItemTaskDomainService;
    private final ChallengeItemDomainService challengeItemDomainService;
    private final ChallengeDomainService challengeDomainService;
    private final AccountDomainService accountDomainService;
    private final PointsService pointsService;



    private ChallengeAttempt storeChallengeAttempt(ValidatedChallengeAnswer validatedChallengeAnswer) {
        ChallengeAttempt challengeAttempt = new ChallengeAttempt();
        int totalScore = 0;
        Challenge challenge = challengeDomainService.findById(validatedChallengeAnswer.getChallengeId());
        Account account = accountDomainService.findById(1L);
        challengeAttempt.setChallenge(challenge);
        challengeAttempt.setAccount(account);
        challengeAttempt.setCreatedAt(new Date());

        List<ChallengeItemAttempt> challengeItemAttemptList = new ArrayList<>();

        for (ValidatedChallengeItemAnswer validatedChallengeItemAnswer : validatedChallengeAnswer.getChallengeItemAnswers()) {
            ChallengeItemAttempt challengeItemAttempt = new ChallengeItemAttempt();
            ChallengeItem challengeItem = challengeItemDomainService.findById(validatedChallengeItemAnswer.getChallengeItemId());
            challengeItemAttempt.setChallengeAttempt(challengeAttempt);
            challengeItemAttempt.setChallengeItem(challengeItem);

            List<ChallengeItemTaskAttempt> challengeItemTaskAttemptList = new ArrayList<>();

            for (ValidatedChallengeItemTaskAnswer validatedChallengeItemTaskAnswer : validatedChallengeItemAnswer.getTaskAnswers()) {
                ChallengeItemTaskAttempt challengeItemTaskAttempt = new ChallengeItemTaskAttempt();
                challengeItemTaskAttempt.setValid(validatedChallengeItemTaskAnswer.isCorrect());
                challengeItemTaskAttempt.setChallengeItemAttempt(challengeItemAttempt);

                ChallengeItemTask challengeItemTask = challengeItemTaskDomainService.findById(validatedChallengeItemTaskAnswer.getChallengeItemTaskId());

                totalScore += validatedChallengeItemTaskAnswer.getPoints();

                challengeItemTaskAttempt.setChallengeItemTask(challengeItemTask);
                challengeItemTaskAttemptList.add(challengeItemTaskAttempt);
            }
            challengeItemAttempt.setChallengeItemTaskAttemptList(challengeItemTaskAttemptList);
            challengeItemAttemptList.add(challengeItemAttempt);
        }

        challengeAttempt.setChallengeItemAttemptList(challengeItemAttemptList);
        challengeAttempt.setScore(totalScore);

        return challengeAttemptDomainService.save(challengeAttempt);
    }

    public ChallengeAttempt storeChallengeAttemptAndAwardPoints(ValidatedChallengeAnswer validatedChallengeAnswer) {
        ChallengeAttempt challengeAttempt = storeChallengeAttempt(validatedChallengeAnswer);
        pointsService.awardPoints(challengeAttempt.getId());
        return challengeAttempt;
    }

}
