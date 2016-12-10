package com.platform.service.Impl;

import com.platform.dao.impl.BaseDaoImpl;
import com.platform.entities.CourseType;
import com.platform.service.CourseTypeService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/11/25.
 */
@Service("courseTypeService")
public class CourseTypeServiceImpl extends BaseDaoImpl<CourseType> implements CourseTypeService {
}
