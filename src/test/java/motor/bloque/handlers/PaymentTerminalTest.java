package motor.bloque.handlers;

import motor.bloque.entities.PrepayCard;
import motor.bloque.exceptions.CardExpired;
import motor.bloque.exceptions.InsufficientFunds;
import motor.bloque.exceptions.NegativeAmount;
import motor.bloque.exceptions.NoSuchCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PaymentTerminalTest {

    private PrepayCard tarjeta;
    private PrepayCard tarjeta2;

    @BeforeEach
    void setCards() {
        tarjeta = new PrepayCard("Nombre1", "Apellido1", "1234", 700);
        tarjeta2 = new PrepayCard("Nombre2", "Apellido2", "5678", 500);
        Persistence.putCard(tarjeta);
        Persistence.putCard(tarjeta2);
    }


    @Test
    @DisplayName("PaymentTerminal 2 valid movements")
    void test1() throws NoSuchCard, NegativeAmount, InsufficientFunds, ExpiredCard {
        assertTrue(PaymentTerminal.pay(tarjeta.getNumber(), 100, "1234"));
        assertTrue(PaymentTerminal.pay(tarjeta2.getNumber(), 500, "5678"));
    }

    @Test
    @DisplayName("PaymentTerminal NoSuchCard test")
    void test2() {
        assertThrows(NoSuchCard.class, () -> PaymentTerminal.pay("1000", 0, "123"));
    }

    @Test
    @DisplayName("PaymentTerminal InsufficientFunds test")
    void test3() {
        assertThrows(InsufficientFunds.class, () -> PaymentTerminal.pay(tarjeta.getNumber(), 2000, "1234"));
    }

    @Test
    @DisplayName("PaymentTerminal NegativeAmount test")
    void test4() {
        assertThrows(NegativeAmount.class, () -> PaymentTerminal.pay(tarjeta.getNumber(), -2000, "1234"));
    }

    @Test
    @DisplayName("PaymentTerminal Wrong pin")
    void test5() throws NoSuchCard, NegativeAmount, InsufficientFunds, ExpiredCard {
        assertFalse(PaymentTerminal.pay(tarjeta.getNumber(), 100, "0000"));
    }
}
