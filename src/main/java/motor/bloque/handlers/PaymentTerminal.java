package motor.bloque.handlers;

//TODO: This class will handle payments
import motor.bloque.entities.CardMovement;
import motor.bloque.exceptions.InsufficientFunds;
import motor.bloque.exceptions.NegativeAmount;
import motor.bloque.exceptions.NoSuchCard;
import motor.bloque.interfaces.Card;
import motor.bloque.interfaces.Movement;


public class PaymentTerminal {
    private Card cardAux;
    private Movement movementAux;

    public void makeMovement (String cardNumber,int amount,String pin) {
        try {
            cardAux = Persistence.getCard(cardNumber);
            movementAux = new CardMovement(amount);
        } catch (NoSuchCard e) {
        }
        try {
            cardAux.addMovement(pin, movementAux);
        } catch (InsufficientFunds e) {
        } catch (NegativeAmount e) {
        }

    }//Movement

}//Class

