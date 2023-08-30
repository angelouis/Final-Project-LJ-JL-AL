package com.company.gamestore.services;

import com.company.gamestore.exceptions.NotFoundException;
import com.company.gamestore.exceptions.TShirtUpdateException;
import com.company.gamestore.exceptions.TShirtViewModelBuildingException;
import com.company.gamestore.models.TShirt;
import com.company.gamestore.repositories.TShirtRepository;
import com.company.gamestore.viewmodels.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {
    private final TShirtRepository tShirtRepository;

    @Autowired
    public ServiceLayer(TShirtRepository tShirtRepository) {
        this.tShirtRepository = tShirtRepository;
    }

    /**
     * Saves the t-shirt with the specific instance variables
     * @param viewModel - uses the viewmodel t-shirt to save the t-shirt in the t-shirt repo
     * @return returns the t-shirt that was saved
     */
    @Transactional
    public TShirtViewModel saveTShirt(TShirtViewModel viewModel) throws NotFoundException {
        if (viewModel == null) {
            throw new NotFoundException("Failed to save T-Shirt");
        }

        TShirt tShirt = new TShirt();
        tShirt.setQuantity(viewModel.getQuantity());
        tShirt.setSize(viewModel.getSize());
        tShirt.setDescription(viewModel.getDescription());
        tShirt.setColor(viewModel.getColor());
        tShirt.setPrice(viewModel.getPrice());
        tShirt = tShirtRepository.save(tShirt);

        viewModel.settShirtId(tShirt.gettShirtId());
        return viewModel;
    }

    /**
     * Finds the t-shirt within the t-shirt repo
     *
     * @param id - uses the id to find the t-shirt
     * @return Returns the t-shirt as a t-shirt viewmodel if it is present or else it gives null
     */
    @Transactional
    public TShirtViewModel findTShirt(int id) {
        Optional<TShirt> tShirt = tShirtRepository.findById(id);

        if (tShirt == null || tShirt.isEmpty() || !tShirt.isPresent()) {
            throw new NotFoundException("T-shirt with ID " + id + " not found");
        }

        return buildTShirtViewModel(tShirt.get());
    }

    /**
     * Builds the t-shirt viewModel with the specific instances variables
     *
     * @param tShirt uses the t-shirt as a way to create a viewmodel
     * @return returns the filled out t-shirt viewmodel
     */
    public TShirtViewModel buildTShirtViewModel(TShirt tShirt) {
        try {
            TShirtViewModel tvm = new TShirtViewModel();
            tvm.settShirtId(tShirt.gettShirtId());
            tvm.setColor(tShirt.getColor());
            tvm.setDescription(tShirt.getDescription());
            tvm.setPrice(tShirt.getPrice());
            tvm.setQuantity(tShirt.getQuantity());
            tvm.setSize(tShirt.getSize());

            return tvm;
        } catch (Exception e) {
            throw new TShirtViewModelBuildingException("Error Building TShirtViewModel");
        }
    }

    /**
     * Get all the t-shirt from the repo and then converts the t-shirts to viewmodel t-shirts
     *
     * @return Returns the list of viewmodel t-shirts
     */
    @Transactional
    public List<TShirtViewModel> findAllTShirt() throws TShirtViewModelBuildingException {
        List<TShirt> tShirtList = tShirtRepository.findAll();
        List<TShirtViewModel> tvmList = new ArrayList<>();
        try {
            for (TShirt tShirt : tShirtList) {
                TShirtViewModel tvm = buildTShirtViewModel(tShirt);
                tvmList.add(tvm);
            }
        } catch (Exception e) {
            throw new TShirtViewModelBuildingException("Error building T-ShirtViewModel");
        }

        return tvmList;
    }

    /**
     * Updates the t-shirt by creating a new t-shirt model with the updated information
     * @param viewModel - this is the t-shirt viewmoddel we will use to create the new t-shirt value with the updated info
     */
    @Transactional
    public void updateTShirt(TShirtViewModel viewModel) {

        //if viewmodel is inside of the repo
        //do optional tshirt = tshirtrepo.findbyid
        // save the tshirt
        // else throw error
        try {
            TShirt tShirt = new TShirt();
            tShirt.settShirtId(viewModel.gettShirtId());
            tShirt.setPrice(viewModel.getPrice());
            tShirt.setColor(viewModel.getColor());
            tShirt.setDescription(viewModel.getDescription());
            tShirt.setQuantity(viewModel.getQuantity());
            tShirt.setSize(viewModel.getSize());

            tShirtRepository.save(tShirt);
        } catch (Exception e) {
            throw new TShirtUpdateException("Error Updating T-Shirt");
        }
    }

    /**
     * Removes the t-shirt from the repository by id
     * @param id - Uses the id to delete the t-shirt from the repo
     */
//    @Transactional
    public void removeTShirt(int id) {
        tShirtRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

        tShirtRepository.deleteById(id);

    }

    /**
     * Gets all the t-shirts from the repo by the color and then currents the t-shirts into viewmodel t-shirts
     * @param color - Uses the color as a parameter to chose which t-shirts to place in the list
     * @return - Returns a list of viewmodel t-shirts with the specific color
     */
    @Transactional
    public List<TShirtViewModel> findTShirtByColor(String color) {
        List<TShirt> tShirtList = tShirtRepository.findByColor(color);

        List<TShirtViewModel> tvmList = new ArrayList<>();

        try {
            for (TShirt tShirt : tShirtList) {
                TShirtViewModel tvm = buildTShirtViewModel(tShirt);
                tvmList.add(tvm);
            }
        } catch (Exception e) {
            throw new TShirtViewModelBuildingException("Error building TShirt");
        }

        return tvmList;
    }

    /**
     * Gets all the t-shirts from the repo by the size and then currents the t-shirts into viewmodel t-shirts
     * @param size - Uses the size as a parameter to chose which t-shirts to place in the list
     * @return - Returns a list of viewmodel t-shirts with the specific size
     */
    @Transactional
    public List<TShirtViewModel> findTShirtBySize(String size) {
        List<TShirt> tShirtList = tShirtRepository.findBySize(size);

        List<TShirtViewModel> tvmList = new ArrayList<>();

        try {
            for (TShirt tShirt : tShirtList) {
                TShirtViewModel tvm = buildTShirtViewModel(tShirt);
                tvmList.add(tvm);
            }
        } catch (Exception e) {
            throw new TShirtViewModelBuildingException("Error building TShirt");
        }

        return tvmList;
    }
}
