package pl.akademiakodu.book_store.view;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {


   // @RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String home(Model model) {
        String greet = "Hello World!";
        model.addAttribute("greet", greet);
        return "index";
    }


}
