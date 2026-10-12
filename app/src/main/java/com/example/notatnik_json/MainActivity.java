package com.example.notatnik_json;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listaWalut;
    List<Waluta> walutyZPliku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listaWalut = findViewById(R.id.listView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/T0mpans/plikjson_do_notatnika/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder =
                retrofit.create(JsonPlaceHolder.class);

        Call<List<Waluta>> call = jsonPlaceHolder.getWaluta();

        call.enqueue(new Callback<List<Waluta>>() {

            @Override
            public void onResponse(
                    Call<List<Waluta>> call,
                    Response<List<Waluta>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Błąd: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                walutyZPliku = response.body();

                List<String> elementyListy = new ArrayList<>();

                for (Waluta waluta : walutyZPliku) {
                    elementyListy.add("Nazwa: " + waluta.getNazwaWaluty());
                    elementyListy.add("Kraj: " + waluta.getKraj());
                    elementyListy.add("Wartość: " + waluta.getWartoscWaluty());
                    elementyListy.add("=======================================");
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        elementyListy
                );

                listaWalut.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Waluta>> call, Throwable t) {
                Toast.makeText(
                        MainActivity.this,
                        "Błąd połączenia: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}