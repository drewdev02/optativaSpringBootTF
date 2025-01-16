package org.adrewdev.optativatf.controller;

import org.adrewdev.optativatf.entity.CategoryEntity;
import org.adrewdev.optativatf.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private CategoryEntity category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new CategoryEntity();
        category.setId(1L);
        category.setName("Test Category");
    }

    @Test
    void getAllCategories() {
        List<CategoryEntity> categories = Arrays.asList(category);
        when(categoryService.getAllCategories()).thenReturn(categories);

        var response = (ResponseEntity<List<CategoryEntity>>) categoryController.getAllCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Category", response.getBody().get(0).getName());
    }

    @Test
    void createCategory() {
        when(categoryService.saveCategory(any(CategoryEntity.class))).thenReturn(category);

        var response = (ResponseEntity<CategoryEntity>) categoryController.createCategory(category);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test Category", response.getBody().getName());
    }

    @Test
    void updateCategory() {
        when(categoryService.updateCategory(1L, category)).thenReturn(category);

        var response = (ResponseEntity<CategoryEntity>) categoryController.updateCategory(1L, category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Category", response.getBody().getName());
    }

    @Test
    void deleteCategory() {
        doNothing().when(categoryService).deleteCategory(1L);

        var response = (ResponseEntity<Void>) categoryController.deleteCategory(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoryService, times(1)).deleteCategory(1L);
    }

    @Test
    void getCategoryByName() {
        when(categoryService.getCategoryByName("Test Category")).thenReturn(Optional.of(category));

        var response = (ResponseEntity<Optional<CategoryEntity>>) categoryController.getCategoryByName("Test Category");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Category", response.getBody().get().getName());
    }
}
