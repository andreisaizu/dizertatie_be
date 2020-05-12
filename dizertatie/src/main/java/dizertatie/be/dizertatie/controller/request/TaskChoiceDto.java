package dizertatie.be.dizertatie.controller.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskChoiceDto {
    private long id;
    private String value;
    private boolean selected;
    private boolean correct;
    
}
