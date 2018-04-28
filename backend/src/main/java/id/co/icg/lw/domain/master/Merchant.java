package id.co.icg.lw.domain.master;

import java.util.List;

public class Merchant {
    private String id;
    private String category;
    private String name;
    private String description;
    private String phone;
    private String icon;
    private String image;
    private List<MerchantOfficeHour> officeHours;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<MerchantOfficeHour> getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(List<MerchantOfficeHour> officeHours) {
        this.officeHours = officeHours;
    }
}
