package com.company.gamestore.controllers;

import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodels.ConsoleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ConsoleController {

    @Autowired
    ServiceLayer serviceLayer;

    // Create
    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsoleViewModel addConsole(@RequestBody @Valid ConsoleViewModel consoleViewModel) {
        return serviceLayer.saveConsole(consoleViewModel);
    }

    //Read (By Id)
    @GetMapping("/consoles/{id}")
    public ConsoleViewModel getConsoleById(@PathVariable int id) {
        return serviceLayer.findConsole(id);
    }

    //Read (All)
    @GetMapping("/consoles")
    public List<ConsoleViewModel> getConsoles() {
        return serviceLayer.findAllConsoles();
    }

    //Update
    //In Insomnia, the console_id you want to change needs to be sent in request body
    @PutMapping("/consoles")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody @Valid ConsoleViewModel consoleViewModel) {
        serviceLayer.updateConsole(consoleViewModel);
    }

    //Delete
    @DeleteMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        serviceLayer.removeConsole(id);
    }

    //Get Console By Manufacturer
    @GetMapping("/consoles/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getConsolesByManufacturer(@PathVariable String manufacturer) {
        return serviceLayer.findConsolesByManufacturer(manufacturer);
    }


}
