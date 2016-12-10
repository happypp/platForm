package com.platform.task;

import com.platform.entities.Chapter;
import com.platform.entities.Course;
import com.platform.entities.Video;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class ArticleSpider implements PageProcessor {

	private List<Course> list = new ArrayList<Course>();
	private List<Chapter> chapterLists = new ArrayList<Chapter>();
	private List<Video> videoLists = new ArrayList<Video>();
	private List<String> videoListVideoAddr = new ArrayList<String>();


	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		//course(page);
		chapter(page);
	}

	public void chapter(Page page){
		System.out.println(page.getUrl() + " getContent");
		List<String> chapters = null;
		chapters = page.getHtml().xpath("//div[@class='chapter']/h3/strong/text()")
				.all();
		System.out.println(chapters);

		List<String> videos = null;
		videos = page.getHtml().xpath("//ul[@class='video']/li/a/text()")
				.all();
		System.out.println(videos);

		List<String> videoAs = null;
		videoAs = page.getHtml().xpath("//ul[@class='video']/li/a/@href")
				.all();
		System.out.println(videoAs);

//		List<String> courseinformations = null;
//		courseinformations = page.getHtml().xpath("//div[@class='course-info-tip']/dl[1]/dd/text()")
//				.all();
//		System.out.println(courseinformations);
//
//		List<String> learninginformations = null;
//		learninginformations = page.getHtml().xpath("//div[@class='course-info-tip']/dl[2]/dd/text()")
//				.all();
//		System.out.println(learninginformations);

		for(int i=0; i<videos.size(); i++){
			videoLists.add(new Video(videos.get(i)));
		}

		for(int i = 0;i < chapters.size();i++){
			chapterLists.add(new Chapter(chapters.get(i),32));
		}
	}


	public void course(Page page){
		System.out.println(page.getUrl() + " getContent");
		List<String> courseimgs = null;
		courseimgs = page.getHtml().xpath("//div[@class='course-list-img']/img/@src")
				.all();
		System.out.println(courseimgs);

		List<String> titles = null;
		titles = page.getHtml()
				.xpath("//li[@class='course-one']/a/h5/span/text()").all();
		System.out.println(titles);

		List<String> tips = null;
		tips = page.getHtml()
				.xpath("//p[@class='text-ellipsis']/text()").all();
		System.out.println(tips);

		for(int i = 0;i < courseimgs.size();i++){
			list.add(new Course(courseimgs.get(i),titles.get(i),tips.get(i),0,1));
		}
	}

	@Override
	public Site getSite() {
		return site;
	}

	public List<Chapter> getChapterLists() {
		return chapterLists;
	}

	public void setChapterLists(List<Chapter> chapterLists) {
		this.chapterLists = chapterLists;
	}

	public List<Video> getVideoLists() {
		return videoLists;
	}

	public void setVideoLists(List<Video> videoLists) {
		this.videoLists = videoLists;
	}

	public List<Course> getList() {
		return list;
	}

	public void setList(List<Course> list) {
		this.list = list;
	}

	public List<String> getVideoListVideoAddr() {
		return videoListVideoAddr;
	}

	public void setVideoListVideoAddr(List<String> videoListVideoAddr) {
		this.videoListVideoAddr = videoListVideoAddr;
	}
}
