package com.example.deltatask3;

import java.util.ArrayList;

public class GunahKiList {

    String category;
    String persistent_id;
    private locations location;

    public class locations {
        String latitude;
        String longitude;

        public String getLatitude()
        {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }
    }

    GunahKiList()
    {
        category=persistent_id="NOTHING's UP";
        location = new locations();
    }



    public String getCategory()
    {
        return category;
    }

    public String getPersistent_id()
    {
        return persistent_id;
    }

    public locations getLocation()
    {
        return location;
    }
}
