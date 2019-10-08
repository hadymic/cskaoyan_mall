package com.cskaoyan.mall.controller.mallmanager;


import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.service.admin.LogService;
import com.cskaoyan.mall.service.mallmanager.CategoryService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LogService logService;

    /**
     * 显示所有的商品及类目信息
     * @return
     * @author Zeng-jz
     */
    @RequestMapping("list")
    @RequiresPermissions(value = "admin:category:list")
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
    @RequiresPermissions(value = "admin:category:list")
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
    @RequiresPermissions(value = "admin:category:update")
    public BaseRespVo update(@RequestBody Category category){
        if (categoryService.update(category) != 0) {
            logService.log(1, "修改类目", true);
            return BaseRespVo.success(null);
        }else {
            logService.log(1, "修改类目", false);
            return BaseRespVo.fail("参数不对");
        }
    }

    /**
     * 删除选定的商品或商品类目
     * @param category
     * @return
     */
    @RequestMapping("delete")
    @RequiresPermissions(value = "admin:category:delete")
    public BaseRespVo delete(@RequestBody Category category){
        categoryService.delete(category);
        logService.log(1, "删除类目", true);
        return BaseRespVo.success(null);
    }

    /**
     * 增加商品
     * @param category
     * @return
     */
    @RequestMapping("create")
    @RequiresPermissions(value = "admin:category:create")
    public BaseRespVo create(@RequestBody Category category){
        Category newCategory = categoryService.create(category);
        if (newCategory != null) {
            logService.log(1, "添加类目", true);
            return BaseRespVo.success(newCategory);
        }else {
            logService.log(1, "添加类目", false);
            return BaseRespVo.fail("参数不对");
        }
    }
}
