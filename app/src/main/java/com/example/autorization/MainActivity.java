package com.example.autorization;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_CODE=112;
    private static final int STOCKAGE_PERMISSION_CODE=113;

    Button btnCamera,btnStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCamera=findViewById(R.id.btnCamera);
        btnStorage=findViewById(R.id.btnStockage);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(Manifest.permission.CAMERA,CAMERA_PERMISSION_CODE);

            }
        });
        btnStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE,STOCKAGE_PERMISSION_CODE);

            }
        });

    }
    public void checkPermission(String permission, int requestCode ) {
        //Verification de la permission
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            //take permission
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);


        } else {
            //permission t3aadet
            Toast.makeText(MainActivity.this, "Permission deja accordée", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==CAMERA_PERMISSION_CODE){
            // Vérification si l'utilisateur a accordé l'autorisation ou non.
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                btnCamera.setText("Permission accordée");
                Toast.makeText(MainActivity.this,"La permission de camera est accordée",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this,"La permission de camera est refusée",Toast.LENGTH_SHORT).show();


            }
        }else if (requestCode==STOCKAGE_PERMISSION_CODE){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                btnStorage.setText("Permission accordée");
                Toast.makeText(MainActivity.this,"La permission de stockage est accordée",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this,"La permission de stockage est refusée",Toast.LENGTH_SHORT).show();


            }
        }
    }
}
