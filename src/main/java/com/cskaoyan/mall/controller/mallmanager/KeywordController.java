package com.cskaoyan.mall.controller.mallmanager;

import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.service.admin.LogService;
import com.cskaoyan.mall.service.mallmanager.KeywordService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.StringUtils;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jszza
 */
@RestController
@RequestMapping("admin/keyword")
public class KeywordController {
    @Autowired
    private KeywordService keywordService;
    @Autowired
    private LogService logService;


    /**
     * 分页查询关键字
     * @param page
     * @param keyword
     * @param url
     * @return
     */
    @RequestMapping("list")
    @RequiresPermissions(value = "admin:keyword:list")
    public BaseRespVo issueList(Page page, String keyword, String url){
        ListBean<Keyword> data = keywordService.queryKeywordList(page,keyword,url);
        return BaseRespVo.success(data);
    }

    /**
     * 创建keyword
     * @param keyword
     * @return
     */
    @RequestMapping("create")
    @RequiresPermissions(value = "admin:keyword:create")
    public BaseRespVo createKeyword(@RequestBody Keyword keyword){
        if (StringUtils.isEmpty(keyword.getUrl())) {
            logService.log(1, "添加关键字", false);
            return BaseRespVo.fail("参数不对");
        }
        logService.log(1, "添加关键字", true);
        return BaseRespVo.success(keywordService.insertKeyword(keyword));
    }

    /**
     * 更新keyword
     * @param keyword
     * @return
     */
    @RequestMapping("update")
    @RequiresPermissions(value = "admin:keyword:update")
    public BaseRespVo updateKeyword(@RequestBody Keyword keyword){
        if (StringUtils.isEmpty(keyword.getUrl())) {
            logService.log(1, "修改关键字", false);
            return BaseRespVo.fail("参数不对");
        }
        logService.log(1, "修改关键字", true);
        return BaseRespVo.success(keywordService.updateKeyword(keyword));
    }

    /**
     * 删除keyword
     * @param keyword
     * @return
     */
    @RequestMapping("delete")
    @RequiresPermissions(value = "admin:keyword:delete")
    public BaseRespVo deleteKeyword(@RequestBody Keyword keyword){
        keywordService.deleteKeyword(keyword.getId());
        logService.log(1, "删除关键字", true);
        BaseRespVo baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
