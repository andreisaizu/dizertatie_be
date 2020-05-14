package dizertatie.be.dizertatie.domain.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "challenge_attempt")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "ChallengeAttempt.countAll", query = "SELECT COUNT(x) FROM ChallengeAttempt x")
})
public class ChallengeAttempt {
    @Id
    @SequenceGenerator(name = "challenge_attempt_sequence_generator", sequenceName = "challenge_attempt_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenge_attempt_sequence_generator" )
    @Column(name = "id", nullable = false)
    private Long id;

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Column(name = "score")
    private Integer score;

    @ManyToOne
    @JoinColumn(name="challenge_id",referencedColumnName="id", nullable=false)
    private Challenge challenge;

    @OneToMany(mappedBy="challengeAttempt", targetEntity= ChallengeItemAttempt.class, cascade = CascadeType.PERSIST)
    private List<ChallengeItemAttempt> challengeItemAttemptList;

    @ManyToOne
    @JoinColumn(name="account_id",referencedColumnName="id", nullable=false)
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ChallengeItemAttempt> getChallengeItemAttemptList() {
        return challengeItemAttemptList;
    }

    public void setChallengeItemAttemptList(List<ChallengeItemAttempt> challengeItemAttemptList) {
        this.challengeItemAttemptList = challengeItemAttemptList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
