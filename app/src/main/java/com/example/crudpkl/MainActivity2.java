package com.example.crudpkl;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity2 extends AppCompatActivity implements ListView.OnItemClickListener{

    //deklarasi variabel
    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //mengambil id dari activity_main2.xml untuk di pakai di variabel di sebelah kiri
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();

        //mengambil id dari activity_main2.xml untuk di pakai di variabel di sebelah kiri
        Button btnKembali = findViewById(R.id.btnKembali);
        //mensetting button btnTampilData agar ketika diklik
        //dapat menampilkan MainActivity1
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(kembali);
            }
        });

    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        //membuat arraylist bernama list
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            //mengambil data dan menambahkannya ke arraylist list
            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String nama = jo.getString(konfigurasi.TAG_NAMALENGKAP);
                String nim = jo.getString(konfigurasi.TAG_NIM);
                String prodi = jo.getString(konfigurasi.TAG_PRODI);
                String namadudi = jo.getString(konfigurasi.TAG_NAMADUDI);
                String pembimbing = jo.getString(konfigurasi.TAG_PEMBIMBING);
                String statuspkl = jo.getString(konfigurasi.TAG_STATUSPKL);

                HashMap<String,String> barangg = new HashMap<>();
                barangg.put(konfigurasi.TAG_NAMALENGKAP,nama);
                barangg.put(konfigurasi.TAG_NIM,nim);
                barangg.put(konfigurasi.TAG_PRODI,prodi);
                barangg.put(konfigurasi.TAG_NAMADUDI,namadudi);
                barangg.put(konfigurasi.TAG_PEMBIMBING,pembimbing);
                barangg.put(konfigurasi.TAG_STATUSPKL,statuspkl);
                list.add(barangg);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //menampilkan data pada listview
        ListAdapter adapter = new SimpleAdapter(
                MainActivity2.this, list, R.layout.list_itembarang,
                new String[]{konfigurasi.TAG_NAMALENGKAP,konfigurasi.TAG_NIM,konfigurasi.TAG_PRODI,konfigurasi.TAG_NAMADUDI,konfigurasi.TAG_PEMBIMBING,konfigurasi.TAG_STATUSPKL},
                new int[]{R.id.nama, R.id.nim, R.id.prodi, R.id.namadudi, R.id.pembimbing, R.id.statuspkl});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity2.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}