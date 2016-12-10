package com.platform.service.Impl;

import com.platform.dao.impl.BaseDaoImpl;
import com.platform.entities.Video;
import com.platform.service.VideoService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/12/2.
 */
@Service("videoService")
public class VideoServiceImpl extends BaseDaoImpl<Video> implements VideoService {
}
