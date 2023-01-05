package htw.berlin.webtech.MyBazaarList.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import htw.berlin.webtech.MyBazaarList.persistence.ShoppingListEntity;
import htw.berlin.webtech.MyBazaarList.persistence.ProductEntity;
import htw.berlin.webtech.MyBazaarList.service.ListTransformer;
import htw.berlin.webtech.MyBazaarList.service.ProductService;
import htw.berlin.webtech.MyBazaarList.service.ShoppingListService;
import htw.berlin.webtech.MyBazaarList.web.api.Product;
import htw.berlin.webtech.MyBazaarList.web.api.ProductManipulationRequest;
import htw.berlin.webtech.MyBazaarList.web.api.ShoppingList;
import htw.berlin.webtech.MyBazaarList.web.api.ShoppingListManipulationRequest;
import jdk.jfr.Category;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class ProductRestControllerTest {
    private final ProductService productService = Mockito.mock(ProductService.class);
    private final ProductRestController productRestController = Mockito.mock(ProductRestController.class);

   @Test
    public void testFetchProducts_withProducts() {
        // Arrange
        List<Product> products = Arrays.asList(
                new Product(1L, "Product 1", "brandTest1", "Cat1",null),
                new Product(2L, "Product 2", "brandTest2", "Cat2",null)
        );

       //when(productService.findAll()).thenReturn(products);
       when(productRestController.fetchProducts()).thenReturn(new ResponseEntity<>(products, HttpStatus.OK));


       // Act
        ResponseEntity<List<Product>> response = productRestController.fetchProducts();


        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());


    }

    @Test
    public void testFetchProducts_noProducts() {
        // Arrange
        when(productService.findAll()).thenReturn(Collections.emptyList());
        when(productRestController.fetchProducts()).thenReturn(new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK));


        // Act
        ResponseEntity<List<Product>> response = productRestController.fetchProducts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }
    @Test
    public void testFetchProductById_found() {
       //Arrange
        Product product = new Product(1L,"Test","Test", "Test",null);
        //when(productService.findById(1L)).thenReturn(product);
        when(productRestController.fetchProductById(1L)).thenReturn(new ResponseEntity<>(product,HttpStatus.OK));
        //Act
        ResponseEntity<Product> response = productRestController.fetchProductById(1L);

        //Assert
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(product,response.getBody());
    }
}
