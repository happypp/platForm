package com.platform.service.Impl;

import com.platform.dao.impl.BaseDaoImpl;
import com.platform.entities.Chapter;
import com.platform.service.ChapterService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/12/2.
 */
@Service("chapterService")
public class ChapterServiceImpl extends BaseDaoImpl<Chapter> implements ChapterService {
}
