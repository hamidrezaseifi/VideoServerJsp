package de.seifi.videomanagerJsp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {

    registry
        .addResourceHandler("/resources/**")
        .addResourceLocations("/WEB-INF/static/");
  }

  /*
   * @Bean public ViewResolver internalResourceViewResolver() {
   * 
   * final InternalResourceViewResolver bean = new InternalResourceViewResolver(); bean.setViewClass(JstlView.class);
   * bean.setPrefix("/WEB-INF/jsp/"); bean.setSuffix(".jsp"); return bean; }
   */

  @Bean
  public TilesConfigurer tilesConfigurer() {

    final TilesConfigurer tilesConfigurer = new TilesConfigurer();
    tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/tiles.xml" });
    tilesConfigurer.setCheckRefresh(true);
    return tilesConfigurer;
  }

  @Override
  public void configureViewResolvers(final ViewResolverRegistry registry) {

    final TilesViewResolver viewResolver = new TilesViewResolver();
    registry.viewResolver(viewResolver);
  }

}
