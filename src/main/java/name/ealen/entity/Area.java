package name.ealen.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by EalenXie on 2018/10/16 16:49.
 * 典型的 多层级 区域关系
 */

@Entity
@Table(name = "jpa_area")
public class Area {


    /**
     * Id 使用UUID生成策略
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    /**
     * 区域名
     */
    private String name;

    /**
     * 一个区域信息下面很多子区域(多级) 比如 分类 : 广东省  (子)区域 : 广州市  (孙)子区域 : 天河区
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Area parent;


    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Area> children;


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

    public Area getParent() {
        return parent;
    }

    public void setParent(Area parent) {
        this.parent = parent;
    }

    public List<Area> getChildren() {
        return children;
    }

    public void setChildren(List<Area> children) {
        this.children = children;
    }
}
