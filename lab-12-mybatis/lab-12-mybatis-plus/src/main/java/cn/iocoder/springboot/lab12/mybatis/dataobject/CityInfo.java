package cn.iocoder.springboot.lab12.mybatis.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 *
 * @TableName city_info
 */
@TableName(value ="city_info")
public class CityInfo extends BasicEntity implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     *
     */
    @TableField(value = "city")
    private String city;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
