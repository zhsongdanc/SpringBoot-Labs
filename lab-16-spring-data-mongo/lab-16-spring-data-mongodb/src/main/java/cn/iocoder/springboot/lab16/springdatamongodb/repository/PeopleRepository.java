package cn.iocoder.springboot.lab16.springdatamongodb.repository;

import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.PartPeopleDO;
import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.PeopleDO;
import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.UserDO;
import cn.iocoder.springboot.lab16.springdatamongodb.repository.base.BaseRepository;
import cn.iocoder.springboot.lab16.springdatamongodb.vo.UserVO;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
import org.springframework.util.StringUtils;

@Repository
public class PeopleRepository extends BaseRepository<PeopleDO> {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    protected Class<PeopleDO> getEntityClass() {
        return PeopleDO.class;
    }

    public List<PeopleDO> queryPeopleDOList(){
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("age").is(12), Criteria.where("age").is(null));

        Query query = new Query();
        query.addCriteria(criteria);

        return mongoTemplate.find(query, PeopleDO.class);
    }

    public List<PeopleDO> find(Criteria criteria) {
        Query query = new Query(criteria);
        return super.getMongoTemplate().find(query, PeopleDO.class);
    }

    public List<PeopleDO> findWithAggregation() {
        Criteria criteria = new Criteria();
        criteria.and("where").is("sz");
        MatchOperation matchOperation = Aggregation.match(criteria);

        ProjectionOperation projectionOperation = Aggregation.project("name", "job", "where");
        Aggregation aggregation = Aggregation.newAggregation(projectionOperation, matchOperation);
        AggregationResults<PeopleDO> aggregate = mongoTemplate.aggregate(aggregation, "People", PeopleDO.class);
        return aggregate.getMappedResults();
    }

    public List<PeopleDO> regexWithChinese() {
        Criteria criteria = new Criteria();
        criteria.and("name").regex("好");
        MatchOperation matchOperation = Aggregation.match(criteria);

        ProjectionOperation projectionOperation = Aggregation.project("name", "job", "where");
        Aggregation aggregation = Aggregation.newAggregation(projectionOperation, matchOperation);
        AggregationResults<PeopleDO> aggregate = mongoTemplate.aggregate(aggregation, "People", PeopleDO.class);
        return aggregate.getMappedResults();
    }

    public List<PeopleDO> embedCriteria() {
        Criteria criteria = new Criteria();

        String code = "pub demus";
        String[] strs = code.split(" ");
        List<Criteria> criteriaList = new ArrayList<>();

        Arrays.stream(strs).map(item -> criteriaList.add(Criteria.where("name").regex(item))).collect(Collectors.toList());
        Criteria splitCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));

        criteria.orOperator(Criteria.where("name").regex("default"), splitCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        ProjectionOperation projectionOperation = Aggregation.project("name", "job", "where");
        Aggregation aggregation = Aggregation.newAggregation(projectionOperation, matchOperation);
        AggregationResults<PeopleDO> aggregate = mongoTemplate.aggregate(aggregation, "People", PeopleDO.class);
        return aggregate.getMappedResults();
    }

    public List<PartPeopleDO> testProjection() {
        ProjectionOperation projectionOperation = Aggregation.project("models", "name", "job", "age", "summary");
        Aggregation aggregation = Aggregation.newAggregation(projectionOperation);
        AggregationResults<PartPeopleDO> aggregate = mongoTemplate.aggregate(aggregation, "People", PartPeopleDO.class);
        return aggregate.getMappedResults();
    }

    public List<PeopleDO> testWithTwoAndOperator() {
        Criteria criteria = new Criteria();
//        criteria.andOperator(Criteria.where("age").lt(10), Criteria.where("age").gt(0));
//        criteria.andOperator(Criteria.where("count").lt(10), Criteria.where("count").gt(0));
        List<Criteria> criteriaList = new ArrayList<>();
        criteriaList.add(new Criteria().and("count").lt(10));
        criteriaList.add(new Criteria().and("count").gt(0));
        Criteria criteria1 = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
        criteria.orOperator(criteria1, Criteria.where("count").is(5));

        criteria.andOperator(Criteria.where("name").regex("demus"), Criteria.where("name").regex("0213"));

        MatchOperation matchOperation = Aggregation.match(criteria);

        ProjectionOperation projectionOperation = Aggregation.project("name", "job", "where", "age", "count");
        Aggregation aggregation = Aggregation.newAggregation(projectionOperation, matchOperation);
        AggregationResults<PeopleDO> aggregate = mongoTemplate.aggregate(aggregation,  "People", PeopleDO.class);
        return aggregate.getMappedResults();
    }
}

