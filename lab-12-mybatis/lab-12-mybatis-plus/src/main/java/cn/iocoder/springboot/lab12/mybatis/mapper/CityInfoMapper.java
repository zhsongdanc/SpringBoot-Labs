package cn.iocoder.springboot.lab12.mybatis.mapper;

import cn.iocoder.springboot.lab12.mybatis.dataobject.CityInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author demussong
 * @description 针对表【city_info】的数据库操作Mapper
 * @createDate 2023-07-04 17:18:27
 * @Entity generator.domain.CityInfo
 */
public interface CityInfoMapper extends BaseMapper<CityInfo> {

    Page<CityInfo> selectALl(Page<CityInfo> page);
}

