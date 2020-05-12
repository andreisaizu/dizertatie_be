package dizertatie.be.dizertatie.controller.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChallengeItemTaskAnswer {
    long challengeItemTaskId;
    List<Long> selectedChoicesIds;
    List<TaskChoiceDto> taskChoiceDtoList;
    List<String> correctChoicesValues;
    private boolean correct;
    private String question;
    private String explanation;

    public ChallengeItemTaskAnswer() {
        taskChoiceDtoList = new ArrayList<>();
    }

    public void add(TaskChoiceDto taskChoiceDto) {
        taskChoiceDtoList.add(taskChoiceDto);
    }
}
