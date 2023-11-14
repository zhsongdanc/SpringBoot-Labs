package cn.iocoder.springboot.lab16.springdatamongodb.dataobject;

import java.util.Arrays;
import lombok.Data;
import lombok.ToString;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/2/13 20:26
 */
@Data
@ToString
public class Models {
    private String[] models;
    private String tel;


    public Models(String[] models) {
        this.models = models;
    }


}
