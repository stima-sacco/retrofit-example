package com.example.retrofitexample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.retrofitexample.databinding.ActivityMainBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private String p_MerchantRequestID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        Handler handler = new Handler();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObject json = new JsonObject();

                try {
                    json.addProperty("mobileNo", "0725036299");
                    json.addProperty("amount", "1");
                    json.addProperty("name", "Kevin");
                }
                catch(Exception e){
                    e.printStackTrace();
                }

                Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<MpesaResponse> call = methods.clientToBusiness(json);

                call.enqueue(new Callback<MpesaResponse>() {
                    @Override
                    public void onResponse(Call<MpesaResponse> call, Response<MpesaResponse> response) {
                        String strOutput = response.toString();
                        String MerchantRequestID =  response.body().getMerchantRequestID();
                        String ResponseCode = response.body().getResponseCode();
                        String ResponseDescription = response.body().getResponseDescription();
                        String CheckoutRequestID = response.body().getCheckoutRequestID();
                        String CustomerMessage = response.body().getCustomerMessage();

                        p_MerchantRequestID = MerchantRequestID;

                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                downloadBookIf();
                            }
                        };

                        handler.postDelayed(runnable, 3500);
                    }

                    @Override
                    public void onFailure(Call<MpesaResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Error Occurred",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void downloadBookIf()
    {
        JsonObject json = new JsonObject();

        try {
            json.addProperty("MerchantRequestID", p_MerchantRequestID);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<TransactionResponse> call = methods.transactionResponse(json);

        call.enqueue(new Callback<TransactionResponse>() {
            @Override
            public void onResponse(Call<TransactionResponse> call, Response<TransactionResponse> response) {

                if(response != null) {
                    String strOutput = response.toString();
                    String message = response.body().getMessage();
                    String resultCode = response.body().getResultCode();
                    String TransactionDate = response.body().getTransactionDate();
                    String MobileNo = response.body().getMobileNo();

                    if(resultCode == "0")
                    {
                        // OPEN PDF FOR USER
                    }
                }
            }

            @Override
            public void onFailure(Call<TransactionResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error Occurred",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}