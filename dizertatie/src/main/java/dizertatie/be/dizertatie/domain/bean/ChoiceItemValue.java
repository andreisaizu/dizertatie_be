package dizertatie.be.dizertatie.domain.bean;

import javax.persistence.*;

@Entity
@Table(name = "choice_item_value")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "ChoiceItemValue.countAll", query = "SELECT COUNT(x) FROM ChoiceItemValue x")
})
public class ChoiceItemValue {
    @Id
    @SequenceGenerator(name = "choice_sequence_generator", sequenceName = "choice_item_value_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "choice_sequence_generator" )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "value", nullable = false)
    private String value;


    @Column(name = "order")
    private Integer order;

    public ChoiceItem getChoiceItem() {
        return choiceItem;
    }

    public void setChoiceItem(ChoiceItem choiceItem) {
        this.choiceItem = choiceItem;
    }

    @ManyToOne
    @JoinColumn(name = "choice_item_id", referencedColumnName = "id", nullable = false)
    private ChoiceItem choiceItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
