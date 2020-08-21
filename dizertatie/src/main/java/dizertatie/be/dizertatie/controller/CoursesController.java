package dizertatie.be.dizertatie.controller;

import dizertatie.be.dizertatie.controller.responses.ChallengeLightDto;
import dizertatie.be.dizertatie.controller.responses.CourseDto;
import dizertatie.be.dizertatie.controller.responses.CourseResponse;
import dizertatie.be.dizertatie.controller.responses.LessonDto;
import dizertatie.be.dizertatie.domain.bean.*;
import dizertatie.be.dizertatie.domain.service.AccountDomainService;
import dizertatie.be.dizertatie.domain.service.AccountXCourseDomainService;
import dizertatie.be.dizertatie.domain.service.CourseDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("courses")
@RequiredArgsConstructor
public class CoursesController {
    private final CourseDomainService courseDomainService;
    private final AccountDomainService accountDomainService;
    private final AccountXCourseDomainService accountXCourseDomainService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseResponse> getCourses(@RequestHeader String Authorization) {
        List<Course> courseList = new ArrayList<>();

        Account account = accountDomainService.findByUsername(Authorization);

        if (account.getRole().equals("STUDENT")) {
            courseList = account.getStudentCourseList();
        }

        if (account.getRole().equals("TEACHER")) {
            courseList = account.getTeacherCourseList();
        }

        return ResponseEntity.ok(mapCourseResponse(courseList));
    }

    @GetMapping(path="/not-enrolled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseResponse> getNotEnrolledCourses(@RequestHeader String Authorization) {
        List<Course> courseList = new ArrayList<>();
        Account account = accountDomainService.findByUsername(Authorization);
        courseList = courseDomainService.findNotEnrolledCourse(account.getId());
        return ResponseEntity.ok(mapCourseResponse(courseList));
    }

    @GetMapping(path = "/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDto> getCourseById(@RequestHeader String Authorization, @PathVariable Long courseId) {
        Course course = courseDomainService.findById(courseId);

        return ResponseEntity.ok(mapCourseDto(course));
    }

    @PostMapping(path = "/new-course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createLesson(@RequestBody CourseDto courseDto) {
        Account account = accountDomainService.findByUsername(courseDto.getUsername());
        Course course = new Course();
        course.setTitle(courseDto.getTitle());
        course.setTeacherAccount(account);
        courseDomainService.save(course);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{courseId}/enroll", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity enrollToCourse(@RequestHeader String Authorization, @PathVariable Long courseId) {
        Account account = accountDomainService.findByUsername(Authorization);

        AccountXCourse accountXCourse = new AccountXCourse();
        accountXCourse.setEnrolledAt(new Date());
        accountXCourse.setAccountId(account.getId());
        accountXCourse.setCourseId(courseId);
        accountXCourseDomainService.save(accountXCourse);

        return ResponseEntity.ok().build();
    }


    private CourseDto mapCourseDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setTitle(course.getTitle());

        courseDto.setAccountList(course.getAccountList());
        List<LessonDto> lessonDtoList = new ArrayList<>();
        for (Lesson lesson : course.getLessonList()) {
            lessonDtoList.add(mapLessonDto(lesson));
        }
        courseDto.setLessonList(lessonDtoList);

        List<ChallengeLightDto> challengeLightDtoList = new ArrayList<>();
        for (Challenge challenge : course.getChallengeList()) {
            challengeLightDtoList.add(mapChallengeDto(challenge));
        }
        courseDto.setChallengeList(challengeLightDtoList);
        return courseDto;
    }

    private LessonDto mapLessonDto(Lesson lesson) {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(lesson.getId());
        lessonDto.setTitle(lesson.getTitle());
        return lessonDto;
    }

    private CourseResponse mapCourseResponse(List<Course> courseList) {
        CourseResponse courseResponse = new CourseResponse();
        List<CourseDto> courseDtoList = new ArrayList<>();
        for (Course course : courseList) {
            courseDtoList.add(mapCourseDto(course));
        }
        courseResponse.setCourseList(courseDtoList);
        return courseResponse;
    }

    private ChallengeLightDto mapChallengeDto(Challenge challenge) {
        ChallengeLightDto challengeLightDto = new ChallengeLightDto();
        challengeLightDto.setId(challenge.getId());
        challengeLightDto.setTitle(challenge.getTitle());
        return challengeLightDto;
    }
}
