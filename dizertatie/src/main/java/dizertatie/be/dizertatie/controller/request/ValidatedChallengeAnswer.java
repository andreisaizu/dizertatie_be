package dizertatie.be.dizertatie.controller.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidatedChallengeAnswer {
    private long challengeId;
    private int totalScore;
    private List<ValidatedChallengeItemAnswer> challengeItemAnswers;

    public ValidatedChallengeAnswer(){
        challengeItemAnswers = new ArrayList<>();
    }

    public void add(ValidatedChallengeItemAnswer challengeItemAnswer){
        challengeItemAnswers.add(challengeItemAnswer);
    }
}
