package dizertatie.be.dizertatie.controller.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChallengeAnswer {
    private long challengeId;
    private List<ChallengeItemAnswer> challengeItemAnswers;

    public ChallengeAnswer(){
        challengeItemAnswers = new ArrayList<>();
    }

    public void add(ChallengeItemAnswer challengeItemAnswer){
        challengeItemAnswers.add(challengeItemAnswer);
    }
}
