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
public class ChallengeItemDto2 implements Comparable{
    private int id;
    private String description;
    private List<ChallengeItemTaskDto2> challengeItemTaskList;
    private long challengeId;

    @Override
    public int compareTo(Object challengeItemDto) {
        return this.id - ((ChallengeItemDto2)challengeItemDto).getId();
    }
}
