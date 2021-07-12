package main.java.com.vladimir1506.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;


public class CSVConverter {
    public void makeCSV(List<Product> products) {
        try {
            File file = new File("result.csv");
            PrintWriter pw = new PrintWriter(file);
            pw.write(appendLines(products).toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private StringBuilder appendLines(List<Product> products) {
        StringBuilder sb = new StringBuilder();
        sb.append("id,position,name,onlinePrice,discountPrice,currency,discount,url,imageUrl,shopUrl,rating\n");
        for (Product product : products
        ) {
            sb.append(product.toString()).append("\n");
        }
        return sb;
    }
}
