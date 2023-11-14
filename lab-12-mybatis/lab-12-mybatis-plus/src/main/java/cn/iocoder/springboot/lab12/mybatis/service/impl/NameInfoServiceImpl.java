package cn.iocoder.springboot.lab12.mybatis.service.impl;

import cn.iocoder.springboot.lab12.mybatis.mapper.NameInfoMapper;
import cn.iocoder.springboot.lab12.mybatis.service.NameInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.springboot.lab12.mybatis.dataobject.NameInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author demussong
* @description 针对表【name_info】的数据库操作Service实现
* @createDate 2023-07-04 17:18:27
*/
@Service
public class NameInfoServiceImpl extends ServiceImpl<NameInfoMapper, NameInfo>
    implements NameInfoService {

    @Resource
    private NameInfoMapper nameInfoMapper;

    public void insert(NameInfo nameInfo) {
        nameInfoMapper.insert(nameInfo);
    }

    @Override
    public void throwE() {
        int x = 4;
        if (x == 4) {
            throw new RuntimeException("diy exception");
        }
    }
}




