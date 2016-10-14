package com.example.user.sqlite.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.sqlite.Config;
import com.example.user.sqlite.Model.Person;

import java.util.ArrayList;

public class Db {
    DataBaseHelper dataBaseHelper;

    public Db(Context context) {
        this.dataBaseHelper = new DataBaseHelper(context);
    }

    public ArrayList<Person> getAllPersons()
    {
        ArrayList<Person> persons = new ArrayList<Person>();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(Config.COMMAND_SELECT, null);
        Person person = null;
        if (cursor.moveToFirst())
        {
            do{
                person = new Person();
                person.setId(Integer.parseInt(cursor.getString(0)));
                person.setImg(cursor.getBlob(1));
                person.setName(cursor.getString(2));
                person.setSurname(cursor.getString(3));
                person.setAge(Integer.parseInt(cursor.getString(4)));
                persons.add(person);
            }while (cursor.moveToNext());
        }
        return persons;
    }

    public ArrayList<Person> getPerson(int id)
    {
        ArrayList<Person> persons = new ArrayList<Person>();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor =
                db.query(Config.TABLE_PERSON,      // 1. table
                        null,                      // 2. column names
                        Config.KEY_ID + " = "+ id, // 3. selections
                        null,                      // 4. selections args
                        null,                      // 5. group by
                        null,                      // 6. having
                        null,                      // 7. order by
                        null);                     // 8. limit

        if (cursor != null){
            cursor.moveToFirst();
        }

        Person person = new Person();
        person.setId(Integer.parseInt(cursor.getString(0)));
        person.setImg(cursor.getBlob(1));
        person.setName(cursor.getString(2));
        person.setSurname(cursor.getString(3));
        person.setAge(Integer.parseInt(cursor.getString(4)));
        persons.add(person);
        db.close();
        return persons ;
    }

    public void addPerson(Person person)
    {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Config.KEY_ID, person.getId());
        values.put(Config.KEY_PHOTO, person.getImg());
        values.put(Config.KEY_NAME, person.getName());
        values.put(Config.KEY_SURNAME, person.getSurname());
        values.put(Config.KEY_AGE,person.getAge());
        db.insert(Config.TABLE_PERSON, null, values);
        db.close();
    }

    public void deleteAllPerson()
    {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        db.delete(Config.TABLE_PERSON, null, null);
        db.close();
    }

    public void deletePerson(int id)
    {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        db.delete(Config.TABLE_PERSON, Config.KEY_ID + " = "+ id, null);
        db.close();
    }
    public void updatePerson(int id, byte[] photo, String name, String surname, int age)
    {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Photo", photo);
        values.put("Name", name);
        values.put("SurName", surname);
        values.put("Age", age);
        db.update(Config.TABLE_PERSON, values, Config.KEY_ID + " = "+ id,null);
        db.close();
    }
}
