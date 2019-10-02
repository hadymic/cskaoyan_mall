package com.cskaoyan.mall.controller.mallmanager;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.service.mallmanager.BrandService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商场管理--品牌制造商
 *
 * @author Zeng-jz
 */
@RestController
@RequestMapping("admin/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    /**
     * 显示品牌商信息
     * @param page
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo list(Page page, String id, String name){
        ListBean brandList = brandService.list(page, id, name);
        return BaseRespVo.success(brandList);
    }

    /**
     * 删除选定的品牌商
     * @param brand
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Brand brand){
        brandService.delete(brand);
        return BaseRespVo.success(null);
    }

    /**
     * 修改选定的品牌商信息
     * @param brand
     * @return
     */
    @RequestMapping("update")
    public BaseRespVo update(@RequestBody Brand brand){
        Brand newBrand = brandService.update(brand);
        if (newBrand != null) {
            return BaseRespVo.success(newBrand);
        }else {
            return BaseRespVo.fail("参数不对");
        }
    }
}
