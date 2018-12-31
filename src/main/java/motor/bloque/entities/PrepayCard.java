package motor.bloque.entities;
import motor.bloque.interfaces.*;

//TODO: class to hold all user and card data.

import java.util.List;

public class PrepayCard implements Card {

    public PrepayCard(){};

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public String getHashedPIN() {
        return null;
    }

    @Override
    public String getSalt() {
        return null;
    }

    @Override
    public int getBalance() {
        return 0;
    }

    @Override
    public List<Movement> getMovements() {
        return null;
    }

    @Override
    public boolean changePIN(int oldPIN, int newPIN) {
        return false;
    }

    @Override
    public boolean recharge(int amount) {
        return false;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setNumber(int number) {

    }

    @Override
    public void setHashedPIN(String hashedPIN) {

    }

    @Override
    public void setSalt(String salt) {

    }

    @Override
    public void setBalance(int balance) {

    }

    @Override
    public void setMovements(List<Movement> movements) {

    }


}
