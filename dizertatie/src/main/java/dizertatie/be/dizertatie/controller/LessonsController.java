package dizertatie.be.dizertatie.controller;

import dizertatie.be.dizertatie.controller.responses.ChallengeLightDto;
import dizertatie.be.dizertatie.controller.responses.CourseDto;
import dizertatie.be.dizertatie.controller.responses.CourseResponse;
import dizertatie.be.dizertatie.controller.responses.LessonDto;
import dizertatie.be.dizertatie.domain.bean.Account;
import dizertatie.be.dizertatie.domain.bean.Challenge;
import dizertatie.be.dizertatie.domain.bean.Course;
import dizertatie.be.dizertatie.domain.bean.Lesson;
import dizertatie.be.dizertatie.domain.service.AccountDomainService;
import dizertatie.be.dizertatie.domain.service.CourseDomainService;
import dizertatie.be.dizertatie.domain.service.LessonDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("lessons")
@RequiredArgsConstructor
public class LessonsController {
    private final LessonDomainService lessonDomainService;
    private final CourseDomainService courseDomainService;

    @GetMapping(path = "/{lessonId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LessonDto> getCourseById(@RequestHeader String Authorization, @PathVariable Long lessonId) {
        Lesson lesson = lessonDomainService.findById(lessonId);
        return ResponseEntity.ok(mapLessonDto(lesson));
    }

    @PutMapping(path = "/{lessonId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LessonDto> updateLessonById(@RequestBody LessonDto lessonDto, @PathVariable Long lessonId) {
        Lesson lesson = lessonDomainService.findById(lessonId);
        lesson.setTitle(lessonDto.getTitle());
        lesson.setContent(lessonDto.getContent());
        lessonDomainService.save(lesson);

        return ResponseEntity.ok(mapLessonDto(lesson));
    }

    @PostMapping(path="/new-lesson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createLesson(@RequestBody LessonDto lessonDto) {
        Course course = courseDomainService.findById(lessonDto.getCourseId());
        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDto.getTitle());
        lesson.setContent(lessonDto.getContent());
        lesson.setCourse(course);
        lesson.setCreatedAt(new Date());
        lessonDomainService.save(lesson);
        return ResponseEntity.ok().build();
    }


    private LessonDto mapLessonDto(Lesson lesson) {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(lesson.getId());
        lessonDto.setTitle(lesson.getTitle());
        lessonDto.setContent(lesson.getContent());
        return lessonDto;
    }

}
