package com.kksg.dtos;

import java.util.ArrayList;
import java.util.List;

import com.kksg.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListResponseDTO extends BaseResponseDTO {

	private String name;
    private Long parentCategoryId;
    private List<CategoryListResponseDTO> subCategories = new ArrayList<>();
}

