package com.ir_tech.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity
{
    Button getServerData_BTN;
    TextView serverResponse;
    String server_url = "http://192.168.0.105/zone/greetings.php";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serverResponse = findViewById(R.id.serverResponseTextView);
        getServerData_BTN = findViewById(R.id.serverbtnid);

        getServerData_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest string_request = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                serverResponse.setText(response);
                                requestQueue.stop();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        serverResponse.setText("Something went wrong");
                        error.printStackTrace();
                        requestQueue.stop();
                    }
                });
                requestQueue.add(string_request);
            }
        });

    }
}
