package com.example.yummio.utils;

import com.example.yummio.model.Cake;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class JSONUtils {
    public static ArrayList<Cake> parseJson(String json) {
        ArrayList<Cake> cakes = new ArrayList<>();
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<Collection<Cake>>() {}.getType();
            cakes = gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cakes;
    }
}
