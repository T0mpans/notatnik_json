package com.example.notatnik_json;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //url do jsona: "https://raw.githubusercontent.com/T0mpans/plikjson_do_notatnika/main/db.json";
    TextView textViewTresc;
    ListView listaWalut;
    List<Waluta> walutyZPliku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textViewTresc = findViewById(R.id.textView);
        listaWalut = findViewById(R.id.listView);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/T0mpans/plikjson_do_notatnika/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<List<Waluta>> call = jsonPlaceHolder.getWaluta();

        call.enqueue(
                new Callback<List<Waluta>>() {
                    @Override
                    public void onResponse(Call<List<Waluta>> call, Response<List<Waluta>> response) {
                        if (!response.isSuccessful()){
                            textViewTresc.setText("nie poszlo");
                            //Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        walutyZPliku = response.body();
                        textViewTresc.setText("Pytanie: "+walutyZPliku.get(0).getKraj());
                    }

                    @Override
                    public void onFailure(Call<List<Waluta>> call, Throwable t) {

                    }
                }
        );
    }
}