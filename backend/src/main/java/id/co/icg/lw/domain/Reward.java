package id.co.icg.lw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;
import id.co.icg.lw.domain.merchant.Merchant;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name="reward")
public class Reward {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "reward_id")
    private long rewardId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Event event;

    @Column(name = "reward_name")
    String rewardName;

    @Column(name = "reward_point")
    int rewardPoint;

    @Column(name = "reward_image")
    String rewardImage;

    @Column(name = "reward_description")
    String rewardDescription;

    @OneToOne
    @JoinColumn(name="merchant_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Merchant merchant;

    private String active;

    public long getRewardId() {
        return rewardId;
    }

    public void setRewardId(long rewardId) {
        this.rewardId = rewardId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public String getRewardImage() {
        return rewardImage;
    }

    public void setRewardImage(String rewardImage) {
        this.rewardImage = rewardImage;
    }

    public String getRewardDescription() {
        return rewardDescription;
    }

    public void setRewardDescription(String rewardDescription) {
        this.rewardDescription = rewardDescription;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getActive() { return active; }

    public void setActive(String active) { this.active = active; }
}
