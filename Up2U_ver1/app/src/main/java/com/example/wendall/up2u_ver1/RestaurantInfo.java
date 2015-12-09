package com.example.wendall.up2u_ver1;

import android.app.Activity;

/**
 * Created by drkdagron on 2015-11-27.
 */
public class RestaurantInfo {
    public int ID;
    public String ActivityName;
    public float ActivityPrice;
    public String ActivityAddress;
    public String ActivityCategory;
    public String ActivityTown;

    public RestaurantInfo(int arg0, String arg1, float arg2, String arg3, String arg4) {
        this.ID = arg0;
        this.ActivityName = arg1;
        this.ActivityPrice = arg2;
        this.ActivityAddress = arg3;
        this.ActivityCategory = arg4;
        switch (this.ID % 7)
        {
            case 0:
                ActivityTown = "Agincourt";
                break;
            case 1:
                ActivityTown = "Markham";
                break;
            case 2:
                ActivityTown = "Mississauga";
                break;
            case 3:
                ActivityTown = "North York";
                break;
            case 4:
                ActivityTown = "Scarborough";
                break;
            case 5:
                ActivityTown = "Toronto";
                break;
            case 6:
                ActivityTown = "York";
                break;
        }

    }

    public String toString() {
        return "Name: " + ActivityName + "\tPrice: " + ActivityPrice  + "\tAddress: " + ActivityAddress + "\tCategory: " + ActivityCategory + "\n";
    }
}
