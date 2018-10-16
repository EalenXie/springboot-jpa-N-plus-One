package name.ealen;

import com.alibaba.fastjson.JSONArray;
import name.ealen.dao.CategoryRepository;
import name.ealen.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;


/**
 * Created by EalenXie on 2018/10/16 17:03.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 新增分类测试
     */
    @Test
    public void addCategory() {

        //一个 家用电器分类(顶级分类)
        Category appliance = new Category();
        appliance.setName("家用电器");
        categoryRepository.save(appliance);

        //家用电器 下面的 电脑分类(二级分类)
        Category computer = new Category();
        computer.setName("电脑");
        computer.setParent(appliance);
        categoryRepository.save(computer);

        //电脑 下面的 笔记本电脑分类(三级分类)
        Category notebook = new Category();
        notebook.setName("笔记本电脑");
        notebook.setParent(computer);
        categoryRepository.save(notebook);

        //家用电器 下面的 手机分类(二级分类)
        Category mobile = new Category();
        mobile.setName("手机");
        mobile.setParent(appliance);
        categoryRepository.save(mobile);

        //手机 下面的 智能机 / 老人机(三级分类)
        Category smartPhone = new Category();
        smartPhone.setName("智能机");
        smartPhone.setParent(mobile);
        categoryRepository.save(smartPhone);

        Category oldPhone = new Category();
        oldPhone.setName("老人机");
        oldPhone.setParent(mobile);
        categoryRepository.save(oldPhone);
    }

    /**
     * 查找分类测试  已经解决了经典的 N+1 问题
     */
    @Test
    @Transactional
    public void findCategory() {
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            System.out.println(JSONArray.toJSONString(category));
        }

    }
}
