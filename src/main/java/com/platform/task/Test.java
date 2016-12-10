package com.platform.task;

import us.codecraft.webmagic.Spider;

/**
 * Created by Administrator on 2015/11/3.
 */
public class Test {

    public static void main(String[] args){
        ArticleSpider articleSpider = new ArticleSpider();
        Spider.create(articleSpider).addPipeline(new ArticlePipeline())
                .addUrl("http://www.icoolxue.com/album/affix/view/php/1/8?orderBy=create_time").thread(1).run();

        System.out.println(articleSpider.getList());
    }
}
