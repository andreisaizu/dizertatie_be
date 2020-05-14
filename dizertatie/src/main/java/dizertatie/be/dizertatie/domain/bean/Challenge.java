package dizertatie.be.dizertatie.domain.bean;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenge_sequence_generator" )
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany(mappedBy = "challengeList")
    private List<Account> accountList = new ArrayList<>();

    @OneToMany(mappedBy="challenge", targetEntity= ChallengeItem.class)
    private List<ChallengeItem> challengeItemList;

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
