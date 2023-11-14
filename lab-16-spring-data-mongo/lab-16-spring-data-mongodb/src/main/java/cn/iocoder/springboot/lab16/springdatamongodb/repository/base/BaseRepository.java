package cn.iocoder.springboot.lab16.springdatamongodb.repository.base;

import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.result.UpdateResult;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/*
 * @Author: yubinzhong
 * @Date: 2022-05-20 20:16:58
 * @Last Modified by: yubinzhong
 * @Last Modified time: 2022-05-26 20:26:39
 */

@Component
public abstract class BaseRepository<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    /***
     * 获取对象属性返回字符串数组
     */
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

    protected abstract Class<T> getEntityClass();

    public MongoTemplate getMongoTemplate() {
        return this.mongoTemplate;
    }

    /**
     * 保存一个对象
     *
     * @param t 对象
     */
    public T insert(T t) {
        this.mongoTemplate.insert(t);
        return t;
    }

    /**
     * 批量保存对象，返回成功条数
     *
     * @param array 批量数据源
     * @return 成功条数
     */
    public int insertMany(List<T> array) {
        BulkOperations operations = this.mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED,
                this.getEntityClass());
        array.forEach(operations::insert);
        return operations.execute().getInsertedCount();
    }

    /**
     * 根据主键id，获取所有对象
     *
     * @param id id
     * @return 对象
     */
    public List<T> findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return this.mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     * 根据主键id，获取第一个对象
     *
     * @param id id
     * @return 对象
     */
    public T findOneById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }

    /**
     * 根据某个字段，获取所有对象
     *
     * @param field 字段名
     * @param value 字段value
     * @return T
     */
    public List<T> findByField(String field, Object value) {
        Query query = new Query(Criteria.where(field).is(value));
        return this.mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     * 根据某个字段查找，并根据某个字段排序
     *
     * @param field 查找所依据的字段
     * @param value 查找的值
     * @param field2 排序所依据的字段
     * @param direction 顺序还是逆序
     * @return
     */
    public List<T> findByFieldWithSort(String field, Object value, String field2, Sort.Direction direction) {
        Query query = new Query(Criteria.where(field).is(value)).with(Sort.by(direction, field2));
        return this.mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     * 根据某个字段，获取所有对象
     *
     * @param field 字段名
     * @param value 字段value
     * @return T
     */
    public List<T> findInField(String field, List<String> value) {
        Query query = new Query(Criteria.where(field).in(value));
        return this.mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     * 同时满足两个字段条件的一组对象
     *
     * @param field1
     * @param value1
     * @param field2
     * @param value2
     * @return
     */
    public List<T> findByTwoField(String field1, Object value1, String field2, Object value2) {
        Query query = new Query(Criteria.where(field1).is(value1).andOperator(Criteria.where(field2).is(value2)));
        return this.mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     * 根据某个字段，获取一个对象
     *
     * @param field 字段名
     * @param value 字段value
     * @return T
     */
    public T findOneByField(String field, Object value) {
        Query query = new Query(Criteria.where(field).is(value));
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }

    /**
     * 根据主键id，删除对象
     *
     * @param id ID
     */
    public int deleteById(String id) {
        Criteria criteria = Criteria.where("_id").is(id);
        if (null != criteria) {
            Query query = new Query(criteria);
            T obj = this.mongoTemplate.findOne(query, this.getEntityClass());
            if (!ObjectUtils.isEmpty(obj)) {
                return this.delete(obj);
            }
        }
        return 0;
    }

    /*
     * MongoDB中更新操作分为三种
     * 1：updateFirst 修改第一条
     * 2：updateMulti 修改所有匹配的记录
     * 3：upsert 修改时如果不存在则进行添加操作
     */

    /***
     * 根据对象，删除对象
     *
     * @param t 对象
     * @return 影响行数
     */
    public int delete(T t) {
        return (int) this.mongoTemplate.remove(t).getDeletedCount();
    }

    /**
     * 修改匹配到的第一条记录
     *
     * @param srcObj 源
     * @param targetObj 目标
     */
    public UpdateResult updateFirst(T srcObj, T targetObj) {
        Query query = getQueryByObject(srcObj);
        Update update = getUpdateByObject(targetObj);
        return this.mongoTemplate.updateFirst(query, update, this.getEntityClass());
    }

    /**
     * 修改指定ID的记录
     *
     * @param id
     * @param targetObj
     * @return
     */
    public UpdateResult updateFirstByID(String id, T targetObj) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = getUpdateByObject(targetObj);
        return this.mongoTemplate.updateFirst(query, update, this.getEntityClass());
    }

    /**
     * 更新所有字段
     *
     * @param id
     * @param targetObj
     * @return
     */
    public UpdateResult updateAllFields(String id, T targetObj) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = getAllFieldsUpdate(targetObj);
        return this.mongoTemplate.updateFirst(query, update, this.getEntityClass());
    }

    /***
     * 修改匹配到的所有记录
     *
     * @param srcObj    源
     * @param targetObj 目标
     */
    public void updateMulti(T srcObj, T targetObj) {
        Query query = getQueryByObject(srcObj);
        Update update = getUpdateByObject(targetObj);
        this.mongoTemplate.updateMulti(query, update, this.getEntityClass());
    }

    /**
     * 批量更新，通过主键
     *
     * @param update
     */
    public int updateList(List<T> update) {
        BulkOperations bulkOperations = this.mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED,
                this.getEntityClass());

        update.forEach(item -> {
            Object idObj = getFieldValueByName("ID", item);
            Query queryUpdate = new Query();
            queryUpdate.addCriteria(Criteria.where("_id").is(idObj));
            Update updateQueryObject = getUpdateByObject(item);
            bulkOperations.updateOne(queryUpdate, updateQueryObject);
        });
        BulkWriteResult result = bulkOperations.execute();
        return result.getMatchedCount();
    }

    /**
     * 将查询条件对象转换为query
     *
     * @param object
     * @return
     * @author Jason
     */
    private Query getQueryByObject(T object) {
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

    /**
     * 将查询条件对象转换为update
     *
     * @param object
     * @return
     * @author Jason
     */
    public Update getUpdateByObject(T object) {
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

    /**
     * 将查询条件对象转换为update
     *
     * @param object
     * @return
     * @author Jason
     */
    private Update getAllFieldsUpdate(T object) {
        Update update = new Update();
        List<String> fields = getfieldName(object);
        for (String field : fields) {
            Object fieldValue = getFieldValueByName(field, object);
            update.set(field, fieldValue);
        }
        return update;
    }

    /***
     * 根据属性获取对象属性值
     *
     * @param fieldName
     * @param o
     * @return
     */
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

    /**
     * getfieldSimpleType
     *
     * @param fieldName
     * @param o
     * @return
     */
    private String getfieldSimpleType(String fieldName, Object o) {
        try {
            return o.getClass().getDeclaredField(fieldName).getType().getSimpleName();
        } catch (Exception e) {
            return "";
        }
    }
}
