package dizertatie.be.dizertatie.controller.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidatedChallengeItemTaskAnswer {
    long challengeItemTaskId;
    List<TaskChoiceDto> taskChoiceDtoList;
    List<String> correctChoicesValues;
    private boolean correct;
    private String question;
    private String explanation;
    private Integer points;

    public ValidatedChallengeItemTaskAnswer() {
        taskChoiceDtoList = new ArrayList<>();
    }

    public void add(TaskChoiceDto taskChoiceDto) {
        taskChoiceDtoList.add(taskChoiceDto);
    }
}
