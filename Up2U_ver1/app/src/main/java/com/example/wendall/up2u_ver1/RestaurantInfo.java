package com.example.wendall.up2u_ver1;

/**
 * Created by drkdagron on 2015-11-27.
 */
public class RestaurantInfo {
    public int ID;
    public String ResturantName;
    public float ResturantPrice;
    public String ResturantAddress;
    public String ResturantCatagory;

    public RestaurantInfo(int arg0, String arg1, float arg2, String arg3, String arg4) {
        this.ID = arg0;
        this.ResturantName = arg1;
        this.ResturantPrice = arg2;
        this.ResturantAddress = arg3;
        this.ResturantCatagory = arg4;
    }
}
