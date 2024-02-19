package greenstitch.sushil.assignment.shell;

import greenstitch.sushil.assignment.service.ShellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellCommand {

    @Autowired
    ShellService shellService;
    @ShellMethod("Create a Parking lot")
    public String create_parking_lot(int numcars) {
        String result = shellService.createparkinglot(numcars);
        return result;
    }

    @ShellMethod("Park a Car")
    public String park(String regnum, String colour) {
        String result = shellService.parkcar(regnum, colour);
        return result;
    }

    @ShellMethod("Delete a car")
    public String leave(int num) {
        String result = shellService.deleteparkedcar(num);
        return result;
    }

    @ShellMethod("Availabe parked cars")
    public String status() {
        String result = shellService.availablecars();
        return result;
    }
    @ShellMethod("Show all cars with a particular colour")
    public String registration_numbers_for_cars_with_colour(String rang) {
        String result = shellService.showcarsofcolours(rang);
        return result;
    }

    @ShellMethod("Exit from the shell")
    public void exit() {
        System.exit(0);
    }






}
