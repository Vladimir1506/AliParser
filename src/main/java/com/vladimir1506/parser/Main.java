package main.java.com.vladimir1506.parser;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        int productsLimit = 100;
        List<Product> products = new Parser(productsLimit).getProductsList();
        new CSVConverter().makeCSV(products);
    }
}