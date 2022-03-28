package com.tacocloud.tacocloud.controller;


import com.tacocloud.tacocloud.data.IngredientRepository;
import com.tacocloud.tacocloud.domain.Ingredient;
import com.tacocloud.tacocloud.domain.Taco;
import com.tacocloud.tacocloud.domain.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tacocloud.tacocloud.domain.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    private IngredientRepository repository;

    @Autowired
    public DesignTacoController(IngredientRepository repository) {
        this.repository = repository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        repository.findAll().forEach(ingredients::add);

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(ingredients, type)
            );
        }
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder tacoOrder() {
        return new TacoOrder();
    }


    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(each -> each.getType().equals(type))
                .collect(Collectors.toList());
    }

}
