package diana.springframework.controller;

import diana.springframework.model.Recipe;
import diana.springframework.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    public void testMockMVC() throws Exception {
        // MockMvcBuilders.webAppContextSetup => brings the spring context so the test is no longer unit test
        // MockMvcBuilders.standaloneSetup() is faster
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect((status()).isOk())
                .andExpect(view().name("index"));
    }


    @Test
    void getIndexPage() throws Exception{
        // testing the set with an argument catcher
        //given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        //creating a different recipe because it's a set
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);

        //when
        when(recipeService.getRecipes()).thenReturn(recipes);

        //then
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // the method return a string that equals index
        String viewName = controller.getIndexPage(model);
        assertEquals("index", viewName);

        // model.addAttribute is called one time
        verify(recipeService, times(1)).getRecipes();

        // recipeService.getRecipes() is called one time
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());

        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2,setInController.size());

    }
}