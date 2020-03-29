package com.fatima.bitcointicker;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;



import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;
import android.content.Intent;;
//import cz.msebera.android.httpclient.Header;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
public class Home extends AppCompatActivity {

    private final String BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC";
    String APIKKEY="YWRmMmZlMWU4NTQ2NDlkMGFjOTc5ZjRhNTQ0YzJjMTY";


    @Override
public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Spinner spinner=(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.currency_array,R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Bitcoin","Item selected is: "+spinner.getItemAtPosition(position));
                letsDoSomeNetworking(BASE_URL+spinner.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("Bitcoin","Nothing has been selected");

            }
        });








    }
    protected void letsDoSomeNetworking(String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("x-ba-key","YWEyYTVlMzg5OTE0NDBmYWI3ZDA1M2ZkMmJkM2MzMDE");
        client.get(url,new JsonHttpResponseHandler(){



            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("Bitcoin", "JSON: " + response.toString());
                try{
                    String price2=response.getString("ask");
                    updateUI(price2);}
                catch (org.json.JSONException e){
                    e.printStackTrace();


                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("Bitcoin", "Request fail! Status code: " + statusCode);
                Log.d("Bitcoin", "Fail response: " + response);
                Log.e("ERROR", e.toString());

            }
        });






    }
    protected void updateUI(String price2){
        TextView mp= findViewById(R.id.price);
        mp.setText("PRICE "+price2);






    }


}
