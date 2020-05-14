package dizertatie.be.dizertatie.domain.service;

import dizertatie.be.dizertatie.domain.bean.ChallengeAttempt;
import dizertatie.be.dizertatie.domain.repository.ChallengeAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeAttemptDomainService {

    @Autowired
    private ChallengeAttemptRepository challengeAttemptRepository;

    public ChallengeAttempt findById(Long id) {
        return challengeAttemptRepository.findById(id).isPresent() ? challengeAttemptRepository.findById(id).get() : null;
    }

    public List<ChallengeAttempt> findAll() {
        return (List<ChallengeAttempt>) challengeAttemptRepository.findAll();
    }

    public long countAll() {
        return challengeAttemptRepository.count();
    }

    public ChallengeAttempt save(ChallengeAttempt record) {
        return challengeAttemptRepository.save(record);
    }

    public ChallengeAttempt findBestChallengeAttemptForAccount(Long accountId, Long challengeId, Long currentChallengeAttemptId){
       return challengeAttemptRepository.findFirstByAccount_IdAndChallenge_IdAndIdNotOrderByScoreDesc(accountId, challengeId, currentChallengeAttemptId);
    }
}
