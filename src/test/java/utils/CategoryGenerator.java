package utils;

import pojos.CreateCategory;


public class CategoryGenerator {
	public static CreateCategory Category() {
		return CreateCategory.builder()
				.name("string")
				.id(0)
				.build();
	}
}