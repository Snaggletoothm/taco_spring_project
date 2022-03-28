package com.tacocloud.tacocloud.data;

import com.tacocloud.tacocloud.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateRepository implements IngredientRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query(
                "select id, name, type from ingredient",
                this::getRowMapper4Ingredient
        );
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        Ingredient ingredient = jdbcTemplate.queryForObject(
                "select id, name, type from ingredient where id=?",
                this::getRowMapper4Ingredient,
                id
        );
        if (ingredient == null) return Optional.empty();
        return Optional.of(ingredient);
    }

    private Ingredient getRowMapper4Ingredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type"))
        );
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into ingredients (id, name, type) values (?, ?, ?)",
                ingredient.getId(), ingredient.getName(), ingredient.getType().toString()
        );
        return ingredient;
    }
}
