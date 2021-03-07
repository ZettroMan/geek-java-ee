package ru.geekbrains.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.persist.Category;

@Data
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;

    public CategoryDto(Category category) {
        id = category.getId();
        name = category.getName();
    }
}
