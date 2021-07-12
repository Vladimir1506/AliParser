package com.vladimir1506.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private HttpURLConnection connection;
    private List<Product> productsList = new ArrayList<>();

    public Parser(int productsLimit) {
        int offset = 0;
        while (productsList.size() < productsLimit) {
            String response = load(String.format("https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18309996746731201487_1626031088485&widget_id=5547572&platform=pc&limit=25&offset=%d&phase=1&productIds2Top=&postback=83ebc8ad-d8ff-4186-b947-1f8857197786", offset));
            getProductsFromResponse(response);
            offset = +25;
        }
    }

    public String load(String url) {
        String inputLine;
        StringBuilder stringBuilder = null;
        try {
            URL obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            stringBuilder = new StringBuilder();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public void getProductsFromResponse(String responseBody) {
        responseBody = responseBody.substring(responseBody.indexOf("["), responseBody.lastIndexOf("]") + 1);
        try {
            JSONArray products = new JSONArray(responseBody);
            for (int i = 0; i < products.length(); i++) {
                JSONObject object = products.getJSONObject(i);
                productsList.add(getProductFromJSON(object));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    private Product getProductFromJSON(JSONObject object) {
        Product product = null;
        try {
            String productName = object.getString("productTitle");
            String productUrl = object.getString("productDetailUrl");
            product = new Product(productName, productUrl);
            Double discountPrice = Double.parseDouble(object.getString("oriMaxPrice").replaceAll("[^\\d.]", ""));
            product.setDiscountPrice(discountPrice);
            Long productId = object.getLong("productId");
            product.setProductId(productId);
            int discount = object.getInt("discount");
            product.setDiscount(discount);
            String shopUrl = object.getString("shopUrl");
            product.setShopUrl(shopUrl);
            String imageUrl;
            imageUrl = object.getString("productImage");
            product.setImageUrl(imageUrl);
            double onlinePrice = Double.parseDouble(object.getString("minPrice").replaceAll("[^\\d.]", ""));
            product.setOnlinePrice(onlinePrice);
            String currency = object.getString("minPrice").replaceAll("[^A-Z]", "");
            product.setCurrency(currency);
            Double rating = object.getDouble("productAverageStar");
            product.setRating(rating);
            product.setPosition(getProductsList().size() + 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return product;
    }
}
