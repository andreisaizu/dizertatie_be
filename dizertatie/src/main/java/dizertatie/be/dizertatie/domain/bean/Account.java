package dizertatie.be.dizertatie.domain.bean;

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
