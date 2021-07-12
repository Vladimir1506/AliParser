package main.java.com.vladimir1506.parser;

public class Product {
    private Long productId;
    private int position;
    private String name;
    private Double onlinePrice;
    private Double discountPrice;
    private String currency;
    private int discount;
    private String url;
    private String imageUrl;
    private String shopUrl;
    private Double rating;

    public Product(String name, String url) {
        this.name = name;
        this.url = url;

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOnlinePrice() {
        return onlinePrice;
    }

    public void setOnlinePrice(double onlinePrice) {
        this.onlinePrice = onlinePrice;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return productId + ","
                + getPosition() + ","
                + name + ","
                + onlinePrice + ","
                + discountPrice + ","
                + currency + ","
                + discount + ","
                + url + ","
                + imageUrl + ","
                + shopUrl + ","
                + rating;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
