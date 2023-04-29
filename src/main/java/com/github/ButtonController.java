package com.github;

import com.github.services.Delete;
import com.github.services.Insert;
import com.github.services.Populate;
import com.github.services.Select;
import com.github.services.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ButtonController {

    @Autowired
    private Delete deleteService;

    @Autowired
    private Insert insertService;

    @Autowired
    private Populate populateService;

    @Autowired
    private Select selectService;

    @Autowired
    private Update updateService;

    @GetMapping("/delete")
    public void delete() {
        deleteService.delete();
    }

    @GetMapping("/populate")
    public void populate() {
        populateService.populate();
    }

    @GetMapping("/insert")
    public void insert() {
        insertService.insert();
    }

    @GetMapping("/select")
    public void select() {
        selectService.select();
    }

    @GetMapping("/update")
    public void update() {
        updateService.update();
    }

}
