package blogappapi.Controller;

import blogappapi.Service.CategoryService;
import blogappapi.dao.CategoryRepository;
import blogappapi.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto createCategory = categoryService.crateCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable int id) {
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, id);
        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping("/getCategory/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable int id) {
        CategoryDto createCategory = categoryService.getCategory(id);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.OK);
    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> createCategorys = categoryService.getCategories();
        return ResponseEntity.ok(createCategorys);
    }
}
