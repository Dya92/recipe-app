package diana.springframework.controller;

import diana.springframework.command.RecipeCommand;
import diana.springframework.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe",recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        // recipe folder - recipeForm file
        return "recipe/recipeForm";
    }

    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeForm";
    }

    //@RequestMapping(name = "recipe", method = RequestMethod.POST) - old version, replaced with the next 2 rows
    @PostMapping
    @RequestMapping("recipe")
    // @ModelAttribute - telling Spring to bind the form post parameters to the recipe command object
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        // "redirect: ... " go to
        return "redirect:/recipe/" +  + savedCommand.getId() +"/show";
    }
}
