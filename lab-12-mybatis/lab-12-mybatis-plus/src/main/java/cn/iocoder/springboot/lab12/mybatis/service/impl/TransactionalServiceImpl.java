package cn.iocoder.springboot.lab12.mybatis.service.impl;

import cn.iocoder.springboot.lab12.mybatis.dataobject.CityInfo;
import cn.iocoder.springboot.lab12.mybatis.dataobject.NameInfo;
import cn.iocoder.springboot.lab12.mybatis.service.CityInfoService;
import cn.iocoder.springboot.lab12.mybatis.service.NameInfoService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/7/12 16:43
 */
@Service
public class TransactionalServiceImpl {

    @Resource
    private CityInfoService cityInfoService;

    @Resource
    private NameInfoService nameInfoService;

    @Transactional
    public void insertCityInfo() {
        CityInfo cityInfo = new CityInfo();
        cityInfo.setId("666666");
        cityInfo.setCity("NewYork");
        cityInfoService.insert(cityInfo);
        nameInfoService.throwE();
//        insertNameInfo(7);

    }

//    @Transactional
    public void insertNameInfo(int a) {
        NameInfo nameInfo = new NameInfo();
        nameInfo.setId("666");
        nameInfo.setName("szh-NewYork");
        nameInfoService.insert(nameInfo);

        if (a == 7) {
            throw new RuntimeException("diy exception");
        }
    }



}
