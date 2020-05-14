package dizertatie.be.dizertatie.domain.bean;

import javax.persistence.*;

@Entity
@Table(name = "choice_item")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "ChoiceItem.countAll", query = "SELECT COUNT(x) FROM ChoiceItem x")
})
public class ChoiceItem {
    @Id
    @SequenceGenerator(name = "choice_sequence_generator", sequenceName = "choice_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "choice_sequence_generator" )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "value", nullable = false)
    private String value;


    @Column(name = "correct", nullable = false)
    private Boolean correct;

    public ChallengeItemTask getChallengeItemTask() {
        return challengeItemTask;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public void setChallengeItemTask(ChallengeItemTask challengeItemTask) {
        this.challengeItemTask = challengeItemTask;
    }

    @ManyToOne
    @JoinColumn(name = "challenge_item_task_id", referencedColumnName = "id", nullable = false)
    private ChallengeItemTask challengeItemTask;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
