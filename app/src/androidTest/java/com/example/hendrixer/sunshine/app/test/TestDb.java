package com.example.hendrixer.sunshine.app.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import com.example.hendrixer.sunshine.app.data.WeatherContract.LocationEntry;
import com.example.hendrixer.sunshine.app.data.WeatherDbHelper;

/**
 * Created by Hendrixer on 7/27/14.
 */
public class TestDb extends AndroidTestCase {
    public void testCreateDb() throws Throwable {
        mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new WeatherDbHelper(this.mContext)
                .getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

    private static String LOG_TAG = TestDb.class.getSimpleName();

    public void testInsertReadDb() {
        String testName = "North Pole";
        String testLocationSetting = "99705";
        double testLatitude = 64.772;
        double testLongitude = -147.355;

        WeatherDbHelper dbHelper =
                new WeatherDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LocationEntry.COLUMN_CITY_NAME, testName);
        values.put(LocationEntry.COLUMN_LOCATION_SETTING, testLocationSetting);
        values.put(LocationEntry.COLUMN_COORD_LAT, testLatitude);
        values.put(LocationEntry.COLUMN_COORD_LONG, testLongitude);

        long locationRowId;
        locationRowId = db.insert(LocationEntry.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "row id here!! "+ locationRowId);
        assertTrue(locationRowId != -1);
        Log.d(LOG_TAG, "New row id: "+ locationRowId);


        String[] columns = {
                LocationEntry._ID,
                LocationEntry.COLUMN_LOCATION_SETTING,
                LocationEntry.COLUMN_CITY_NAME,
                LocationEntry.COLUMN_COORD_LAT,
                LocationEntry.COLUMN_COORD_LONG
        };

        Cursor cursor = db.query(
                LocationEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            int locationIndex = cursor.getColumnIndex(LocationEntry.COLUMN_LOCATION_SETTING);
            String location = cursor.getString(locationIndex);

            int nameIndex = cursor.getColumnIndex(LocationEntry.COLUMN_CITY_NAME);
            String name = cursor.getString(nameIndex);

            int latIndex = cursor.getColumnIndex(LocationEntry.COLUMN_COORD_LAT);
            double latitude = cursor.getDouble(latIndex);

            int longIndex = cursor.getColumnIndex(LocationEntry.COLUMN_COORD_LONG);
            double longitude = cursor.getDouble(longIndex);


            assertEquals(testName, name);
            assertEquals(testLocationSetting, location);
            assertEquals(testLatitude, latitude);
            assertEquals(testLongitude, longitude);

        } else {
            fail("No values returned :(((( ");
        }
    }

}
