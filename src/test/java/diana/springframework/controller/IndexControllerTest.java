package diana.springframework.controller;

import diana.springframework.model.Recipe;
import diana.springframework.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class IndexControllerTest {
    IndexController controller;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        controller = new IndexController(recipeService);
    }

    @Test
    void getIndexPage() throws Exception{

        // the method return a string that equals index
        String viewName = controller.getIndexPage(model);
        assertEquals("index", viewName);

        // model.addAttribute is called one time
        verify(recipeService, times(1)).getRecipes();

        // recipeService.getRecipes() is called one time
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());

    }
}