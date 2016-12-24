package ua.nure.easygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;


@SpringBootApplication
public class EasyGoServerApplication {

	public static void main(String[] args) {
		System.out.println(new File("./").getAbsolutePath());
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		SpringApplication.run(EasyGoServerApplication.class, args);
	}
}
