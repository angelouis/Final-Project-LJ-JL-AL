package com.company.gamestore.controllers;

import com.company.gamestore.exceptions.NotFoundException;
import com.company.gamestore.models.Console;
import com.company.gamestore.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {

    @Autowired
    ServiceLayer serviceLayer;

    // Create
    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody @Valid Console console) {
        return serviceLayer.saveConsole(console);
    }

   // Read (By Id)
    @GetMapping("/consoles/{id}")
    public Console getConsoleById(@PathVariable int id)  throws NotFoundException{
        return Optional
                .ofNullable(serviceLayer.findConsole(id))
                .orElseThrow(() -> new NotFoundException("Requested console was not found! [ id = " + id + "]"));


    }

    //Read (All)
    @GetMapping("/consoles")
    public List<Console> getConsoles() {
        return serviceLayer.findAllConsoles();
    }

    //Update
    //In Insomnia, the console_id you want to change needs to be sent in request body
    @PutMapping("/consoles")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody @Valid Console console) {
        serviceLayer.updateConsole(console);
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
    public List<Console> getConsolesByManufacturer(@PathVariable String manufacturer) throws NotFoundException {
        return Optional
                .ofNullable(serviceLayer.findConsolesByManufacturer(manufacturer))
                .orElseThrow(() -> new NotFoundException("Requested console was not found! [ manufacturer = " + manufacturer + "]"));
    }


}
