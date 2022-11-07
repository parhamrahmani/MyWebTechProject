package htw.berlin.webtech.MyBazaarList.web;

import htw.berlin.webtech.MyBazaarList.MyBazaarListApplication;

import htw.berlin.webtech.MyBazaarList.persistence.ProductEntity;
import htw.berlin.webtech.MyBazaarList.persistence.ProductRepository;
import htw.berlin.webtech.MyBazaarList.service.ProductService;
import htw.berlin.webtech.MyBazaarList.web.api.Product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriTemplate;


import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductRestController.class)
public class ProductRestControllerTest {
    @Autowired
   private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("Testing the fetch all method")
    void testFetchAllProducts()
    {


    }
    @Test
    @DisplayName("Testing the fetch by id method ")
    void testFetchProductById() throws Exception {

        var product = new Product(2L,"Remoulade","Kunella","Oil");
        when(productService.findById(2L)).thenReturn(product);
        this.mockMvc.perform(get("/api/v1/products/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(product.getId()));
    }
    @Test
    @DisplayName("Testing the post Product method")
    void testPostProduct()
    {

    }
    @Test
    @DisplayName("Testing the update product by id")
    void testUpdateProductById()
    {

    }
    @Test
    @DisplayName("Testing the delete product by id method")
    void testDeleteProductById()
    {

    }
    
}









