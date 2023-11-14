package cn.iocoder.springboot.lab16.springdatamongodb.repository;

import com.mongodb.MongoClient;
import org.bson.*;
import com.mongodb.*;
import com.mongodb.client.*;
import org.junit.Test;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/1/4 18:28
 */
public class MongoDriveTest {

    @Test
    public void testWriteAndRead() {
        String mongoUri = "mongodb://localhost:27017"; //  多 IP 实例 Uri 可参考：mongodb://mongouser:******@172.xx.xx.124:27017,172.xx.xx.27:27017,172.xx.xx.136:27017/test?authSource=admin&replicaSet=cmgo-fsstfgob_0
        MongoClientURI connStr = new MongoClientURI(mongoUri);
        MongoClient mongoClient = new MongoClient(connStr);
        try {
            // 使用名为 someonedb 的数据库
            MongoDatabase database = mongoClient.getDatabase("test");
            // 取得集合/表 someonetable 句柄
            MongoCollection<Document> collection = database.getCollection("People");


            // 准备写入数据
            Document doc = new Document();
            doc.append("name", "demus");
            doc.append("job", "coder");
            doc.append("age", 24);


            // 写入数据
            collection.insertOne(doc);
            System.out.println("insert document: " + doc);


            // 读取数据
            BsonDocument filter = new BsonDocument();
            filter.append("name", new BsonString("demus"));
            MongoCursor<Document> cursor = collection.find(filter).iterator();
            while (cursor.hasNext()) {
                System.out.println("find document: " + cursor.next());
            }
        } finally {
            //关闭连接
            mongoClient.close();
        }

    }
}
