package motor.bloque.handlers;


import motor.bloque.entities.CardMovement;
import motor.bloque.exceptions.CardExpired;
import motor.bloque.exceptions.InsufficientFunds;
import motor.bloque.exceptions.NegativeAmount;
import motor.bloque.exceptions.NoSuchCard;
import motor.bloque.interfaces.Card;
import motor.bloque.interfaces.Movement;


class PaymentTerminal {
    static boolean makeMovement(String cardNumber, int amount, String pin) throws NoSuchCard, NegativeAmount, InsufficientFunds, CardExpired {
        Card cardAux = Persistence.getCard(cardNumber);
        Movement movementAux = new CardMovement(amount);

        return cardAux.addMovement(pin, movementAux);
    }//Movement

}//Class

