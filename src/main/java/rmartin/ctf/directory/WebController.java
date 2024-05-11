package rmartin.ctf.directory;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
public class WebController {
    private static final Logger log = Logger.getLogger(WebController.class.getName());

    private final DirectoryService directoryService;

    public WebController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @GetMapping("/")
    ModelAndView index(){
        var mv = new ModelAndView("index");
        mv.addObject("users", directoryService.search("*"));
        mv.addObject("lastQuery", "*");

        return mv;
    }

    @GetMapping("/error")
    ModelAndView error(ModelAndView mv){
        mv.setViewName("error");
        return mv;
    }

    @PostMapping("/search")
    ModelAndView doSearch(@RequestParam String name, HttpServletRequest request){
        log.info(String.format("Executing search %s for client %s", name, request.getRemoteAddr()));

        var mv = new ModelAndView("index");
        mv.addObject("users", directoryService.search(name));
        mv.addObject("lastQuery", name);
        return mv;
    }
}
