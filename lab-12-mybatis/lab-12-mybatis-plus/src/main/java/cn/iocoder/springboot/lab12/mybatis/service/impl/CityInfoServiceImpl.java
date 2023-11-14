package cn.iocoder.springboot.lab12.mybatis.service.impl;

import cn.iocoder.springboot.lab12.mybatis.mapper.CityInfoMapper;
import cn.iocoder.springboot.lab12.mybatis.service.CityInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.springboot.lab12.mybatis.dataobject.CityInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author demussong
* @description 针对表【city_info】的数据库操作Service实现
* @createDate 2023-07-04 17:18:27
*/
@Service
public class CityInfoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo>
    implements CityInfoService {

    @Resource
    private CityInfoMapper cityInfoMapper;

    public void insert(CityInfo cityInfo) {
        cityInfoMapper.insert(cityInfo);
    }

    @Override
    public Page<CityInfo> testWithPage() {
        Page<CityInfo> page = new Page<>();
        page.setCurrent(1);
        page.setSize(2);
//        page.setOrders(condition.getOrderList());
        return cityInfoMapper.selectALl(page);
    }

}




