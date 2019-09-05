package com.example.yummio.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class Cake implements Serializable {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("ingredients")
    ArrayList<Ingredients> ingredients;
    @SerializedName("steps")
    ArrayList<Steps> steps;
    @SerializedName("servings")
    int servings;
    @SerializedName("image")
    String image;

    public Cake(int id, String name, ArrayList<Ingredients> ingredients, ArrayList<Steps> steps, int servings) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Steps> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Steps> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public class Ingredients implements Serializable{
        @SerializedName("quantity")
        float quantity;
        @SerializedName("measure")
        String measure;
        @SerializedName("ingredient")
        String ingredient;

        public Ingredients(float quantity, String measure, String ingredient) {
            this.quantity = quantity;
            this.measure = measure;
            this.ingredient = ingredient;
        }

        public float getQuantity() {
            return quantity;
        }

        public void setQuantity(float quantity) {
            this.quantity = quantity;
        }

        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        public String getIngredient() {
            return ingredient;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }
    }

    public class Steps implements Serializable {
        @SerializedName("id")
        int id;
        @SerializedName("shortDescription")
        String shortDescription;
        @SerializedName("description")
        String description;
        @SerializedName("videoURL")
        String videoUrl;
        @SerializedName("thumbnailURL")
        String thumbnailUrl;

        public Steps(int id, String shortDescription, String description, String videoUrl, String thumbnailUrl) {
            this.id = id;
            this.shortDescription = shortDescription;
            this.description = description;
            this.videoUrl = videoUrl;
            this.thumbnailUrl = thumbnailUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }
    }
}
