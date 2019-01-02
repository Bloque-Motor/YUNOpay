package motor.bloque.entities;

import motor.bloque.handlers.Credentials;
import motor.bloque.handlers.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PrepayCardTest {

    private PrepayCard testCard;

    @BeforeEach
    void setPrepayCard(){
        testCard = new PrepayCard("TestName", "Test Surname", "1234", 0);
        Persistence.putCard(testCard);
    }

    @Test
    @DisplayName("PrepayCard getName")
    void getName() {
        assertEquals("TestName" + "Test Surname", testCard.getName());
    }

    @Test
    void getNumber() {
    }

    @Test
    void getHashedPIN() {
    }

    @Test
    void getSalt() {
    }

    @Test
    @DisplayName("PrepayCard get initial balance")
    void getBalance() {
        assertEquals(0, testCard.getBalance());
    }

   /* @Test
    @DisplayName("PrepayCard getBalance after recharge")
    void getBalance1() {
        testCard.recharge(10, "1234");
        assertEquals(10, testCard.getBalance());
    }*/

    @Test
    void getMovements() {
    }

    @Test
    void changePIN() {
    }

    @Test
    void recharge() {
    }

    @Test
    void addMovement() {
    }

    @Test
    void setName() {
    }

    @Test
    void setNumber() {
    }

    @Test
    void setHashedPIN() {
    }

    @Test
    void setSalt() {
    }

    @Test
    void setBalance() {
    }

    @Test
    void setMovements() {
    }

}