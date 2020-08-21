package dizertatie.be.dizertatie.controller.responses;

import dizertatie.be.dizertatie.domain.bean.ChallengeAttempt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class ChallengeAttemptDto {
    private long id;
    private List<ChallengeAttempt> challengeAttemptList;
}
