package com.example.wendall.up2u_ver1;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by drkdagron on 2015-11-27.
 */
public class LocalData {
    //added comment
    private static LocalData local;
    private List<RestaurantInfo> info;

    public LocalData()
    {
        BuildData();
    }

    private void BuildData()
    {
        info = new ArrayList<RestaurantInfo>();

        for (int i= 0; i < 100; i++)
        {
            String s = "";
            switch (i % 4)
            {
                case 0:
                    s = "Food & Drinks";
                    break;
                case 1:
                    s = "Banks";
                    break;
                case 2:
                    s = "Parks & Rec";
                    break;
                case 3:
                    s = "Entertainment";
                    break;
            }

            info.add(new RestaurantInfo(i, "Activity " + i, i + 0.99f, i + " Fake St.", s));
        }
    }

    public synchronized static LocalData getInstance()
    {
        if (local == null)
            local = new LocalData();

        return local;
    }

    public RestaurantInfo getInfoatID(int id)
    {
        for (int i =0 ; i < 100; i++)
        {
            if (info.get(i).ID == id)
                return info.get(id);
        }

        return null;
    }

    public List<RestaurantInfo> getInfo()
    {
        return info;
    }
}
