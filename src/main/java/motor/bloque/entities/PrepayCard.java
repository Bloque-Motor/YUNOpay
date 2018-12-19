package motor.bloque.entities;
import motor.bloque.interfaces.*;

//TODO: class to hold all user and card data.

import java.util.List;

public class PrepayCard implements Card {
    @Override
    public void setName(String name) {

    }

    @Override
    public void setNumber(int number) {

    }

    @Override
    public boolean setPIN() {
        return false;
    }

    @Override
    public boolean recharge(int amount) {
        return false;
    }

    @Override
    public int checkBalance() {
        return 0;
    }

    @Override
    public List<Movement> checkMovements() {
        return null;
    }
}
