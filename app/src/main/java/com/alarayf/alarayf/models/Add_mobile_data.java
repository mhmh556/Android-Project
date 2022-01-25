package com.alarayf.alarayf.models;

/**
 * Created by Mohammad on 3/15/18.
 */

public class Add_mobile_data {

        private String Name;
        private String Country;
        private String SDesc;
        private String Title;
        private String Cell_phone;
        private String Type_id;

    public void setName(String name) {
        Name = name;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setSDesc(String SDesc) {
        this.SDesc = SDesc;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setCell_phone(String cell_phone) {
        Cell_phone = cell_phone;
    }

    public void setType_id(String type_id) {
        Type_id = type_id;
    }

    public String getName() {
        return Name;
    }

    public String getCountry() {
        return Country;
    }

    public String getSDesc() {
        return SDesc;
    }

    public String getTitle() {
        return Title;
    }

    public String getCell_phone() {
        return Cell_phone;
    }

    public String getType_id() {
        return Type_id;
    }
}
