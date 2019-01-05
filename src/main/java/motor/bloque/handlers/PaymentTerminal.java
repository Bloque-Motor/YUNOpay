package motor.bloque.handlers;


import motor.bloque.entities.CardMovement;
import motor.bloque.exceptions.*;
import motor.bloque.interfaces.Card;


class PaymentTerminal {
    static boolean pay(String cardNumber, int amount, String pin) throws NoSuchCard, NegativeAmount, InsufficientFunds, ExpiredCard, IncorrectPin {
        Card card = Persistence.getCard(cardNumber, pin);
        return card.addMovement(new CardMovement(amount));
    }
}

