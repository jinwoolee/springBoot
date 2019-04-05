package kr.or.ddit;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@SpringBootApplication
public class BootApplication implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>  {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		Set<ErrorPage> errorPageSet = new HashSet<ErrorPage>();
		errorPageSet.add(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404/404.html"));
		errorPageSet.add(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500/500.html"));
		factory.setErrorPages(errorPageSet);
	}
	
	@Bean
	public View jsonView() {
		return new MappingJackson2JsonView();
	}

	@Bean
	public TilesViewResolver tilesViewResolver() {
		TilesViewResolver viewResolver = new TilesViewResolver();
		viewResolver.setViewClass(TilesView.class);
		// viewResolver.setOrder(1);
		return viewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("classpath:/kr/or/ddit/config/tiles/tiles.xml");
		return tilesConfigurer;
	}



}
