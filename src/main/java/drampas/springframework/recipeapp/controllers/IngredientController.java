package drampas.springframework.recipeapp.controllers;

import drampas.springframework.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @RequestMapping("/recipe/{id}/ingredients")
    public String getIngredientList(@PathVariable String id, Model model){
        log.debug("Getting ingredient list for recipe id:"+id);
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "/recipe/ingredient/list";
    }
}
