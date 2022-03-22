package com.tacocloud.tacocloud.controller;


import com.tacocloud.tacocloud.domain.Ingredient;
import com.tacocloud.tacocloud.domain.Taco;
import com.tacocloud.tacocloud.domain.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

import static com.tacocloud.tacocloud.domain.Ingredient.Type;
import static com.tacocloud.tacocloud.domain.Ingredient.ingredients;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
    @GetMapping
    public String showDesignForm(Model model) {
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(ingredients, type)
            );
        }
        model.addAttribute("tacoOrder", new TacoOrder());
        model.addAttribute("taco", new Taco());

        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(each -> each.getType().equals(type))
                .collect(Collectors.toList());
    }

}
