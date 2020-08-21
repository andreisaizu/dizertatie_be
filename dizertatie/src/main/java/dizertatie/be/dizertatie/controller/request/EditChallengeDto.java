package dizertatie.be.dizertatie.controller.request;

import dizertatie.be.dizertatie.controller.responses.ChallengeItemDto2;
import dizertatie.be.dizertatie.controller.responses.ChallengeItemTaskDto;
import dizertatie.be.dizertatie.controller.responses.ChallengeItemTaskDto2;
import lombok.Data;

import java.util.List;

@Data
public class EditChallengeDto {
    private long id;
    private List<ChallengeItemDto2> challengeItemList;
}
