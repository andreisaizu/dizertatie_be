package dizertatie.be.dizertatie.domain.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dizertatie.be.dizertatie.controller.responses.ChallengeItemDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lesson")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "Lesson.countAll", query = "SELECT COUNT(x) FROM Lesson x")
})
public class Lesson {
    @Id
    @SequenceGenerator(name = "lesson_id_seq", sequenceName = "lesson_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_id_seq" )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(name="date_created")
    private Date createdAt;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "course_id", referencedColumnName="id", nullable = false)
    private Course course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
