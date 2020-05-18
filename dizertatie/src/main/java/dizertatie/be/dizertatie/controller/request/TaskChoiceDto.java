package dizertatie.be.dizertatie.controller.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TaskChoiceDto {
    private long id;
    private List<String> values;
    private boolean selected;
    private boolean correct;
    
}
