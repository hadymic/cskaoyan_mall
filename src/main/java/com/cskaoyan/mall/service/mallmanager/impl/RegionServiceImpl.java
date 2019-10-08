package com.cskaoyan.mall.service.mallmanager.impl;

        import com.cskaoyan.mall.bean.Region;
        import com.cskaoyan.mall.mapper.RegionMapper;
        import com.cskaoyan.mall.service.mallmanager.RegionService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.List;

/**
 * 商场Service实现类--行政区域
 *
 * @author Zeng-jz
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionMapper regionMapper;

    /**
     * 获取所有的行政区域详细信息
     * @return
     */
    @Override
    public List list() {
        List<Region> regionList = regionMapper.selectRegions();
        return regionList;
    }
}
