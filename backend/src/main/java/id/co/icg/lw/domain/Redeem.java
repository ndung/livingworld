package id.co.icg.lw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.co.icg.lw.domain.user.Member;
import id.co.icg.lw.domain.user.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="redeem")
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
    @JoinColumn(name="member_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Member member;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", updatable = false)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expired_date")
    private Date expiredDate;

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

    public Member getMember() { return member; }

    public void setMember(Member member) { this.member = member; }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateAt() { return createAt; }

    public void setCreateAt(Date createAt) { this.createAt = createAt; }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
}
