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
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ShoppingListRestControllerTest {

    private final ShoppingListService shoppingListService = Mockito.mock(ShoppingListService.class);
    private final ProductService productService = Mockito.mock(ProductService.class);
    private final ShoppingListRestController shoppingListRestController = new ShoppingListRestController(shoppingListService, productService);

    @Test
    public void testFetchAll() {
        ShoppingList shoppingList1 = new ShoppingList(1L, "Shopping List 1", "Desc 1", Arrays.asList(1L, 2L));
        ShoppingList shoppingList2 = new ShoppingList(2L, "Shopping List 2","Desc 2", Arrays.asList(3L, 4L));
        List<ShoppingList> shoppingLists = Arrays.asList(shoppingList1, shoppingList2);
        when(shoppingListService.findAll()).thenReturn(shoppingLists);

        ResponseEntity<List<ShoppingList>> response = shoppingListRestController.fetchAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shoppingLists, response.getBody());
    }

    @Test
    public void testFetchById_found() {
        ShoppingList shoppingList = new ShoppingList(1L, "Shopping List 1","Desc 1", Arrays.asList(1L, 2L));
        when(shoppingListService.findById(1L)).thenReturn(shoppingList);

        ResponseEntity<ShoppingList> response = shoppingListRestController.fetchById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shoppingList, response.getBody());
    }

    @Test
    public void testFetchById_notFound() {
        when(shoppingListService.findById(1L)).thenReturn(null);

        ResponseEntity<ShoppingList> response = shoppingListRestController.fetchById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    public void testDelete() {
        // Arrange
        long id = 1L;
        ShoppingList shoppingList = new ShoppingList(id, "Shopping List 1","Desc 1", Arrays.asList(1L, 2L));
        when(shoppingListService.findById(id)).thenReturn(shoppingList);

        // Act
        ResponseEntity<Void> response = shoppingListRestController.delete(id);

        // Assert
        verify(shoppingListService).deleteById(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }



    @Test
    public void testUpdate() {
        // Arrange
        ShoppingList shoppingList = new ShoppingList(1L, "Shopping List 1","Desc 1", Arrays.asList(1L, 2L));
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

    /**
     *
     */
    @Test
    public void testFetchProductsByShoppingListId() {
        // Arrange
        long id = 1L;
        ShoppingList shoppingList = new ShoppingList(id, "Shopping List 1", "Desc 1", Arrays.asList(1L, 2L));

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
    public void testPost() throws URISyntaxException {
        // Arrange
        ShoppingListManipulationRequest request = new ShoppingListManipulationRequest("Shopping List 1", "Desc 1");
        ShoppingList shoppingList = new ShoppingList(1L, "Shopping List 1", "Desc 1", Arrays.asList(1L, 2L));
        when(shoppingListService.postShoppingList(request)).thenReturn(shoppingList);

        // Act
        ResponseEntity<Void> response = shoppingListRestController.post(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/api/v1/shoppingLists/1", response.getHeaders().getLocation().toString());
    }





}