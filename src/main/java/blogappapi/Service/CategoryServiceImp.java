package blogappapi.Service;

import blogappapi.dao.CategoryRepository;
import blogappapi.dto.CategoryDto;
import blogappapi.exception.ResourceNotFountException;
import blogappapi.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDto crateCategory(CategoryDto categoryDto) {
       Category cat= this.modelMapper.map(categoryDto,Category.class);
       Category addedCat=this.categoryRepository.save(cat);
        return this.modelMapper.map(addedCat,CategoryDto.class);
    }


    ///////// Wrost COde//////////
//    public List<CategoryDto> carteList(List<CategoryDto>categoryDtos){
//       Category cat= this.modelMapper.map(categoryDtos,Category.class);
//       Category addedCat=this.categoryRepository.saveAll()
//       return this.modelMapper.map(addedCat,CategoryDto.class)>;
//    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
        Category cat=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFountException("Category","id",id));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCat=categoryRepository.save(cat);
        return this.modelMapper.map(updatedCat,CategoryDto.class);


    }

    @Override
    public void deleteCategory(int id) {
        Category cat=categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFountException("Category","id",id));
        categoryRepository.deleteById(cat.getCategoryId());

    }

    @Override
    public CategoryDto getCategory(int id) {
        Category cat=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFountException("Category","id",id));
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories=this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos=categories.stream().map((cat)-> this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}
