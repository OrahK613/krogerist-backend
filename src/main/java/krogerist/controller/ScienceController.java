package krogerist.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import krogerist.domain.Science;
import krogerist.service.ScienceService;

@RestController
public class ScienceController {

    ScienceService scienceService;

    @Autowired
    public ScienceController(ScienceService scienceService) {
        this.scienceService = scienceService;
    }


    @RequestMapping("/science")
    public Science getScience() {

        return scienceService.getScience();
    }


}
