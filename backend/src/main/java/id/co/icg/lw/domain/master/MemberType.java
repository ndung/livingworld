package id.co.icg.lw.domain.master;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_type")
public class MemberType extends Master {

    @Id
    private String id;
    private String name;

    public MemberType(){

    }

    public MemberType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String minimumTransaction;

    public String getMinimumTransaction() {
        return minimumTransaction;
    }

    public void setMinimumTransaction(String minimumTransaction) {
        this.minimumTransaction = minimumTransaction;
    }
}
