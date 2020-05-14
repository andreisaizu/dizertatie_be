package dizertatie.be.dizertatie.controller.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidatedChallengeItemAnswer {
    long challengeItemId;
    List<ValidatedChallengeItemTaskAnswer> taskAnswers;

    public ValidatedChallengeItemAnswer() {
        taskAnswers = new ArrayList<>();
    }

    public void add(ValidatedChallengeItemTaskAnswer challengeItemTaskAnswer) {
        taskAnswers.add(challengeItemTaskAnswer);
    }
}
