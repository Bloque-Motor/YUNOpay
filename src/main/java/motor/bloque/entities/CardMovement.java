package motor.bloque.entities;

import motor.bloque.interfaces.Movement;

import java.time.LocalDateTime;


public class CardMovement implements Movement {

    private int amount;
    private int remainingBalance;
    private LocalDateTime date;

    public CardMovement(int amount){
        this.amount = amount;
        date = LocalDateTime.now();
    }

    public CardMovement(){}

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void setRemainingBalance(int remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    @Override
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public int getRemainingBalance() {
        return remainingBalance;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }
}
