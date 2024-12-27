package com.kksg.dtos;

import java.util.ArrayList;
import java.util.List;

import com.kksg.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO extends BaseResponseDTO {

	private String name;
	private String description;
    private Category parentCategory;
    private List<Category> subCategories = new ArrayList<>();
}
