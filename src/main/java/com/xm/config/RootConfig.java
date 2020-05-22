package com.xm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author 尹晓蒙
 * @date 2020-05-21 14:35
 */
@Configuration
@Import(DataConfig.class)
@ComponentScan(basePackages = {"com.xm"},excludeFilters = {@ComponentScan.Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)})
public class RootConfig {
}
