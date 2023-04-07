package blogappapi.Service;

import blogappapi.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    // Crate

     CategoryDto crateCategory(CategoryDto categoryDto);

    //update

    CategoryDto updateCategory(CategoryDto categoryDto , int id);

    //delete
     void deleteCategory(int id);


    //get

     CategoryDto getCategory(int id);


    //getAll
    List<CategoryDto> getCategories();

}
