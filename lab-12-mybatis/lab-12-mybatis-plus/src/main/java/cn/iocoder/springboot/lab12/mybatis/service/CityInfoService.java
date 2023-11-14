package cn.iocoder.springboot.lab12.mybatis.service;

import cn.iocoder.springboot.lab12.mybatis.dataobject.CityInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author demussong
* @description 针对表【city_info】的数据库操作Service
* @createDate 2023-07-04 17:18:27
*/
public interface CityInfoService extends IService<CityInfo> {
    public void insert(CityInfo cityInfo);
    public Page<CityInfo> testWithPage();
}
