package dizertatie.be.dizertatie.domain.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "challenge_item_task")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "ChallengeItemTask.countAll", query = "SELECT COUNT(x) FROM ChallengeItemTask x")
})
public class ChallengeItemTask {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "question", nullable = false)
    private String question;

    @OneToMany(mappedBy="challengeItemTask", targetEntity= ChoiceItem.class)
    private List<ChoiceItem> choiceItemList;

    @ManyToOne
    @JoinColumn(name="challenge_item_id", referencedColumnName="id",  nullable=false)
    private ChallengeItem challengeItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<ChoiceItem> getChoiceItemList() {
        return choiceItemList;
    }

    public void setChoiceItemList(List<ChoiceItem> choiceItemList) {
        this.choiceItemList = choiceItemList;
    }

    public ChallengeItem getChallengeItem() {
        return challengeItem;
    }

    public void setChallengeItem(ChallengeItem challengeItem) {
        this.challengeItem = challengeItem;
    }

}
