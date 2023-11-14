package cn.iocoder.springboot.lab12.mybatis.mapper;

import cn.iocoder.springboot.lab12.mybatis.Application;
import cn.iocoder.springboot.lab12.mybatis.service.impl.TransactionalServiceImpl;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/7/12 17:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TranTest {

    @Resource
    private TransactionalServiceImpl transactionalService;

    @Test
    public void test() {
        transactionalService.insertCityInfo();
    }
}
