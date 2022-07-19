package com.taco.tacocloud.web;

// import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

import com.taco.tacocloud.Ingredients;
import com.taco.tacocloud.Ingredients.Type;
import com.taco.tacocloud.Taco;


@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    

    @GetMapping
    public String showDesignForm(Model model){

        // Create List of Ingredients 
        List<Ingredients> ingredients = Arrays.asList(
            new Ingredients("FLTO", "Flour Tortilla", Type.WRAP),
            new Ingredients("COTO", "Corn Tortilla", Type.WRAP),
            new Ingredients("GRBF", "Ground Beef", Type.PROTEIN),
            new Ingredients("CARN", "Carnitas", Type.PROTEIN),
            new Ingredients("TMTO", "Diced Tomatoes", Type.VEGGIES),
            new Ingredients("LETC", "Lettuce", Type.VEGGIES),
            new Ingredients("CHED", "Cheddar", Type.CHEESE),
            new Ingredients("JACK", "Monterrey Jack", Type.CHEESE),
            new Ingredients("SLSA", "Salsa", Type.SAUCE),
            new Ingredients("SRCR", "Sour Cream", Type.SAUCE)
        );
        
        // Filter the list by ingredient type 
        Type[] types = Ingredients.Type.values();
        for (Type type: types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
            // System.out.println(type.toString().toLowerCase() +"-"+ filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors){
        if(errors.hasErrors()){
            
            return "design";
        }
        // Save the taco design
        // Chapter 3
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }
    
    private List<Ingredients> filterByType(List<Ingredients> ingredients, Type type) {

        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    
    }

    // //test
    // private static List<Ingredients> ingredients = Arrays.asList(
    //     new Ingredients("FLTO", "Flour Tortilla", Type.WRAP),
    //     new Ingredients("COTO", "Corn Tortilla", Type.WRAP)
    // );
    
    // //test
    // public static void main(String[] args) {
    //     System.out.println(ingredients.stream());
    // }
    
}
