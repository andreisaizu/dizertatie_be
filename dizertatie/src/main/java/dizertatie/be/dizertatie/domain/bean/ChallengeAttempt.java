package dizertatie.be.dizertatie.domain.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "challenge_attempt")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "ChallengeAttempt.countAll", query = "SELECT COUNT(x) FROM ChallengeAttempt x")
})
public class ChallengeAttempt implements Comparable<ChallengeAttempt> {
    @Id
    @SequenceGenerator(name = "challenge_attempt_sequence_generator", sequenceName = "challenge_attempt_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenge_attempt_sequence_generator")
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdAt;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "challenge_id", referencedColumnName = "id", nullable = false)
    private Challenge challenge;

    @OneToMany(mappedBy = "challengeAttempt", targetEntity = ChallengeItemAttempt.class, cascade = CascadeType.PERSIST)
    private List<ChallengeItemAttempt> challengeItemAttemptList;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int compareTo(ChallengeAttempt o) {
        if (this.getCreatedAt() == null || o.getCreatedAt() == null) {
            return 0;
        }
        if (this.getCreatedAt().before(o.getCreatedAt())) {
            return 1;
        } else {
            return -1;
        }
    }
}
