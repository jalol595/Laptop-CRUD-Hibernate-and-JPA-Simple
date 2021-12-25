package uz.pdp.crudbylaptop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.crudbylaptop.model.Laptop;
import uz.pdp.crudbylaptop.repository.LaptopRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    @Autowired
    LaptopRepository laptopRepository;

    @RequestMapping(value = "/laptop", method = RequestMethod.GET)
    public List<Laptop> getLaptop() {
        List<Laptop> laptops = laptopRepository.findAll();
        return laptops;
    }

    @RequestMapping(value = "/laptop", method = RequestMethod.POST)
    public String saveLaptop(@RequestBody Laptop laptop) {
        laptopRepository.save(laptop);
        return "added";
    }

    @RequestMapping(value = "/laptop/{id}", method = RequestMethod.GET)
    public Laptop getByIdLaptop(@PathVariable Integer id) {
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        if (optionalLaptop.isPresent()) {
            Laptop laptop = optionalLaptop.get();
            return laptop;
        }
        return new Laptop();
    }

    @RequestMapping(value = "/laptop/{id}", method = RequestMethod.DELETE)
    public String deleteLaptop(@PathVariable Integer id) {
        laptopRepository.deleteById(id);
        return "deleted";
    }

    @RequestMapping(value = "/laptop/{id}", method = RequestMethod.PUT)
    public String editLaptop(@PathVariable Integer id, @RequestBody Laptop laptop) {
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        if (optionalLaptop.isPresent()) {
            Laptop editLaptop = optionalLaptop.get();
            editLaptop.setName(laptop.getName());
            editLaptop.setBrandName(laptop.getBrandName());
            editLaptop.setModel(laptop.getModel());
            editLaptop.setStorage(laptop.getStorage());
            editLaptop.setMacAddress(laptop.getMacAddress());
            laptopRepository.save(editLaptop);
            return "editing";
        }

        return "not found";
    }


}
