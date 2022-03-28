package ru.samsung.itschool.mdev.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private AnekdotAPI anekdotapi;
    private ArrayList<Anekdot> anekdotArrayList;
    private RecyclerView recyclerView;
    public static final String BASE_URL = "https://umorili.herokuapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anekdotArrayList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(anekdotArrayList);
        recyclerView.setAdapter(adapter);



        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        anekdotapi = retrofit.create(AnekdotAPI.class);

        anekdotapi.getAnekdots("anekdot.ru",10,"new anekdot").enqueue(new Callback<ArrayList<Anekdot>>() {
            @Override
            public void onResponse(Call<ArrayList<Anekdot>> call, Response<ArrayList<Anekdot>> response) {
                Log.d("RRRR",response.body() + "");
                    anekdotArrayList.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged(); // данные в адаптере изменены!
                Log.d("RRRR",anekdotArrayList.get(0).getElementPureHtml());
            }

            @Override
            public void onFailure(Call<ArrayList<Anekdot>> call, Throwable t) {
                Log.d("RRRR",t.getMessage());
            }
        });


    }
}