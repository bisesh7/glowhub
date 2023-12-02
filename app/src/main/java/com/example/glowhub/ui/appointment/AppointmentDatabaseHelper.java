package com.example.glowhub.ui.appointment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AppointmentsDB";
    private static final String TABLE_APPOINTMENTS = "appointments";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE_TIME = "dateTime";
    private static final String KEY_DESCRIPTION = "description";

    public AppointmentDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_APPOINTMENTS_TABLE = "CREATE TABLE " + TABLE_APPOINTMENTS +
                "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_TITLE + " TEXT," +
                KEY_DATE_TIME + " TEXT," +
                KEY_DESCRIPTION + " TEXT" +
                ")";
        db.execSQL(CREATE_APPOINTMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENTS);
        onCreate(db);
    }

    public long addAppointment(Appointment appointment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, appointment.getTitle());
        values.put(KEY_DATE_TIME, appointment.getDateTime());

        return db.insert(TABLE_APPOINTMENTS, null, values);
    }

    @SuppressLint("Range")
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointmentList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_APPOINTMENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Appointment appointment = new Appointment();
                appointment.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                appointment.setDateTime(cursor.getString(cursor.getColumnIndex(KEY_DATE_TIME)));

                appointmentList.add(appointment);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return appointmentList;
    }


    // Implement other methods like getAllAppointments(), updateAppointment(), deleteAppointment(), etc.
    // based on your application's requirements.
}
