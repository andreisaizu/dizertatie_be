package dizertatie.be.dizertatie.domain.bean;

import dizertatie.be.dizertatie.controller.responses.ChallengeItemDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "role")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "Role.countAll", query = "SELECT COUNT(x) FROM Role x")
})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role", nullable = false)
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
