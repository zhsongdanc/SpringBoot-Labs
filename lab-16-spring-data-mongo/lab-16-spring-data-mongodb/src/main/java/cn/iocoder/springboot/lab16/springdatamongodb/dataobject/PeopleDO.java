package cn.iocoder.springboot.lab16.springdatamongodb.dataobject;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/1/3 11:19
 */

import java.util.Arrays;
import java.util.List;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "People")
public class PeopleDO {
    private String name;
    private String job;
    private Integer age;
    private Integer count = 6;
    private Models model;

    private MyObj myObj;
    private List<Version> version;


    public static class MyObj{
        private String myAge;

        public String getMyAge() {
            return myAge;
        }

        public void setMyAge(String myAge) {
            this.myAge = myAge;
        }

        @Override
        public String toString() {
            return "MyObj{" +
                    "myAge='" + myAge + '\'' +
                    '}';
        }
    }
}
