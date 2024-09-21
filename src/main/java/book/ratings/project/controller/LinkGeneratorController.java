package book.ratings.project.controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LinkGeneratorController {
    @GetMapping("/generate-api-link")
    public String generateApiLink(HttpServletRequest request){
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        return "API Base URL: http://" + serverName + ":" + serverPort + contextPath + "/api/books";
    }

}
