package com.company.gamestore.service;

import com.company.gamestore.models.Console;
import com.company.gamestore.repositories.ConsoleRepository;
import com.company.gamestore.viewmodels.ConsoleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ConsoleViewModel saveConsole (ConsoleViewModel viewModel) {
        Console c = new Console();
        c.setManufacturer(viewModel.getManufacturer());
        c.setModel(viewModel.getModel());
        c.setPrice(viewModel.getPrice());
        c.setProcessor(viewModel.getProcessor());
        c.setQuantity(viewModel.getQuantity());
        c.setMemory_amount(viewModel.getMemory_amount());

        c = consoleRepository.save(c);
        viewModel.setId(c.getId());

        return viewModel;


    }

    public ConsoleViewModel findConsole(int id){
        Optional<Console> console = consoleRepository.findById(id);

        return console.isPresent() ? buildConsoleViewModel(console.get()) : null;
    }

    public List<ConsoleViewModel> findAllConsoles() {
        List<Console> consoles = consoleRepository.findAll();

        List<ConsoleViewModel> cvmList = new ArrayList<>();

        consoles.stream()
                .forEach(c -> {
                    cvmList.add(buildConsoleViewModel(c));
                });

        return cvmList;
    }

    public List<ConsoleViewModel> findConsolesByManufacturer(String manufacturer) {
        List<Console> consoles = consoleRepository.findByManufacturer(manufacturer);

        List<ConsoleViewModel> cvmList = new ArrayList<>();

        consoles.stream()
                .forEach(c -> {
                    cvmList.add(buildConsoleViewModel(c));
                });

        return cvmList;


    }

    public void updateConsole(ConsoleViewModel viewModel) {
        Console console = new Console();
        console.setId(viewModel.getId());
        console.setManufacturer(viewModel.getManufacturer());
        console.setModel(viewModel.getModel());
        console.setPrice(viewModel.getPrice());
        console.setProcessor(viewModel.getProcessor());
        console.setQuantity(viewModel.getQuantity());
        console.setMemory_amount(viewModel.getMemory_amount());

        consoleRepository.save(console);
    }

    public void removeConsole(int id){
        consoleRepository.deleteById(id);

    }

    private ConsoleViewModel buildConsoleViewModel(Console console) {
        ConsoleViewModel cvm = new ConsoleViewModel();
        cvm.setId(console.getId());
        cvm.setModel(console.getModel());
        cvm.setManufacturer(console.getManufacturer());
        cvm.setProcessor(console.getProcessor());
        cvm.setPrice(console.getPrice());
        cvm.setQuantity(console.getQuantity());
        cvm.setMemory_amount(console.getMemory_amount());

        return cvm;
    }




}
