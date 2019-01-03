package motor.bloque.handlers;

import motor.bloque.entities.PrepayCard;
import motor.bloque.exceptions.NoSuchCard;
import motor.bloque.exceptions.NegativeAmount;
import motor.bloque.exceptions.InsufficientFunds;
import motor.bloque.handlers.Credentials;
import motor.bloque.handlers.PaymentTerminal;
import motor.bloque.handlers.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import motor.bloque.handlers.Persistence;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;


 public class PaymentTerminalTest {


   @Test
           void test1() throws NoSuchCard ,NegativeAmount, InsufficientFunds{
       PaymentTerminal pay = new PaymentTerminal();
       PrepayCard tarjeta = new PrepayCard("Nombre1", "Apellido1", "1234", 700);
       PrepayCard tarjeta2 = new PrepayCard("Nombre2", "Apellido2", "5678", 500);
       Persistence.putCard(tarjeta);
       Persistence.putCard(tarjeta2);
       boolean resultado1 = pay.makeMovement(tarjeta.getNumber(), 100, "1234");
       boolean resultado2 = pay.makeMovement(tarjeta2.getNumber(),500,"5678");
       assertEquals(true,resultado1);
       assertEquals(true, resultado2);
   }
     @Test
     void test2() throws NoSuchCard ,NegativeAmount, InsufficientFunds{
         PaymentTerminal pay = new PaymentTerminal();
         assertThrows(NoSuchCard.class, () -> pay.makeMovement("1000",0,"123"));
     }
     @Test
     void test3() throws NoSuchCard ,NegativeAmount, InsufficientFunds{
         PaymentTerminal pay = new PaymentTerminal();
         PrepayCard tarjeta = new PrepayCard("Nombre1", "Apellido1", "1234", 700);
         Persistence.putCard(tarjeta);
         assertThrows(InsufficientFunds.class, () -> pay.makeMovement(tarjeta.getNumber(),2000,"1234"));
     }
     @Test
     void test4() throws NoSuchCard ,NegativeAmount, InsufficientFunds{
         PaymentTerminal pay = new PaymentTerminal();
         PrepayCard tarjeta = new PrepayCard("Nombre1", "Apellido1", "1234", 700);
         Persistence.putCard(tarjeta);
         assertThrows(NegativeAmount.class, () -> pay.makeMovement(tarjeta.getNumber(),-2000,"1234"));
     }
     @Test
     void test5()  throws NoSuchCard ,NegativeAmount, InsufficientFunds{
         PaymentTerminal pay = new PaymentTerminal();
         PrepayCard tarjeta = new PrepayCard("Nombre1", "Apellido1", "1234", 700);
         Persistence.putCard(tarjeta);
         boolean resultado1 = pay.makeMovement(tarjeta.getNumber(), 100, "0000");
        assertEquals(false,resultado1);
     }
}
