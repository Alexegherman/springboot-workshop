package eu.accesa.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ceva")
public class TrainingController {

    @RequestMapping(path = "/training", method= RequestMethod.GET)
    public String publicTraining() {
        return "training";
    }

    @GetMapping("/hi")
    public String hiWithParam(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hi";
    }
}
