package com.example.pmd_hilossecundarios;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button botonDescarga;
    ProgressDialog miProgressDialog;
    Handler miHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miHandler = new Handler();
        botonDescarga = (Button) findViewById(R.id.bDescarga);

        botonDescarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarProgressDialosg();
            }
        });

    }

    public void lanzarProgressDialosg(){
       miProgressDialog = new ProgressDialog(MainActivity.this);
       miProgressDialog.setTitle("Simulando descarga");
       miProgressDialog.setMessage("Actualizando datos...");
       miProgressDialog.setProgressStyle(miProgressDialog.STYLE_HORIZONTAL);
       miProgressDialog.setProgress(0);
       miProgressDialog.setMax(100);
       miProgressDialog.show();

       new Thread(new Runnable() {
           @Override
           public void run() {
                try{
                    while(miProgressDialog.getProgress() <= miProgressDialog.getMax()){
                        Thread.sleep(2000);
                        miHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                miProgressDialog.incrementProgressBy(10);
                            }
                        });
                        if(miProgressDialog.getProgress() == miProgressDialog.getMax()){
                            miProgressDialog.dismiss();
                        }
                    }
                }catch (Exception exception){
                    exception.printStackTrace();
                }
           }
       }).start();

    }
}