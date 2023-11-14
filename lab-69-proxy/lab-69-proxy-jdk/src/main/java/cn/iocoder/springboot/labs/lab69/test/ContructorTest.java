package cn.iocoder.springboot.labs.lab69.test;

import cn.iocoder.springboot.labs.lab69.service.UserServiceImpl;
import java.lang.reflect.Constructor;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/1/12 10:50
 */
public class ContructorTest {

    public static void main(String[] args) throws Exception{
        Class<Parent> parentClass = Parent.class;
        Constructor<Parent> constructor = parentClass.getConstructor(String.class);
        Parent test = constructor.newInstance("test");
        test.print();

    }
}

class Parent{

    private String name;

    public Parent(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println(name);
    }


}