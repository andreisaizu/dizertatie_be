package dizertatie.be.dizertatie.controller.responses;

import dizertatie.be.dizertatie.domain.bean.Course;
import lombok.Data;

import java.util.List;

@Data
public class CourseResponse {
    private List<CourseDto> courseList;
}
