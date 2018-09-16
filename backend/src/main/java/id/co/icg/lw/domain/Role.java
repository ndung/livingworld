package id.co.icg.lw.domain;


import id.co.icg.lw.enums.RoleEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
    @Id
    private int id;
    private String name;

    public Role() {
    }

    public Role(RoleEnum roleEnum){
        this.id = roleEnum.getValue();
        this.name = roleEnum.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
