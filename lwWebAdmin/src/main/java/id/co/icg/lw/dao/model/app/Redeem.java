package id.co.icg.lw.dao.model.app;

import id.co.icg.lw.dao.util.PojoModel;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "redeem")
public class Redeem extends PojoModel {

    private Long id;
    private String code;
    private Member member;
    private Date createAt;
    private Date expiredDate;

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="redeem_id", unique=true, nullable=false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="code", nullable=false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="member_id")
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", updatable = false)
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expired_date")
    public Date getExpiredDate() { return expiredDate; }

    public void setExpiredDate(Date expiredDate) { this.expiredDate = expiredDate; }
}
