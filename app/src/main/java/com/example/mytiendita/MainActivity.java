package com.example.mytiendita;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
     EditText nombre, direccion, pedidooo;
     Button pedir,salir;
     String clie, direc,pedii;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String HttpUrl = "https://thetecnology.000webhostapp.com/php/Insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.edtnombre);
        direccion= findViewById(R.id.edtdirec);
        pedidooo= findViewById(R.id.edtpedido);
        pedir= findViewById(R.id.btnpedido);
        salir= findViewById(R.id.btnsalir);
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        progressDialog = new ProgressDialog(MainActivity.this);

        pedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // progressDialog.setMessage("Espera, Se esta insertando los datos en la base de datos");
                //progressDialog.show();
                GetValueFromEditText();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                               // progressDialog.dismiss();
                                //Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_SHORT).show();
                                if (!nombre.getText().toString().isEmpty()&&!direccion.getText().toString().isEmpty()&&!pedidooo.getText().toString().isEmpty()){
                                    MessageDialog();
                                }
                                else{
                                    error();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            private VolleyError error;
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                               // progressDialog.dismiss();
                               // Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                                if (!nombre.getText().toString().isEmpty()&&!direccion.getText().toString().isEmpty()&&!pedidooo.getText().toString().isEmpty()){
                                    error();
                                }
                                else{
                                    MessageDialog2();
                                }


                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("nombre",clie);
                        params.put("direccion",direc);
                        params.put("pedidoo",pedii);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(stringRequest);
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void GetValueFromEditText(){
        clie = nombre.getText().toString().trim();
        direc = direccion.getText().toString().trim();
        pedii = pedidooo.getText().toString().trim();
    }

    public void MessageDialog(){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Enhorabuena!!!")
                .setMessage("se ha realizado el pedido!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }
    public void MessageDialog2(){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Error!!!!")
                .setMessage("No se ha relizado el pedido!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }
    public void error(){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("error!!")
                .setMessage("No has ingresado ningun valor!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }
}