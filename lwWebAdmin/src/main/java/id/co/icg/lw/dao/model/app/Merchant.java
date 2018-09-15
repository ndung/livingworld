package id.co.icg.lw.dao.model.app;

import id.co.icg.lw.dao.util.PojoModel;

import javax.persistence.*;

@Entity
@Table(name = "merchant")
public class Merchant extends PojoModel {

    private String id;
    private String name;
    private MerchantCategory category;

    @Id
    @Column(name="merchant_id", unique=true, nullable=false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="merchant_name", nullable=false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="merchant_category_id")
    public MerchantCategory getCategory() { return category; }

    public void setCategory(MerchantCategory category) {
        this.category = category;
    }
}
