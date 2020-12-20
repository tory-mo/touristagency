package p.zolotaya.touristagency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@Controller
public class ToursofferApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToursofferApplication.class, args);
	}

	/*@RequestMapping(value = "/")
	public String index() {
		return "index";
	}*/
}
