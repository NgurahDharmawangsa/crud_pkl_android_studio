package com.example.crudpkl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //deklarasi variabel
    Spinner txtProdi, txtPembimbing, txtStatusPkl;
    EditText  txtNamaLengkap, txtNim, txtTempatDudi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mengambil id dari activity_main2.xml untuk di pakai di variabel di sebelah kiri
        txtNamaLengkap = (EditText) findViewById(R.id.txtNamaLengkap);
        txtNim = (EditText) findViewById(R.id.txtNim);
        txtTempatDudi = (EditText) findViewById(R.id.txtTempatDudi);
        txtProdi = (Spinner) findViewById(R.id.txtProdi);
        txtPembimbing = (Spinner) findViewById(R.id.txtPembimbing);
        txtStatusPkl = (Spinner) findViewById(R.id.txtStatusPkl);
        Button btnTampilData = findViewById(R.id.btnTampilData);

        //mensetting button btnTampilData agar ketika diklik
        //dapat menampilkan MainActivity2
        btnTampilData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent tampil = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(tampil);
            }

        });

    }

    public void btnSimpan(View view) {
        //mengambil isi dari variabel yang dikanan untuk disimpan ke variabel yang dikiri
        String namalengkap = txtNamaLengkap.getText().toString();
        String nim = txtNim.getText().toString();
        String tempatdudi = txtTempatDudi.getText().toString();
        String prodi = txtProdi.getSelectedItem().toString();
        String pembimbing = txtPembimbing.getSelectedItem().toString();
        String statuspkl = txtStatusPkl.getSelectedItem().toString();


        //mengeksekusi class object dengan parameternya
        simpanData simpen = new simpanData(this);
        simpen.execute(namalengkap, nim, tempatdudi, prodi, pembimbing, statuspkl);
    }
}