package dizertatie.be.dizertatie.controller.responses;

import lombok.Data;

@Data
public class LessonDto {
    private Long id;
    private String title;
    private String content;
    private Long courseId;
}
