package htw.berlin.webtech.MyBazaarList.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import htw.berlin.webtech.MyBazaarList.persistence.ShoppingListEntity;
import htw.berlin.webtech.MyBazaarList.service.ListTransformer;
import htw.berlin.webtech.MyBazaarList.service.ProductService;
import htw.berlin.webtech.MyBazaarList.service.ShoppingListService;
import htw.berlin.webtech.MyBazaarList.web.api.Product;
import htw.berlin.webtech.MyBazaarList.web.api.ProductManipulationRequest;
import htw.berlin.webtech.MyBazaarList.web.api.ShoppingList;
import htw.berlin.webtech.MyBazaarList.web.api.ShoppingListManipulationRequest;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Parham Rahmani 580200
 *
 * This is test for ShoppingListRestController.java
 */
public class ShoppingListRestControllerTest {

    private final ShoppingListService shoppingListService = Mockito.mock(ShoppingListService.class);
    private final ProductService productService = Mockito.mock(ProductService.class);
    private final ShoppingListRestController shoppingListRestController =
            new ShoppingListRestController(shoppingListService, productService);

    @Test
    @Description("testFetchAll() - This test verifies that the fetchAll() method in ShoppingListRestController" +
            " returns an HTTP 200 OK status code and a list of shopping lists when it is called.")
    public void testFetchAll() {
        ShoppingList shoppingList1 =
                new ShoppingList(1L, "Shopping List 1", "Desc 1", Arrays.asList(1L, 2L));
        ShoppingList shoppingList2 =
                new ShoppingList(2L, "Shopping List 2","Desc 2", Arrays.asList(3L, 4L));
        List<ShoppingList> shoppingLists = Arrays.asList(shoppingList1, shoppingList2);
        when(shoppingListService.findAll()).thenReturn(shoppingLists);

        ResponseEntity<List<ShoppingList>> response = shoppingListRestController.fetchAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shoppingLists, response.getBody());
    }

    @Test
    @Description("testFetchById_found() - This test verifies that the fetchById(Long id) method in " +
            "ShoppingListRestController returns an HTTP 200 OK status code and a shopping list when it is" +
            " called with an id that corresponds to a shopping list that exists in the service.")
    public void testFetchById_found() {
        ShoppingList shoppingList =
                new ShoppingList(1L, "Shopping List 1","Desc 1", Arrays.asList(1L, 2L));
        when(shoppingListService.findById(1L)).thenReturn(shoppingList);

        ResponseEntity<ShoppingList> response = shoppingListRestController.fetchById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shoppingList, response.getBody());
    }

    @Test
    @Description("testFetchById_notFound() - This test verifies that the fetchById(Long id) method in" +
            " ShoppingListRestController returns an HTTP 404 Not Found status code when it is called with an " +
            "id that does not correspond to any shopping list in the service.")
    public void testFetchById_notFound() {
        when(shoppingListService.findById(1L)).thenReturn(null);

        ResponseEntity<ShoppingList> response = shoppingListRestController.fetchById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    @Description("testUpdate() - This test verifies that the update(Long id, ShoppingListManipulationRequest request)" +
            " method in ShoppingListRestController calls the update(Long id, ShoppingListManipulationRequest request)" +
            " method of the service, returns an HTTP 200 OK status code, and returns the updated shopping list when " +
            "it is called.")
    public void testUpdate() {
        // Arrange
        ShoppingList shoppingList =
                new ShoppingList(1L, "Shopping List 1","Desc 1", Arrays.asList(1L, 2L));
        shoppingList.setListName("Shopping List After Post");
        ShoppingListManipulationRequest request = new ShoppingListManipulationRequest();
        request.setListName("Shopping List After Post");
        when(shoppingListService.findById(1L)).thenReturn(shoppingList);
        when(shoppingListService.update(1L, request)).thenReturn(shoppingList);

        // Act
        ResponseEntity<ShoppingList> response = shoppingListRestController.update(1L, request);

        // Assert
        assertEquals(shoppingList, response.getBody());
    }

    @Test
    @Description("testFetchProductsByShoppingListId() is a test that verifies the behavior of the" +
            " fetchProductsByShoppingListId(Long id) method in the ShoppingListRestController class." +
            " This method is expected to fetch the products associated with a shopping list with a given id," +
            " and return them in an HTTP 200 OK response.")
    public void testFetchProductsByShoppingListId() {
        // Arrange
        long id = 1L;
        ShoppingList shoppingList =
                new ShoppingList(id, "Shopping List 1", "Desc 1", Arrays.asList(1L, 2L));

        Product product1 = new Product(1L, "Product 1", "Desc 1", "1.0", null );
        Product product2 = new Product(2L, "Product 2", "Desc 2", "2.0", null);
        when(shoppingListService.findById(id)).thenReturn(shoppingList);
        when(productService.findById(1L)).thenReturn(product1);
        when(productService.findById(2L)).thenReturn(product2);

        // Act
        ResponseEntity<List<Product>> response = shoppingListRestController.fetchProductsByShoppingListId(id);

        // Assert
        assertEquals(Arrays.asList(product1, product2), response.getBody());
    }

    @Test
    @Description("testPost() - This test verifies that the post(ShoppingListManipulationRequest request) method in" +
            " ShoppingListRestController calls the postShoppingList(ShoppingListManipulationRequest request) " +
            "method of the service, returns an HTTP 201 Created status code, and returns the URI of the " +
            "created shopping list when it is called.")
    public void testPost() throws URISyntaxException {
        // Arrange
        ShoppingListManipulationRequest request =
                new ShoppingListManipulationRequest("Shopping List 1", "Desc 1");
        ShoppingList shoppingList =
                new ShoppingList(1L, "Shopping List 1", "Desc 1", Arrays.asList(1L, 2L));
        when(shoppingListService.postShoppingList(request)).thenReturn(shoppingList);

        // Act
        ResponseEntity<Void> response = shoppingListRestController.post(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/api/v1/shoppingLists1", response.getHeaders().getLocation().toString());
    }





}