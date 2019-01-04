package motor.bloque.exceptions;

public class CardExpired extends Exception {

    @Override
    public String getMessage(){
        return "The card has expired";
    }
}
