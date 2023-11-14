package cn.iocoder.springboot.lab16.springdatamongodb.repository;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/1/3 11:24
 */

import cn.iocoder.springboot.lab16.springdatamongodb.Application;
import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.Models;
import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.PartPeopleDO;
import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.PeopleDO;
import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.PeopleDO.MyObj;
import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.Version;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PeopleRepositoryTest {

    @Autowired
    private PeopleRepository peopleRepository;

    @Test
    public void testMultiUpdate() {

        PeopleDO src = new PeopleDO();
        src.setAge(0);
        PeopleDO tar = new PeopleDO();
        tar.setAge(1);
        peopleRepository.updateMulti(src, tar);
    }

    @Test
    public void testOrFind() {
        List<PeopleDO> peopleDOS = peopleRepository.queryPeopleDOList();
        for (PeopleDO peopleDO : peopleDOS) {
            System.out.println(peopleDO);
        }
    }

    @Test
    public void testCreate() {
        PeopleDO peopleDO = new PeopleDO();
        List<Version> versionList = new ArrayList<>();
        versionList.add(new Version("a1"));
        versionList.add(new Version("a2"));
        versionList.add(new Version("a3"));
        versionList.add(new Version("a4"));
//        peopleDO.setModel(new Models(new String[]{"a1", "a2"}));
//        peopleDO.setName("pub_demu_a");
        peopleDO.setVersion(versionList);
        peopleDO.setName("demus0213");
        PeopleDO insert = peopleRepository.insert(peopleDO);
    }

    @Test
    public void testFind() {
//        Criteria criteria = Criteria.where("name").is("szh").and("age").is(12);
        List<PeopleDO> peopleDOList = peopleRepository.find(new Criteria());
        System.out.println(peopleDOList);
    }

    @Test
    public void testFindOrOperator() {
        Criteria criteria = new Criteria();
        criteria.and("job").is("worker");
        criteria.orOperator(Criteria.where("name").is("go"), Criteria.where("name").is("szh"));
        List<PeopleDO> peopleDOList = peopleRepository.find(criteria);
        System.out.println(peopleDOList);
    }

    @Test
    public void testFindByNe() {
        Criteria criteria = new Criteria();
        criteria.and("age").ne(1);
        List<PeopleDO> peopleDOList = peopleRepository.find(criteria);
        System.out.println(peopleDOList);
    }

    @Test
    public void testElemMatch() {
        Criteria criteria = new Criteria();
        criteria.and("models").elemMatch(Criteria.where("$eq").is("m2"));

        List<PeopleDO> peopleDOList = peopleRepository.find(criteria);
        System.out.println(peopleDOList);
    }

    @Test
    public void testFindInChild() {
        // 插入一条包含嵌套文档的数据
//        PeopleDO peopleDO = new PeopleDO();
//        MyObj myObj = new MyObj();
//        myObj.setMyAge("30");
//        peopleDO.setMyObj(myObj);
//        peopleDO.setAge(30);
//        PeopleDO insert = peopleRepository.insert(peopleDO);
//        System.out.println(insert);
        Criteria criteria = new Criteria();
        criteria.and("myObj.myAge").is("30");
        List<PeopleDO> peopleDOList = peopleRepository.find(criteria);
        System.out.println(peopleDOList);
    }

    @Test
    public void testFiindInChildDoc() {
        Criteria criteria = new Criteria();
        criteria.and("myobj").elemMatch(Criteria.where("myage").is("24"));

    }

    @Test
    public void testWithAggregation() {
        List<PeopleDO> peopleDOS = peopleRepository.findWithAggregation();
        for (PeopleDO peopleDO : peopleDOS) {
            System.out.println(peopleDO);
        }
    }

    @Test
    public void regexWithChinese() {
        List<PeopleDO> peopleDOList = peopleRepository.regexWithChinese();
        for (PeopleDO peopleDO : peopleDOList) {
            System.out.println(peopleDO);
        }
    }

    @Test
    public void embedCriteria() {
        List<PeopleDO> peopleDOList = peopleRepository.embedCriteria();
        for (PeopleDO peopleDO : peopleDOList) {
            System.out.println(peopleDO);
        }
        String str = "as";
        String str2 = "";
        System.out.println(isEmpty(str2));
        System.out.println(isEmpty(str));
    }

    public static boolean isEmpty(Object obj) {

        if (obj instanceof CharSequence) return ((CharSequence) obj).length() == 0;


        return false;
    }


    @Test
    public void testProjection() {
        List<PartPeopleDO> partPeopleDOS = peopleRepository.testProjection();
        partPeopleDOS.forEach(System.out::println);
    }

    @Test
    public void testWithTwoAndOperator() {
        List<PeopleDO> peopleDOList = peopleRepository.testWithTwoAndOperator();
        for (PeopleDO peopleDO : peopleDOList) {
            System.out.println(peopleDO);
        }
    }
    
    @Test
    public void findById() {
        String id = "63ea2d6634853bcfb2660ee6";
        List<PeopleDO> peopleDO = peopleRepository.findById(id);
        System.out.println(peopleDO);
    }

    // in查询与mysql的in查询不同，如果db中的列表包含待查询列表的任意一项
    @Test
    public void testInSearch() {
        Criteria criteria = new Criteria();
        String str = "m1";
        String str2 = "m999";
        criteria.and("models").in(Arrays.asList(str, str2));
        List<PeopleDO> peopleDOList = peopleRepository.find(criteria);
        System.out.println(peopleDOList);
    }
}
