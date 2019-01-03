package motor.bloque.handlers;

//TODO: This class will handle payments
import motor.bloque.entities.CardMovement;
import motor.bloque.exceptions.InsufficientFunds;
import motor.bloque.exceptions.NegativeAmount;
import motor.bloque.exceptions.NoSuchCard;
import motor.bloque.interfaces.Card;
import motor.bloque.interfaces.Movement;


public class PaymentTerminal {
    private static Card cardAux;
    private static Movement movementAux;

    public static boolean makeMovement (String cardNumber,int amount,String pin) throws NoSuchCard ,NegativeAmount, InsufficientFunds {
            cardAux = Persistence.getCard(cardNumber);
            movementAux = new CardMovement(amount);
             boolean res = cardAux.addMovement(pin, movementAux);

    return  res;
    }//Movement

}//Class

