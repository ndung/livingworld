package id.co.icg.lw.dao.model.app;

import id.co.icg.lw.dao.util.PojoModel;

import javax.persistence.*;

@Entity
@Table(name = "merchant_category")
public class MerchantCategory extends PojoModel {

    private String id;
    private String name;

    @Id
    @Column(name="merchant_category_id", unique=true, nullable=false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="merchant_category_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
