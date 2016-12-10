package com.platform.controller;

import com.platform.sensitive.SensitivewordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by lenovo on 2016/4/2.
 */
@Controller
@RequestMapping("/sensitive")
public class SensitiveController {
    @Autowired
    private SensitivewordFilter sensitivewordFilter;

    @RequestMapping("/filter")
    public void sensitiveFilter(@RequestParam(value = "content") String content,
                                @RequestParam(value = "title", required = false) String title,
                                HttpServletResponse resp) {
        System.out.println("敏感词的数量：" + sensitivewordFilter.sensitiveWordMap.size());
        String contents = content.replace("&nbsp;", "");
        contents = contents.replace(" ", "");
        System.out.println(contents);
        System.out.println("待检测语句字数：" + contents.length());
        long beginTime = System.currentTimeMillis();
        Set<String> set = sensitivewordFilter.getSensitiveWord(contents, 1);
        long endTime = System.currentTimeMillis();
        System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
        System.out.println("总共消耗时间为：" + (endTime - beginTime));
        if (set.size() > 0) {
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                String str = it.next();
                contents = contents.replace(str, "***");
            }
        } else {
            contents = content;
        }
        if (title != null) {
            String titles = title.replace("&nbsp;", "");
            titles = titles.replace(" ", "");
            System.out.println(title);
            System.out.println("待检测语句字数：" + titles.length());
            long beginTimes = System.currentTimeMillis();
            Set<String> sets = sensitivewordFilter.getSensitiveWord(titles, 1);
            long endTimes = System.currentTimeMillis();
            System.out.println("语句中包含敏感词的个数为：" + sets.size() + "。包含：" + sets);
            System.out.println("总共消耗时间为：" + (endTimes - beginTimes));
            if (set.size() > 0) {
                Iterator<String> it = set.iterator();
                while (it.hasNext()) {
                    String str = it.next();
                    titles = titles.replace(str, "***");
                }
            } else {
                titles = title;
            }
            contents = contents + "," + titles;
        }

        System.out.println(contents);
        resp.setCharacterEncoding("UTF-8"); // 输出流使用utf-8编码
        try {
            resp.getWriter().print(contents);// 输出返回值
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
