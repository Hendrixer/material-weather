package com.example.hendrixer.sunshine.app.data;

import android.provider.BaseColumns;

/**
 * Created by Hendrixer on 7/27/14.
 */
public class WeatherContract {

    public static final class WeatherEntry implements BaseColumns{

        public static final String TABLE_NAME = "weather";


        public static final String COLUMN_LOC_KEY = "location_id";

        public static final String COLUMN_DATETEXT = "date";

        public static final String COLUMN_WEATHER_ID =  "weather_id";


        public static final String COLUMN_SHORT_DESC = "short_desc";

        public static final String COLUMN_MIN_TEMP = "min";
        public static final String COLUMN_MAX_TEMP = "max";

        public static final String COLUMN_HUMIDUTY = "humidity";
        public static final String COLUMN_PRESSURE = "pressure";
        public static final String COLUMN_WINDSPEED = "wind_speed";
        public static final String COLUMN_DEGREES = "degress";

    }


    public class LocationEntry implements BaseColumns{
        public static final String TABLE_NAME = "location";
        public static final String COLUMN_POSTAL_CODE = "postal_code";
        public static final String COLUMN_CITY_NAME = "city_name";
        public static final String COLUMN_COORD_LAT = "lat";
        public static final String COLUMN_COORD_LONG = "long";
        public static final String COLUMN_LOCATION_SETTING = "location_setting";


    }
}
