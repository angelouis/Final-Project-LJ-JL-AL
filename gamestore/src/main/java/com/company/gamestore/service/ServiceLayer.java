package com.company.gamestore.service;

import com.company.gamestore.exceptions.NotFoundException;
import com.company.gamestore.models.Console;
import com.company.gamestore.repositories.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceLayer {

    private ConsoleRepository consoleRepository;

    @Autowired
    public ServiceLayer(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }

    // Console API

    public Console saveConsole (Console console) {
        return  consoleRepository.save(console);

    }

    public Console findConsole(int id) {
       Optional<Console> console = consoleRepository.findById(id);

        if(console.isPresent()) {
            return console.get();
        }
        else{
            return null;
        }

    }

    public List<Console> findAllConsoles() {

        return consoleRepository.findAll();
    }

    public List<Console> findConsolesByManufacturer(String manufacturer) {

       // return consoleRepository.findByManufacturer(manufacturer);

        List<Console> console = consoleRepository.findByManufacturer(manufacturer);

        if(!console.isEmpty()) {
            return console;
        }
        else{
            return null;
        }

    }

    public void updateConsole(Console console) {

        consoleRepository.save(console);
    }

    public void removeConsole(int id) {

        consoleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

        consoleRepository.deleteById(id);


    }






}
