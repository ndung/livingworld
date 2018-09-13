package id.co.icg.ie.dao.model.app;

import id.co.icg.ie.dao.util.PojoModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_type")
public class MemberType extends PojoModel {
    private String id;
    private String name;

    public MemberType() {
    }

    @Id
    @Column(name="id", unique=true, nullable=false)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="name", nullable=false, length=200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
