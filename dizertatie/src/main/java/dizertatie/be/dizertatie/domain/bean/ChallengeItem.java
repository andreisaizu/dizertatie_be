package dizertatie.be.dizertatie.domain.bean;

import dizertatie.be.dizertatie.enums.ChallengeItemType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "challenge_item")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "ChallengeItem.countAll", query = "SELECT COUNT(x) FROM ChallengeItem x")
})
public class ChallengeItem {
    @Id
    @SequenceGenerator(name = "challenge_item_sequence_generator", sequenceName = "challenge_item_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenge_item_sequence_generator" )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "challenge_item_type", nullable = false)
    private String challengeItemType;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="challenge_id",referencedColumnName="id", nullable=false)
    private Challenge challenge;

    @OneToMany(mappedBy="challengeItem", targetEntity= ChallengeItemTask.class, cascade = CascadeType.PERSIST)
    private List<ChallengeItemTask> challengeItemTaskList;

    public String getChallengeItemType() {
        return challengeItemType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setChallengeItemType(String challengeItemType) {
        this.challengeItemType = challengeItemType;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public List<ChallengeItemTask> getChallengeItemTaskList() {
        return challengeItemTaskList;
    }

    public void setChallengeItemTaskList(List<ChallengeItemTask> challengeItemTaskList) {
        this.challengeItemTaskList = challengeItemTaskList;
    }
}
