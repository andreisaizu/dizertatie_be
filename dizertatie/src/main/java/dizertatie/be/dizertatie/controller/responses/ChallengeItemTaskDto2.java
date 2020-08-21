package dizertatie.be.dizertatie.controller.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeItemTaskDto2 {
    private long challengeItemId;
    private String question;
    private List<ChoiceDto2> choiceList;
}
