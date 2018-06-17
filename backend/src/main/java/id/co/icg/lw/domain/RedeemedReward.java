package id.co.icg.lw.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class RedeemedReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="redeem_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Redeem redeemId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="reward_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Reward rewardId;

    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Redeem getRedeemId() {
        return redeemId;
    }

    public void setRedeemId(Redeem redeemId) {
        this.redeemId = redeemId;
    }

    public Reward getRewardId() {
        return rewardId;
    }

    public void setRewardId(Reward rewardId) {
        this.rewardId = rewardId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;


    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @PrePersist
    protected void onCreate() {
        createAt = updateAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = new Date();
    }

}
