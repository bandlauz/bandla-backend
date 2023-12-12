package uz.bandla.business_panel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("BusinessPanelMainController")
@RequestMapping("/api/business-panel/main")
public class MainController {

    @GetMapping
    private ResponseEntity<String> main() {
        return ResponseEntity.ok("Main controller of business panel");
    }
}
