package com.example.notatnik_json;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
    EditText iloscPieniedzy;
    TextView wynik;
    Button przyciskOblicz;
    List<Waluta> walutyZPliku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //listaWalut = findViewById(R.id.listView);
        iloscPieniedzy = findViewById(R.id.editTextIloscPieniedzy);
        wynik = findViewById(R.id.textViewWynik);
        przyciskOblicz = findViewById(R.id.button);

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
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        elementyListy
                );

                //listaWalut.setAdapter(adapter);

                przyciskOblicz.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String podanaIloscPieniedzy = iloscPieniedzy.getText().toString();
                                int podanaIloscPieniedzyInt = Integer.parseInt(podanaIloscPieniedzy);

                                String tekstWyniku = "";

                                for (Waluta waluta : walutyZPliku) {
                                    String nazwa2 = waluta.getNazwaWaluty();
                                    double wartosc = waluta.getWartoscWaluty();

                                    double wynikObliczenia = podanaIloscPieniedzyInt * wartosc;

                                    tekstWyniku += nazwa2 + ": " + wynikObliczenia + "\n";
                                }

                                //wynik.setVisibility();
                                wynik.setText(tekstWyniku);
                            }
                        }
                );
            }

            @Override
            public void onFailure(Call<List<Waluta>> call, Throwable t) {

            }
        });
    }
}