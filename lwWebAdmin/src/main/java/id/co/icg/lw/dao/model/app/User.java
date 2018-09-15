/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.lw.dao.model.app;

import id.co.icg.lw.dao.util.PojoModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author fauzimarjalih@gmail.com
 */
@Entity
@Table(name = "user")
public class User extends PojoModel {

    private static final long serialVersionUID = 1L;

    private String id;
    private String password;
    private String fullName;
    private String phone;
    private String email;
    private int status;
    private String imagePath;
    private AppAdminRole appRole;
    private Merchant merchant;

    public User() {
    }

    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "mobile_number", nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "photo_profile_url")
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")
    public AppAdminRole getAppRole() {
        return appRole;
    }

    public void setAppRole(AppAdminRole role) {
        this.appRole = role;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="merchant_id")
    public Merchant getMerchant() { return merchant; }

    public void setMerchant(Merchant merchant) { this.merchant = merchant; }
}
