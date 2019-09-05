package com.example.yummio;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yummio.model.Cake;
import com.example.yummio.utils.JSONUtils;
import com.example.yummio.utils.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainAdapter.MainAdapterOnClickHandler {

    public static ArrayList<Cake> cakeArrayList;
    private RecyclerView mRecyclerView;
    private ArrayList<Integer> cakeIds = new ArrayList<>();
    private ArrayList<String> cakeNames = new ArrayList<>();
    MainAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        myAdapter = new MainAdapter(this);

        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        loadCakes();
    }

    private void loadCakes() {
        new MyAsyncTask().execute();
    }

    @Override
    public void onClick(int cakeId) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        String cakeIdString = Integer.toString(cakeId);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, cakeIdString);
        startActivity(intentToStartDetailActivity);
    }

    public class MyAsyncTask extends AsyncTask<String, Void, ArrayList<Cake>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Cake> doInBackground(String... params) {
            String result = null;
            try {
                result = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ArrayList<Cake> cakes = JSONUtils.parseJson(result);
            return cakes;
        }

        @Override
        protected void onPostExecute(ArrayList<Cake> cakes) {
            cakeArrayList = cakes;
            if (!cakes.isEmpty()) {
                for (int i = 0; i < cakes.size(); i++) {
                    cakeIds.add(cakes.get(i).getId());
                    cakeNames.add(cakes.get(i).getName());
                }
            }
            showData(cakeIds, cakeNames);
        }
    }

    private void showData(ArrayList<Integer> ids, ArrayList<String> names) {
        myAdapter.setData(ids, names);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
