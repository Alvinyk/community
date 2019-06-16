package org.study.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yangkai
 * @description 项目主页
 * @date 2019/5/31 16:21
 **/

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
