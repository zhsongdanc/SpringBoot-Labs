package cn.iocoder.springboot.lab12.mybatis.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 *
 * @TableName name_info
 */
@TableName(value ="name_info")
public class NameInfo extends BasicEntity implements Serializable {

    /**
     *
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    /**
     *
     */
    @TableField(value = "name")
    private String name;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
