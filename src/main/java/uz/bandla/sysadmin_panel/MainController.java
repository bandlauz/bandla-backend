package uz.bandla.sysadmin_panel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("SysAdminPanelMainController")
@RequestMapping("/api/sysadmin-panel/main")
public class MainController {

    @GetMapping
    private ResponseEntity<String> main() {
        return ResponseEntity.ok("Main controller of system admin panel");
    }
}
