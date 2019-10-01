package com.cskaoyan.mall.controller.promotion;

import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.service.promotion.GrouponService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.GrouponVo;
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
    public BaseRespVo showGrouponRulesList(Page page, Integer goodsId) {
        ListBean<GrouponRules> grouponRulesListBean = grouponService.queryGrouponRuless(page, goodsId);
        return BaseRespVo.success(grouponRulesListBean);
    }

    /**
     * 添加团购规则
     *
     * @param grouponRules
     * @return
     */
    @PostMapping("create")
    public BaseRespVo insertGrouponRules(@RequestBody GrouponRules grouponRules) {
        String msg = grouponService.insertGrouponRules(grouponRules);
        return msg == null ? BaseRespVo.success(null) : BaseRespVo.fail(msg);
    }

    /**
     * 修改团购规则
     *
     * @param grouponRules
     * @return
     */
    @PostMapping("update")
    public BaseRespVo updateGrouponRules(@RequestBody GrouponRules grouponRules) {
        String msg = grouponService.updateGrouponRules(grouponRules);
        return msg == null ? BaseRespVo.success(null) : BaseRespVo.fail(msg);
    }

    /**
     * 删除团购规则
     *
     * @param grouponRules
     * @return
     */
    @PostMapping("delete")
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
    public BaseRespVo showListRecord(Page page, Integer goodsId) {
        ListBean<GrouponVo> grouponVoListBean = grouponService.queryGrouponVo(page, goodsId);
        return BaseRespVo.success(grouponVoListBean);
    }
}
