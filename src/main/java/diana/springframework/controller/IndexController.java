package diana.springframework.controller;

import diana.springframework.model.Category;
import diana.springframework.model.UnitOfMeasure;
import diana.springframework.repository.CategoryRepository;
import diana.springframework.repository.UnitOfMeasureRepository;
import diana.springframework.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","index"})
    public String getIndexPage(Model model) {
        log.debug("I'm in the controller. Getting index page");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }



//    private CategoryRepository categoryRepository;
//    private UnitOfMeasureRepository unitOfMeasureRepository;
//
//    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
//        this.categoryRepository = categoryRepository;
//        this.unitOfMeasureRepository = unitOfMeasureRepository;
//    }

//    @RequestMapping({"","/","index"})
//    public String getIndexPage() {
//        System.out.println("some message...bla bla bla");
//
//        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
//        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
//
//        System.out.println("Cat Id is: " + categoryOptional.get().getId());
//        System.out.println("UOM Id is: " + unitOfMeasureOptional.get().getId());
//        return "index";
//    }
}
