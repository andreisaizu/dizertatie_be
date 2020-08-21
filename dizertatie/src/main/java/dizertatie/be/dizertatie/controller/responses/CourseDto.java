package dizertatie.be.dizertatie.controller.responses;

import dizertatie.be.dizertatie.domain.bean.Account;
import dizertatie.be.dizertatie.domain.bean.Course;
import lombok.Data;

import java.util.List;

@Data
public class CourseDto {
    private Long id;
    private String title;
    private String username;
    private List<LessonDto> lessonList;
    private List<ChallengeLightDto> challengeList;
    private List<Account> accountList;

}
