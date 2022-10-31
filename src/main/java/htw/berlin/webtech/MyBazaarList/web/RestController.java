package htw.berlin.webtech.MyBazaarList.web;

import htw.berlin.webtech.MyBazaarList.web.api.Product;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private List<Product> products;

    public RestController() {
        products = new ArrayList<>();
        products.add(new Product(1,"H-Milch 3%","Ja"));
        products.add(new Product(2,"Turkish Jogurt","Guen"));

    }

    @GetMapping(path="/api/v1/products")
    public List<Product> fetchProducts() {
   return products;
    }
}
