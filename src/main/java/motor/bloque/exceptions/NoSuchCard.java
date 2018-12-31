package motor.bloque.exceptions;

public class NoSuchCard extends Exception {

    public int invalidCard;
    public String message = "The card number does not match any card in the system";

    public NoSuchCard(int invalidCard){
        this.invalidCard = invalidCard;
    }

    public int getInvalidCard(){
        return invalidCard;
    }

    public String getMessage(){
        return message;
    }

}
