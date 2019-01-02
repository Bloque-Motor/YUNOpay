package motor.bloque.exceptions;

public class NoSuchCard extends Exception {

    public final String invalidCard;
    private static final String MESSAGE = "The card number does not match any card in the system";

    public NoSuchCard(String invalidCard){
        this.invalidCard = invalidCard;
    }

    public String getInvalidCard(){
        return invalidCard;
    }

    @Override
    public String getMessage(){
        return MESSAGE;
    }

}
