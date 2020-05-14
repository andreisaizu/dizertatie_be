package dizertatie.be.dizertatie.domain.bean;

import javax.persistence.*;

@Entity
@Table(name = "challenge_item_task_attempt")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "ChallengeItemTaskAttempt.countAll", query = "SELECT COUNT(x) FROM ChallengeItemTaskAttempt x")
})
public class ChallengeItemTaskAttempt {
    @Id
    @SequenceGenerator(name = "challenge_item_task_attempt_sequence_generator", sequenceName = "challenge_item_task_attempt_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenge_item_task_attempt_sequence_generator" )
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="challenge_item_attempt_id",referencedColumnName="id", nullable=false)
    private ChallengeItemAttempt challengeItemAttempt;

    @ManyToOne
    @JoinColumn(name="challenge_item_task_id",referencedColumnName="id", nullable=false)
    private ChallengeItemTask challengeItemTask;

    @Column(name = "valid", nullable = false)
    private Boolean valid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChallengeItemTask getChallengeItemTask() {
        return challengeItemTask;
    }

    public void setChallengeItemTask(ChallengeItemTask challengeItemTask) {
        this.challengeItemTask = challengeItemTask;
    }

    public ChallengeItemAttempt getChallengeItemAttempt() {
        return challengeItemAttempt;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public void setChallengeItemAttempt(ChallengeItemAttempt challengeItemAttempt) {
        this.challengeItemAttempt = challengeItemAttempt;
    }
}
