package com.alarayf.alarayf.models;

/**
 * Created by Mohammad on 11/26/16.
 */

public class ContactUs_data {

    private String Name;
    private String Email;
    private String Cell_phone;
    private String type_id;

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getCell_phone() {
        return Cell_phone;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        return Title;
    }

    public String getSDesc() {
        return SDesc;
    }

    private String Title;

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setCell_phone(String cell_phone) {
        Cell_phone = cell_phone;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setSDesc(String SDesc) {
        this.SDesc = SDesc;
    }

    private String SDesc;

}
