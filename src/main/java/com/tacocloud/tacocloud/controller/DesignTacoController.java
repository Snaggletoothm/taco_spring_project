package com.tacocloud.tacocloud.controller;


import com.tacocloud.tacocloud.domain.Ingredient;
import com.tacocloud.tacocloud.domain.Taco;
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

    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {

    }
}
