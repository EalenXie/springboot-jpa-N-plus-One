package name.ealen.dao;

import name.ealen.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by EalenXie on 2018/10/16 16:19.
 */
public interface CategoryRepository extends JpaRepository<Category, String> {
    /**
     * 解决 懒加载 JPA 典型的 N + 1 问题
     */
    @EntityGraph(value = "Category.findAll", type = EntityGraph.EntityGraphType.FETCH)
    List<Category> findAll();


    @EntityGraph(value = "Category.findByParent",type = EntityGraph.EntityGraphType.FETCH)
    List<Category> findByParent(Category parent);
}
