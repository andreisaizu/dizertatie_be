/*
 * Created on 2019-12-26 ( Time 13:27:37 )
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */

package dizertatie.be.dizertatie.domain.repository;

import dizertatie.be.dizertatie.domain.bean.Account;
import dizertatie.be.dizertatie.domain.bean.ChallengeAttempt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring JPA Repository for OriLogs
 * 
 * @author Telosys Tools Generator
 *
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByUsernameAndPassword(String username, String password);
    Account findByUsername(String username);
}