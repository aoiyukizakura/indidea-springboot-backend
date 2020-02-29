package com.mirai.indidea;

import com.mirai.indidea.dto.ProjectDto.UpdateProjectDto;
import com.mirai.indidea.entity.Project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.lang.reflect.Field;

@EnableJpaAuditing
@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
