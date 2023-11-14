package cn.iocoder.springboot.lab16.springdatamongodb.repository;

import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.UserDO;
import cn.iocoder.springboot.lab16.springdatamongodb.vo.UserVO;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Repository
public class UserRepository{

    @Autowired
    MongoTemplate mongoTemplate;

    public void updateMulti(UserVO srcObj, UserVO targetObj) {
        Query query = getQueryByObject(srcObj);
        Update update = getUpdateByObject(targetObj);
        this.mongoTemplate.updateMulti(query, update, UserVO.class);
    }

    private Query getQueryByObject(UserVO object) {
        Query query = new Query();
        List<String> fields = getfieldName(object);
        Criteria criteria = new Criteria();
        for (String field : fields) {
            Object fieldValue = getFieldValueByName(field, object);
            String type = getfieldSimpleType(field, object);
            if (!ObjectUtils.isEmpty(fieldValue)) {
                if (type.equals("int") && Integer.parseInt(fieldValue.toString()) == 0) {
                } else {
                    criteria.and(field).is(fieldValue);
                }

            }
        }
        query.addCriteria(criteria);
        return query;
    }

    public Update getUpdateByObject(UserVO object) {
        Update update = new Update();
        List<String> fields = getfieldName(object);
        for (String field : fields) {
            Object fieldValue = getFieldValueByName(field, object);
            if (!Objects.isNull(fieldValue)) {
                update.set(field, fieldValue);
            }
        }
        return update;
    }

    private static List<String> getfieldName(Object o) {
        List<Field> fieldsList = new ArrayList<>();
        Class<?> tempClass = o.getClass();
        while (tempClass != null) {
            // 递归获取当前类的所有属性和父类的所有属性
            fieldsList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }
        List<String> fieldsName = new ArrayList<>();
        for (Field field : fieldsList) {
            fieldsName.add(field.getName());
        }
        return fieldsName;
    }

    public Object getFieldValueByName(String fieldName, Object o) {
        try {
            String e = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + e + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[0]);
            return method.invoke(o, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    private String getfieldSimpleType(String fieldName, Object o) {
        try {
            return o.getClass().getDeclaredField(fieldName).getType().getSimpleName();
        } catch (Exception e) {
            return "";
        }
    }

    public List<UserVO> list(Criteria where) {

        List<UserVO> list = new ArrayList<>();

        // 分页查询
//        String key = "username";
//        String value = "yudao";


        String key = "username";
        String value = "szh";
        int pageSize = 10;
        int pageIndex = 1;

        // 模糊匹配
        where.and(key).regex(value,"i");




        // 查询字段
        ProjectionOperation project = Aggregation.project(
                "id", "username","nickname","gender");

        // 匹配条件
        MatchOperation match = Aggregation.match(where);
        // 排序条件
        SortOperation sort = Aggregation.sort(Sort.by(Sort.Order.desc("username")));
        // 分页条件
        SkipOperation skip = Aggregation.skip((pageIndex - 1) * pageSize);
        LimitOperation limit = Aggregation.limit(pageSize);

        // 排序规则
        Collation collation = Collation.of("en").numericOrdering(true);
        AggregationOptions aggregationOptions = new AggregationOptions.Builder().collation(collation).build();

        // 聚合条件--结果数据
        Aggregation aggregation = Aggregation
                .newAggregation(project, match, sort, skip, limit)
                .withOptions(aggregationOptions);

        // 聚合条件--分页数据
        Aggregation aggregationPage = Aggregation
                .newAggregation(project, match, sort)
                .withOptions(aggregationOptions);


        // 执行查询
        AggregationResults<UserVO> aggregate = mongoTemplate.aggregate(
                aggregation, // 条件
                "User", // 主表名
                UserVO.class); // 输出：数据结构

        AggregationResults<UserVO> aggregatePage = mongoTemplate.aggregate(
                aggregationPage, // 条件
                "User", // 主表名
                UserVO.class); // 输出：数据结构

        // 获取结果
        List<UserVO> userVOList = aggregate.getMappedResults();

        System.out.println(userVOList.size());
        System.out.println(userVOList);


        return userVOList;
    }
}
