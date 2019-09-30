package com.cskaoyan.mall.controller.mallmaneger;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.service.mallmaneger.CategoryService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 显示所有的商品及类目信息
     * @return
     * @author Zeng-jz
     */
    @RequestMapping("list")
    public BaseRespVo list(){
        List list = categoryService.list();
        return BaseRespVo.success(list);
    }

    /**
     * 显示L1级别的商品名称及编号
     * @return
     * @author Zeng-jz
     */
    @RequestMapping("l1")
    public BaseRespVo l1(){
        List list = categoryService.l1();
        return BaseRespVo.success(list);
    }

    /**
     * 修改选定的商品或商品类目信息
     * @param category
     * @return
     */
    @RequestMapping("update")
    public BaseRespVo update(@RequestBody Category category){
        if (categoryService.update(category) != 0) {
            return BaseRespVo.success(null);
        }else {
            return BaseRespVo.fail("参数不对");
        }
    }

    /**
     * 删除选定的商品或商品类目
     * @param category
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Category category){
        categoryService.delete(category);
        return BaseRespVo.success(null);
    }
}
