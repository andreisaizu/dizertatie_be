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
public class ChallengeItemDto {
    private int id;
    private List<ChallengeItemTaskDto> challengeItemTaskDtoList;
}
