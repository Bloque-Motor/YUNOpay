package motor.bloque.interfaces;

import java.time.LocalDateTime;

public interface Movement {

    void setAmount(int amount);
    void setRemainingBalance(int remainingBalance);
    void setDate(LocalDateTime date);
    int getAmount();
    int getRemainingBalance();
    LocalDateTime getDate();
}
