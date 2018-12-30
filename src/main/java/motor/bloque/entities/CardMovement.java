package motor.bloque.entities;

import motor.bloque.interfaces.Movement;

import java.util.Date;

//TODO: this class is for logging movements for later retrieval.

public class CardMovement implements Movement {
    @Override
    public void setAmount(int amount) {

    }

    @Override
    public void setRemainingBalance(int remainingBalance) {

    }

    @Override
    public void setDate(Date date) {

    }

    @Override
    public int getAmount() {
        return 0;
    }

    @Override
    public int getRemainingBalance() {
        return 0;
    }

    @Override
    public Date getDate() {
        return null;
    }
}
