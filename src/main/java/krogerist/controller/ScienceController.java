package krogerist.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import krogerist.domain.Science;
import krogerist.service.ScienceService;

import java.util.List;
import java.util.Map;

@RestController
public class ScienceController {

    ScienceService scienceService;

    @Autowired
    public ScienceController(ScienceService scienceService) {
        this.scienceService = scienceService;
    }


    @RequestMapping("/science")
    public List<Map<String, Object>> getScience() {

        return scienceService.getScience();
    }


}
