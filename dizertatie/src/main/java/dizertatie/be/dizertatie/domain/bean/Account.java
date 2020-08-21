package dizertatie.be.dizertatie.domain.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "Account.countAll", query = "SELECT COUNT(x) FROM Account x")
})
public class Account {

    @Id
    @SequenceGenerator(name = "account_sequence_generator", sequenceName = "account_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence_generator" )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "score")
    private Integer score;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Account_x_Challenge",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "challenge_id") }
    )
    List<Challenge> challengeList = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Account_x_Course",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    List<Course> studentCourseList = new ArrayList<>();

    public List<Course> getTeacherCourseList() {
        return teacherCourseList;
    }

    public void setTeacherCourseList(List<Course> teacherCourseList) {
        this.teacherCourseList = teacherCourseList;
    }

    @OneToMany(mappedBy= "teacherAccount", targetEntity= Course.class)
    private List<Course> teacherCourseList;

    @JsonManagedReference
    @OneToMany(mappedBy="account", targetEntity= ChallengeAttempt.class)
    private List<ChallengeAttempt> challengeAttemptList;

    public List<Challenge> getChallengeList() {
        return challengeList;
    }

    public void setChallengeList(List<Challenge> challengeList) {
        this.challengeList = challengeList;
    }

    public List<ChallengeAttempt> getChallengeAttemptList() {
        return challengeAttemptList;
    }

    public void setChallengeAttemptList(List<ChallengeAttempt> challengeAttemptList) {
        this.challengeAttemptList = challengeAttemptList;
    }

    public List<Course> getStudentCourseList() {
        return studentCourseList;
    }

    public void setStudentCourseList(List<Course> accountList) {
        this.studentCourseList = accountList;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
