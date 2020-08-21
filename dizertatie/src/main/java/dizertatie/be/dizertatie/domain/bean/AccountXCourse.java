package dizertatie.be.dizertatie.domain.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "account_x_course")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "AccountXCourse.countAll", query = "SELECT COUNT(x) FROM AccountXCourse x")
})
public class AccountXCourse {
    @Id
    @SequenceGenerator(name = "choice_sequence_generator", sequenceName = "choice_item_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "choice_sequence_generator" )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="enrolled_at")
    private Date enrolledAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Date getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(Date enrolledAt) {
        this.enrolledAt = enrolledAt;
    }
}
