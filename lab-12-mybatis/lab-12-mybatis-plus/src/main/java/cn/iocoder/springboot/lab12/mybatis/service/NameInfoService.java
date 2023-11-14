package cn.iocoder.springboot.lab12.mybatis.service;

import cn.iocoder.springboot.lab12.mybatis.dataobject.NameInfo;
import cn.iocoder.springboot.lab12.mybatis.mapper.NameInfoMapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author demussong
* @description 针对表【name_info】的数据库操作Service
* @createDate 2023-07-04 17:18:27
*/
public interface NameInfoService extends IService<NameInfo> {

    public void insert(NameInfo nameInfo);

    void throwE();
}
