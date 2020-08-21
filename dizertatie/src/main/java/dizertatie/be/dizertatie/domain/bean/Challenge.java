package dizertatie.be.dizertatie.domain.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "challenge")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "Challenge.countAll", query = "SELECT COUNT(x) FROM Challenge x")
})
public class Challenge {

    @Id
    @SequenceGenerator(name = "challenge_sequence_generator", sequenceName = "challenge_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenge_sequence_generator")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany(mappedBy = "challengeList")
    private List<Account> accountList = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", targetEntity = ChallengeItem.class)
    private List<ChallengeItem> challengeItemList;


    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Course course;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public List<ChallengeItem> getChallengeItemList() {
        return challengeItemList;
    }

    public void setChallengeItemList(List<ChallengeItem> challengeItemList) {
        this.challengeItemList = challengeItemList;
    }
}
