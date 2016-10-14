package com.example.user.sqlite.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.sqlite.DB.Db;
import com.example.user.sqlite.Model.Person;
import com.example.user.sqlite.R;
import com.example.user.sqlite.UI.Adapter.AdapterRecyclerView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    EditText  editTextImageUrl;
    EditText  editTextId;
    EditText  editTextName;
    EditText  editTextSurname;
    EditText  editTextAge;
    Button    btnSelectAll;
    Button    btnSelectOne;
    Button    btnPut;
    Button    btnDeleteAll;
    Button    btnDeleteOne;
    Button    btnUpdate;
    Db db;

    RecyclerView rv;
    AdapterRecyclerView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Db(this);

        editTextImageUrl = (EditText)findViewById(R.id.textImageUrl);
        editTextId       = (EditText)findViewById(R.id.textId);
        editTextName     = (EditText)findViewById(R.id.textName);
        editTextSurname  = (EditText)findViewById(R.id.textSurName);
        editTextAge      = (EditText)findViewById(R.id.textAge);

        rv = (RecyclerView)findViewById(R.id.recyclertview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        btnSelectAll = (Button)findViewById(R.id.buttonSelectAll);
        btnSelectAll.setOnClickListener(this);

        btnSelectOne = (Button)findViewById(R.id.buttonSelectOne);
        btnSelectOne.setOnClickListener(this);

        btnPut = (Button)findViewById(R.id.buttonPut);
        btnPut.setOnClickListener(this);

        btnDeleteAll = (Button)findViewById(R.id.buttonDeleteAll);
        btnDeleteAll.setOnClickListener(this);

        btnDeleteOne = (Button)findViewById(R.id.buttonDeleteOne);
        btnDeleteOne.setOnClickListener(this);

        btnUpdate = (Button)findViewById(R.id.buttonUpdate);
        btnUpdate.setOnClickListener(this);
    }

    private byte[] getLogoImage(String url){
        try {
            URL imageUrl = new URL(url);
            URLConnection ucon = imageUrl.openConnection();

            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[500];
            int current = 0;
            while((current = bis.read(data, 0, data.length)) != -1){
                buffer.write(data, 0, current);
            }

            return buffer.toByteArray();
        } catch (Exception e) {
            Log.d("ImageManager", "Error: " + e.toString());
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        byte[] img     = getLogoImage("http://ddragon.leagueoflegends.com/cdn/6.17.1/img/champion/Akali.png"); // editTextImageUrl.getText().toString()
        int id         = Integer.parseInt( editTextId.getText().toString() );
        String name    = editTextName.getText().toString();
        String surname = editTextSurname.getText().toString();
        int age        = Integer.parseInt( editTextAge.getText().toString() );

        switch( v.getId() )
        {
            case R.id.buttonSelectAll:
                adapter = new AdapterRecyclerView(db.getAllPersons(), this);
                rv.setAdapter(adapter);
                break;

            case R.id.buttonSelectOne:
                adapter = new AdapterRecyclerView(db.getPerson(id), this);
                rv.setAdapter(adapter);
                break;

            case R.id.buttonPut:
                db.addPerson(new Person(id, img, name, surname, age));
                break;

            case R.id.buttonDeleteAll:
                db.deleteAllPerson();
                break;

            case R.id.buttonDeleteOne:
                db.deletePerson(id);
                break;

            case R.id.buttonUpdate:
                db.updatePerson(id, img, name, surname, age);
                break;
        }
    }
}
