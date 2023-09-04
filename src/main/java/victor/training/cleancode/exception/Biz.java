package victor.training.cleancode.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.Order;

import java.io.IOException;
import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class Biz {
   private final Config config;

   public void applyDiscount(Order order, Customer customer) {
      System.out.println("START");
      try {
         if (order.getOfferDate().before(config.getLastPromoDate())) { // TODO inside

            System.out.println("APPLYING DISCOUNT");
            Integer points = customer.getMemberCard().getFidelityPoints();
            order.setPrice(order.getPrice() * (100 - 2 * points) / 100);
         } else {
            System.out.println("NO DISCOUNT");
         }
         // cod ce trebuie sarit
      } catch (CustomRuntimeEx1 e) {
         throw new RuntimeException(e);
      }
   }
}

