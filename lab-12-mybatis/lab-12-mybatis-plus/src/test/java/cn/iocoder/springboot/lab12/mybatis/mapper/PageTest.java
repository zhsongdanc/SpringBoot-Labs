package cn.iocoder.springboot.lab12.mybatis.mapper;

import cn.iocoder.springboot.lab12.mybatis.Application;
import cn.iocoder.springboot.lab12.mybatis.dataobject.CityInfo;
import cn.iocoder.springboot.lab12.mybatis.service.CityInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/7/13 13:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PageTest {

    @Resource
    private CityInfoService cityInfoService;

    @Test
    public void testPage() {
        Page<CityInfo> cityInfoPage = cityInfoService.testWithPage();
        System.out.println("");
    }

}
