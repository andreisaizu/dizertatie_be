package dizertatie.be.dizertatie.controller.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChallengeItemTaskAnswer {
    long challengeItemTaskId;
    List<Long> selectedChoicesIds;
    List<String> selectedChoicesValues;
    List<TaskChoiceDto> taskChoiceDtoList;

    public ChallengeItemTaskAnswer() {
        taskChoiceDtoList = new ArrayList<>();
    }

    public void add(TaskChoiceDto taskChoiceDto) {
        taskChoiceDtoList.add(taskChoiceDto);
    }
}
