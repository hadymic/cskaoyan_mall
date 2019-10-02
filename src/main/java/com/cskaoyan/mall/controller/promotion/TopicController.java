package com.cskaoyan.mall.controller.promotion;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.service.promotion.TopicService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
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

    /**
     * 显示专题列表
     *
     * @param page
     * @return
     */
    @GetMapping("list")
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
    public BaseRespVo insertTopic(@RequestBody Topic topic) {
        Topic newTopic = topicService.insertTopic(topic);
        return newTopic != null ? BaseRespVo.success(newTopic) : BaseRespVo.fail("添加专题失败");
    }

    /**
     * 修改专题
     *
     * @param topic
     * @return
     */
    @PostMapping("update")
    public BaseRespVo updateTopic(@RequestBody Topic topic) {
        Topic newTopic = topicService.updateTopic(topic);
        return newTopic != null ? BaseRespVo.success(newTopic) : BaseRespVo.fail("更新专题失败");
    }

    /**
     * 删除专题
     *
     * @param topic
     * @return
     */
    @PostMapping("delete")
    public BaseRespVo deleteTopic(@RequestBody Topic topic) {
        boolean flag = topicService.deleteTopic(topic.getId());
        return flag ? BaseRespVo.success(null) : BaseRespVo.fail("专题删除失败");
    }
}
