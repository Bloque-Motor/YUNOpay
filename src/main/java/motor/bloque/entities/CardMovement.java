package motor.bloque.entities;

import motor.bloque.interfaces.Movement;

import java.time.LocalDateTime;

//TODO: this class is for logging movements for later retrieval.

public class CardMovement implements Movement {

    public CardMovement(){};

    @Override
    public void setAmount(int amount) {

    }

    @Override
    public void setRemainingBalance(int remainingBalance) {

    }

    @Override
    public void setDate(LocalDateTime date) {

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
    public LocalDateTime getDate() {
        return null;
    }
}
