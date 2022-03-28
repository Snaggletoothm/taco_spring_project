package com.tacocloud.tacocloud.web;

import com.tacocloud.tacocloud.data.IngredientRepository;
import com.tacocloud.tacocloud.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private final IngredientRepository repository;

    public IngredientByIdConverter(IngredientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ingredient convert(String id) {
        return repository.findById(id).orElse(null);
    }
}
