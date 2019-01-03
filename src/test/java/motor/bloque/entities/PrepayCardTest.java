package motor.bloque.entities;

import motor.bloque.exceptions.InsufficientFunds;
import motor.bloque.exceptions.NegativeAmount;
import motor.bloque.handlers.Persistence;
import motor.bloque.interfaces.Movement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrepayCardTest {

    private PrepayCard testCard;
    private PrepayCard emptyCard;

    @BeforeEach
    void setPrepayCard() {
        testCard = new PrepayCard("TestName", "Test Surname", "1234", 0);
        emptyCard = new PrepayCard();
        Persistence.putCard(testCard);
    }

    @Test
    @DisplayName("PrepayCard getName")
    void getName() {
        assertEquals("TestName" + "Test Surname", testCard.getName());
    }

    @Test
    @DisplayName("PrepayCard change pin invalid card")
    void invalidCard() {
        assertFalse(emptyCard.changePIN("1234", "4321"));
    }

    @Test
    @DisplayName("PrepayCard get initial balance")
    void getBalance() {
        assertEquals(0, testCard.getBalance());
    }

    @Test
    @DisplayName("PrepayCard getBalance after recharge")
    void getBalance1() throws NegativeAmount {
        testCard.recharge(10, "1234");
        assertEquals(10, testCard.getBalance());
    }

    @Test
    @DisplayName("PrepayCard recharge with wrong pin")
    void getBalance2() throws NegativeAmount {
        assertFalse(testCard.recharge(10, "123"));
        assertEquals(0, testCard.getBalance());
    }

    @Test
    @DisplayName("PrepayCard getMovements")
    void getMovements() throws InsufficientFunds, NegativeAmount {
        Movement movement = new CardMovement();
        movement.setAmount(10);
        testCard.setBalance(20);
        testCard.addMovement("1234", movement);
        System.out.println(testCard.getBalance());

        assertEquals(movement, testCard.getMovements().get(0));
    }


    @Test
    @DisplayName("PrepayCard change pin")
    void changePIN() throws NegativeAmount {
        assertTrue(testCard.changePIN("1234", "4321"));
        assertTrue(testCard.recharge(10, "4321"));
        assertEquals(10, testCard.getBalance());

    }

    @Test
    @DisplayName("PrepayCard change pin (Wrong pin number)")
    void changePIN2() {
        assertFalse(testCard.changePIN("123", "4321"));
    }

    @Test
    @DisplayName("PrepayCard recharge positive amount")
    void recharge() throws NegativeAmount {
        assertTrue(testCard.recharge(25, "1234"));
        assertEquals(25, testCard.getBalance());
    }

    @Test
    @DisplayName("PrepayCard recharge negative amount")
    void recharge2() {
        assertThrows(NegativeAmount.class, () -> testCard.recharge(-10, "1234"));
    }

    @Test
    @DisplayName("PrepayCard recharge invalid card")
    void recharge3() throws NegativeAmount {
        assertFalse(emptyCard.recharge(10, "1111"));
    }

    @Test
    @DisplayName("PrepayCard recharge wrong pin number")
    void recharge4() throws NegativeAmount {
        assertFalse(testCard.recharge(25, "123"));
        assertEquals(0, testCard.getBalance());
    }

    @Test
    @DisplayName("PrepayCard addMovement")
    void addMovement() throws InsufficientFunds, NegativeAmount {
        Movement movement = new CardMovement();
        movement.setAmount(10);
        testCard.setBalance(50);
        testCard.addMovement("1234", movement);
        assertEquals(40, testCard.getBalance());
    }

    @Test
    @DisplayName("PrepayCard addMovement with insufficient funds")
    void addMovement0() {
        Movement movement = new CardMovement();
        movement.setAmount(10);

        assertThrows(InsufficientFunds.class, () -> testCard.addMovement("1234", movement));
    }

    @Test
    @DisplayName("PrepayCard addMovement invalid card")
    void addMovement1() throws InsufficientFunds, NegativeAmount {
        Movement movement = new CardMovement();
        movement.setAmount(5);
        assertFalse(emptyCard.addMovement("1111", movement));
    }

    @Test
    @DisplayName("PrepayCard addMovement negative amount")
    void addMovement2() {
        Movement movement = new CardMovement();
        movement.setAmount(-10);
        assertThrows(NegativeAmount.class, () -> testCard.addMovement("1234", movement));
    }

    @Test
    @DisplayName("PrepayCard set a new name")
    void setNewName() {

        testCard.setName("Francisco");
        assertEquals("Francisco", testCard.getName());
    }

    @Test
    @DisplayName("PrepayCard set a new number")
    void setNewNumber() {

        testCard.setNumber("190319961994");
        assertEquals("190319961994", testCard.getNumber());

    }

    @Test
    @DisplayName("PrepayCard set a new list of movements")
    void setNewMovements() {
        Movement movement = new CardMovement();
        movement.setAmount(10);
        Movement movement1 = new CardMovement();
        movement1.setAmount(5);
        List<Movement> movements = new ArrayList<>();
        movements.add(movement);
        movements.add(movement1);
        testCard.setMovements(movements);
        assertEquals(movements, testCard.getMovements());
    }

    @Test
    @DisplayName("PrepayCard get expiration date")
    void getExpirationDate() {
        LocalDateTime dateTime = LocalDateTime.now();
        testCard.setExpirationDate(dateTime);
        assertEquals(dateTime, testCard.getExpirationDate());
    }
}