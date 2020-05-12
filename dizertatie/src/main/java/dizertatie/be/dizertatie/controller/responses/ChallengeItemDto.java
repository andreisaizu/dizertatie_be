package dizertatie.be.dizertatie.controller.responses;

import dizertatie.be.dizertatie.enums.ChallengeItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeItemDto {
    private long id;
    private String description;
    private List<ChallengeItemTaskDto> challengeItemTaskList;
    private String challengeItemType;
}
