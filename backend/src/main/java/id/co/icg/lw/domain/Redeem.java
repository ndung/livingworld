package id.co.icg.lw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.co.icg.lw.domain.user.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Redeem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "redeem_id")
    private long redeemId;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="redeemId", cascade = {CascadeType.ALL})
    @JsonIgnore
    private List<RedeemedReward> redeemedRewards;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private User user;

    private String code;

    public long getRedeemId() {
        return redeemId;
    }

    public void setRedeemId(long redeemId) {
        this.redeemId = redeemId;
    }

    public List<RedeemedReward> getRedeemedRewards() {
        return redeemedRewards;
    }

    public void setRedeemedRewards(List<RedeemedReward> redeemedRewards) {
        this.redeemedRewards = redeemedRewards;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
