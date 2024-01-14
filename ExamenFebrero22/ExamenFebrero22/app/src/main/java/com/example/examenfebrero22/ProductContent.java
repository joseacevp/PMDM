package com.example.examenfebrero22;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductContent {


    //List of all the Products to be listed in the RecyclerView
    public static List<Product> ITEMS = new ArrayList<Product>();

    public static void clearProducts() {
        ITEMS.clear();
    }

    public static void loadProductsFromJSON(Context c) {

        String json = null;
        try {
            InputStream is =
                    c.getAssets().open("productsList.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray couchList = jsonObject.getJSONArray("products_list");
            for (int i = 0; i < couchList.length(); i++) {
                JSONObject jsonCouch = couchList.getJSONObject(i);
                String name = jsonCouch.getString("name");
                String category = jsonCouch.getString("category");
                String price = jsonCouch.getString("price");
                Bitmap photo = null;
                try {
                    photo = BitmapFactory.decodeStream(
                            c.getAssets().open("imagenes/" +
                                    jsonCouch.getString("image")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ITEMS.add(new Product(photo, name, category, price));
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

    }

    public static class Product {
        private Bitmap photo;
        private String name;
        private String category;
        private String price;

        public Product(Bitmap photo, String name, String category, String price) {
            this.photo = photo;
            this.name = name;
            this.category = category;
            this.price = price;
        }

        public Bitmap getPhoto() {
            return photo;
        }

        public void setPhoto(Bitmap photo) {
            this.photo = photo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "photo=" + photo +
                    ", name='" + name + '\'' +
                    ", category='" + category + '\'' +
                    ", price='" + price + '\'' +
                    '}';
        }
    }
}
