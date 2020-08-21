package dizertatie.be.dizertatie.domain.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "Course.countAll", query = "SELECT COUNT(x) FROM Course x")
})
public class Course {

    @Id
    @SequenceGenerator(name = "course_id_seq", sequenceName = "course_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_seq" )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy="course", cascade = CascadeType.PERSIST)
    private List<Lesson> lessonList;

    @OneToMany(mappedBy="course", targetEntity= Challenge.class, cascade = CascadeType.PERSIST)
    private List<Challenge> challengeList;


    @ManyToMany(mappedBy = "studentCourseList")
    @JsonBackReference
    private List<Account> accountList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="created_by_account_id",referencedColumnName="id", nullable=false)
    @JsonBackReference
    private Account teacherAccount;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public List<Challenge> getChallengeList() {
        return challengeList;
    }

    public void setChallengeList(List<Challenge> challengeList) {
        this.challengeList = challengeList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Account getTeacherAccount() {
        return teacherAccount;
    }

    public void setTeacherAccount(Account account) {
        this.teacherAccount = account;
    }

}
