/*
 * Created on 2019-12-26 ( Time 13:27:38 )
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */

package dizertatie.be.dizertatie.domain.service;

import dizertatie.be.dizertatie.domain.bean.ChallengeItemTask;
import dizertatie.be.dizertatie.domain.repository.ChallengeItemTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChallengeItemTaskDomainService {

    @Autowired
    private ChallengeItemTaskRepository challengeItemTaskRepository;


    public ChallengeItemTask findById(Long id) {
        return challengeItemTaskRepository.findById(id).isPresent() ? challengeItemTaskRepository.findById(id).get() : null;
    }

    public List<ChallengeItemTask> findAll() {
        return (List<ChallengeItemTask>) challengeItemTaskRepository.findAll();
    }

    public long countAll() {
        return challengeItemTaskRepository.count();
    }

    public ChallengeItemTask create(ChallengeItemTask record) {
        ChallengeItemTask saved = challengeItemTaskRepository.save(record);
        return saved;
    }


}