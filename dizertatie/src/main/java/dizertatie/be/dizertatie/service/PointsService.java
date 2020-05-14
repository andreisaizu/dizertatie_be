package dizertatie.be.dizertatie.service;

import dizertatie.be.dizertatie.domain.bean.Account;
import dizertatie.be.dizertatie.domain.bean.ChallengeAttempt;
import dizertatie.be.dizertatie.domain.service.AccountDomainService;
import dizertatie.be.dizertatie.domain.service.ChallengeAttemptDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointsService {
    private final ChallengeAttemptDomainService challengeAttemptDomainService;
    private final AccountDomainService accountDomainService;

    public void awardPoints(Long challengeAttemptId) {
        ChallengeAttempt challengeAttempt = challengeAttemptDomainService.findById(challengeAttemptId);
        Account account = accountDomainService.findById(challengeAttempt.getAccount().getId());
        ChallengeAttempt bestChallengeAttempt = challengeAttemptDomainService.findBestChallengeAttemptForAccount(challengeAttempt.getAccount().getId(), challengeAttempt.getChallenge().getId(), challengeAttemptId);
        if(bestChallengeAttempt == null){
            account.setScore(challengeAttempt.getScore());
            accountDomainService.save(account);
        }
        if(challengeAttempt.getScore() > bestChallengeAttempt.getScore()){
            int accountCurrentScore = account.getScore();
            account.setScore(accountCurrentScore + (challengeAttempt.getScore() - bestChallengeAttempt.getScore()));
            accountDomainService.save(account);
        }
    }
}
