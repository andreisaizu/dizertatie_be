package dizertatie.be.dizertatie.controller.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChallengeItemAnswer {
    long challengeItemId;
    List<ChallengeItemTaskAnswer> taskAnswers;

    public ChallengeItemAnswer() {
        taskAnswers = new ArrayList<>();
    }

    public void add(ChallengeItemTaskAnswer challengeItemTaskAnswer) {
        taskAnswers.add(challengeItemTaskAnswer);
    }
}
