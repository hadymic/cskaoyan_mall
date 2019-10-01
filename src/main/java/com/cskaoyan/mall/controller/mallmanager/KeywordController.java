package com.cskaoyan.mall.controller.mallmanager;

import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.service.mallmanager.KeywordService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
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
    KeywordService keywordService;


    /**
     * 分页查询关键字
     * @param page
     * @param keyword
     * @param url
     * @return
     */
    @RequestMapping("list")
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
    public BaseRespVo createKeyword(@RequestBody Keyword keyword){
        return BaseRespVo.success(keywordService.insertKeyword(keyword));
    }

    /**
     * 更新keyword
     * @param keyword
     * @return
     */
    @RequestMapping("update")
    public BaseRespVo updateKeyword(@RequestBody Keyword keyword){
        return BaseRespVo.success(keywordService.updateKeyword(keyword));
    }

    /**
     * 删除keyword
     * @param keyword
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo deleteKeyword(@RequestBody Keyword keyword){
        keywordService.deleteKeyword(keyword.getId());
        BaseRespVo baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
