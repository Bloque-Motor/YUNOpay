package motor.bloque.exceptions;

public class InsufficientFunds extends Exception {

    private final int shortBy;


    public InsufficientFunds(int shortBy){
        this.shortBy = shortBy;
    }

    @Override
    public String getMessage(){
        return "Insufficient funds. You need an extra " + shortBy + " euros to complete this transaction.";
    }

}
