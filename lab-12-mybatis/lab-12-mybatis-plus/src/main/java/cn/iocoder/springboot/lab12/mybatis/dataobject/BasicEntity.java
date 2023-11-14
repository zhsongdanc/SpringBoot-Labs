package cn.iocoder.springboot.lab12.mybatis.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/7/4 17:22
 */
public class BasicEntity {


    /**
     *
     */
    @TableField(value = "time")
    private String time;

    /**
     *
     */
    @TableField(value = "creater")
    private String creater;



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}
