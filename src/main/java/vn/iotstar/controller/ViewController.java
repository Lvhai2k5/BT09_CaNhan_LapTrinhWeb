package vn.iotstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "index"; // sẽ tìm /WEB-INF/views/index.jsp
    }

    @GetMapping("/users")
    public String users() {
        return "users"; // /WEB-INF/views/users.jsp
    }

    @GetMapping("/categories")
    public String categories() {
        return "categories"; // /WEB-INF/views/categories.jsp
    }

    @GetMapping("/products")
    public String products() {
        return "products"; // /WEB-INF/views/products.jsp
    }
}
