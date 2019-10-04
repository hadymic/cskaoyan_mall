package com.cskaoyan.mall.controller.promotion;

import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.service.promotion.GrouponService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.StringUtils;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.promotion.GrouponVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 团购管理
 *
 * @author hadymic
 */
@RestController
@RequestMapping("admin/groupon")
public class GrouponController {
    @Autowired
    private GrouponService grouponService;

    /**
     * 显示团购规则列表
     *
     * @param page
     * @return
     */
    @GetMapping("list")
    @RequiresPermissions("admin:groupon:list")
    public BaseRespVo showGrouponRulesList(Page page, String goodsId) {
        Integer goodsid = null;
        if (!StringUtils.isEmpty(goodsId)) {
            try {
                goodsid = Integer.parseInt(goodsId);
            } catch (NumberFormatException e) {
                return BaseRespVo.success(null);
            }
        }
        ListBean<GrouponRules> grouponRulesListBean = grouponService.queryGrouponRuless(page, goodsid);
        return BaseRespVo.success(grouponRulesListBean);
    }

    /**
     * 添加团购规则
     *
     * @param grouponRules
     * @return
     */
    @PostMapping("create")
    @RequiresPermissions("admin:groupon:create")
    public BaseRespVo insertGrouponRules(@RequestBody GrouponRules grouponRules) {
        GrouponRules newGrouponRules = grouponService.insertGrouponRules(grouponRules);
        return newGrouponRules != null ? BaseRespVo.success(newGrouponRules) : BaseRespVo.fail("添加团购规则失败");
    }

    /**
     * 修改团购规则
     *
     * @param grouponRules
     * @return
     */
    @PostMapping("update")
    @RequiresPermissions("admin:groupon:update")
    public BaseRespVo updateGrouponRules(@RequestBody GrouponRules grouponRules) {
        GrouponRules newGrouponRules = grouponService.updateGrouponRules(grouponRules);
        return newGrouponRules != null ? BaseRespVo.success(newGrouponRules) : BaseRespVo.fail("更新团购规则失败");
    }

    /**
     * 删除团购规则
     *
     * @param grouponRules
     * @return
     */
    @PostMapping("delete")
    @RequiresPermissions("admin:groupon:delete")
    public BaseRespVo deleteGrouponRules(@RequestBody GrouponRules grouponRules) {
        boolean flag = grouponService.deleteGrouponRules(grouponRules.getId());
        return flag ? BaseRespVo.success(null) : BaseRespVo.fail("团购规则删除失败");
    }

    /**
     * 显示团购规则列表
     *
     * @param page
     * @return
     */
    @GetMapping("listRecord")
    @RequiresPermissions("admin:groupon:read")
    public BaseRespVo showListRecord(Page page, String goodsId) {
        Integer goodsid = null;
        if (!StringUtils.isEmpty(goodsId)) {
            try {
                goodsid = Integer.parseInt(goodsId);
            } catch (NumberFormatException e) {
                return BaseRespVo.success(null);
            }
        }
        ListBean<GrouponVo> grouponVoListBean = grouponService.queryGrouponVo(page, goodsid);
        return BaseRespVo.success(grouponVoListBean);
    }
}
