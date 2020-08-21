package dizertatie.be.dizertatie.domain.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "challenge_item_attempt")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "ChallengeItemAttempt.countAll", query = "SELECT COUNT(x) FROM ChallengeItemAttempt x")
})
public class ChallengeItemAttempt {
    @Id
    @SequenceGenerator(name = "challenge_item_attempt_sequence_generator", sequenceName = "challenge_item_attempt_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenge_item_attempt_sequence_generator" )
    @Column(name = "id", nullable = false)
    private Long id;

    public ChallengeItem getChallengeItem() {
        return challengeItem;
    }

    public void setChallengeItem(ChallengeItem challengeItem) {
        this.challengeItem = challengeItem;
    }

    @ManyToOne
    @JoinColumn(name="challenge_item_id",referencedColumnName="id", nullable=false)
    @JsonBackReference
    private ChallengeItem challengeItem;

    @ManyToOne
    @JoinColumn(name="challenge_attempt_id",referencedColumnName="id", nullable=false)
    @JsonBackReference
    private ChallengeAttempt challengeAttempt;

    @OneToMany(mappedBy="challengeItemAttempt", targetEntity= ChallengeItemTaskAttempt.class, cascade = CascadeType.PERSIST)
    private List<ChallengeItemTaskAttempt> challengeItemTaskAttemptList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChallengeAttempt getChallengeAttempt() {
        return challengeAttempt;
    }

    public void setChallengeAttempt(ChallengeAttempt challengeAttempt) {
        this.challengeAttempt = challengeAttempt;
    }

    public List<ChallengeItemTaskAttempt> getChallengeItemTaskAttemptList() {
        return challengeItemTaskAttemptList;
    }

    public void setChallengeItemTaskAttemptList(List<ChallengeItemTaskAttempt> challengeItemTaskAttemptList) {
        this.challengeItemTaskAttemptList = challengeItemTaskAttemptList;
    }
}
