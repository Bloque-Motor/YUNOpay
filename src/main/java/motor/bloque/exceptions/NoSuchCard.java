package motor.bloque.exceptions;

public class NoSuchCard extends Exception {

    public String invalidCard;
    public String message = "The card number does not match any card in the system";

    public NoSuchCard(String invalidCard){
        this.invalidCard = invalidCard;
    }

    public String getInvalidCard(){
        return invalidCard;
    }

    public String getMessage(){
        return message;
    }

}
