package se331.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// ✅ เพิ่มตรงนี้
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // อนุญาตทุก endpoint
						.allowedOrigins("http://localhost:5173") // อนุญาตเฉพาะจาก Frontend ของเรา
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // อนุญาตทุก Method
						.allowedHeaders("*") // อนุญาตทุก Header
						.exposedHeaders("x-total-count")  // 👈 ต้องเพิ่มบรรทัดนี้
						.allowCredentials(true); // อนุญาตการส่งข้อมูลรับรอง (ถ้ามี)
			}
		};
	}
}
