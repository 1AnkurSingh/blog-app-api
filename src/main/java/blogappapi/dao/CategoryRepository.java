package blogappapi.dao;

import blogappapi.dto.CategoryDto;
import blogappapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
