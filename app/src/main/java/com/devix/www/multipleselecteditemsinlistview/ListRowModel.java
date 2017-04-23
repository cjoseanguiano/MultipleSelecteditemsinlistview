package com.devix.www.multipleselecteditemsinlistview;
/*
 *Created by Carlos Anguiano on 23/04/17.
 *For more info contact : c.joseanguiano@gmail.com
 */

public class ListRowModel {
    private int ImageId;
    private String countryName;
    private String countryCode;

    public ListRowModel(int imageId, String countryName, String countryCode) {
        ImageId = imageId;
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "ListRowModel{" +
                "ImageId=" + ImageId +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
