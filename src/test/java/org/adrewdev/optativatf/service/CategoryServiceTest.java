package org.adrewdev.optativatf.service;

import org.adrewdev.optativatf.entity.CategoryEntity;
import org.adrewdev.optativatf.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private CategoryEntity category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new CategoryEntity();
        category.setId(1L);
        category.setName("Fiction");
    }

    @Test
    void getAllCategories() {
        var categories = Arrays.asList(category);
        when(categoryRepository.findAll()).thenReturn(categories);

        var result = categoryService.getAllCategories();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Fiction", result.get(0).getName());
    }

    @Test
    void saveCategory() {
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(category);

        var result = categoryService.saveCategory(category);

        assertNotNull(result);
        assertEquals("Fiction", result.getName());
    }

    @Test
    void updateCategory() {
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(category);

        var result = categoryService.updateCategory(1L, category);

        assertNotNull(result);
        assertEquals("Fiction", result.getName());
    }

    @Test
    void deleteCategory() {
        doNothing().when(categoryRepository).deleteById(1L);

        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    void getCategoryByName() {
        when(categoryRepository.findByName("Fiction")).thenReturn(Optional.of(category));

        var result = categoryService.getCategoryByName("Fiction");

        assertTrue(result.isPresent());
        assertEquals("Fiction", result.get().getName());
    }
}
