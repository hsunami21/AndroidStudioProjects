package com.example.wendall.up2u_ver1;

/**
 * Created by drkdagron on 2015-11-27.
 */
public class RestaurantInfo {
    public int ID;
    public String ActivityName;
    public float ActivityPrice;
    public String ActivityAddress;
    public String ActivityCategory;

    public RestaurantInfo(int arg0, String arg1, float arg2, String arg3, String arg4) {
        this.ID = arg0;
        this.ActivityName = arg1;
        this.ActivityPrice = arg2;
        this.ActivityAddress = arg3;
        this.ActivityCategory = arg4;
    }

    public String toString() {
        return "Name: " + ActivityName + "\tPrice: " + ActivityPrice  + "\tAddress: " + ActivityAddress + "\tCategory: " + ActivityCategory + "\n";
    }
}
