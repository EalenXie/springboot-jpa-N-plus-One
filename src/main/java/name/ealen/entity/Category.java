package name.ealen.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by EalenXie on 2018/10/16 16:13.
 * 典型的 多层级 分类
 * <p>
 * :@NamedEntityGraph :注解在实体上 , 解决典型的N+1问题
 * name表示实体图名, 与 repository中的注解 @EntityGraph的value属性相对应,
 * attributeNodes 表示被标注要懒加载的属性节点 比如此例中 : 要懒加载的子分类集合children
 */

@Entity
@Table(name = "jpa_category")
@NamedEntityGraph(name = "Category.Graph", attributeNodes = {@NamedAttributeNode("children")})
public class Category {

    /**
     * Id 使用UUID生成策略
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    /**
     * 分类名
     */
    private String name;

    /**
     * 一个商品分类下面可能有多个商品子分类(多级) 比如 分类 : 家用电器  (子)分类 : 电脑  (孙)子分类 : 笔记本电脑
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Category parent;                //父分类


    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<Category> children;       //子分类集合

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
    }
}
