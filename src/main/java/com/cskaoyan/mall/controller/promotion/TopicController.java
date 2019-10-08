package com.cskaoyan.mall.controller.promotion;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.service.admin.LogService;
import com.cskaoyan.mall.service.promotion.TopicService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 专题管理
 *
 * @author hadymic
 */
@RestController
@RequestMapping("admin/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private LogService logService;

    /**
     * 显示专题列表
     *
     * @param page
     * @return
     */
    @GetMapping("list")
    @RequiresPermissions("admin:topic:list")
    public BaseRespVo showTopicList(Page page, String title, String subtitle) {
        ListBean<Topic> topicListBean = topicService.queryTopics(page, title, subtitle);
        return BaseRespVo.success(topicListBean);
    }

    /**
     * 添加专题
     *
     * @param topic
     * @return
     */
    @PostMapping("create")
    @RequiresPermissions("admin:topic:create")
    public BaseRespVo insertTopic(@RequestBody Topic topic) {
        Topic newTopic = topicService.insertTopic(topic);
        if (newTopic != null) {
            logService.log(1, "添加专题", true);
            return BaseRespVo.success(newTopic);
        } else {
            logService.log(1, "添加专题", false);
            return BaseRespVo.fail("专题添加失败");
        }
    }

    /**
     * 修改专题
     *
     * @param topic
     * @return
     */
    @PostMapping("update")
    @RequiresPermissions("admin:topic:update")
    public BaseRespVo updateTopic(@RequestBody Topic topic) {
        Topic newTopic = topicService.updateTopic(topic);
        if (newTopic != null) {
            logService.log(1, "修改专题", true);
            return BaseRespVo.success(newTopic);
        } else {
            logService.log(1, "修改专题", false);
            return BaseRespVo.fail("专题修改失败");
        }
    }

    /**
     * 删除专题
     *
     * @param topic
     * @return
     */
    @PostMapping("delete")
    @RequiresPermissions("admin:topic:delete")
    public BaseRespVo deleteTopic(@RequestBody Topic topic) {
        boolean flag = topicService.deleteTopic(topic.getId());
        if (flag) {
            logService.log(1, "删除专题", true);
            return BaseRespVo.success(null);
        } else {
            logService.log(1, "删除专题", false);
            return BaseRespVo.fail("专题删除失败");
        }
    }
}
