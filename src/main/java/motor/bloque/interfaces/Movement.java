package motor.bloque.interfaces;

import java.util.Date;

public interface Movement {

    void setAmount(int amount);
    void setRemainingBalance(int remainingBalance);
    void setDate(Date date);
    int getAmount();
    int getRemainingBalance();
    Date getDate();
}
