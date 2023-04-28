package victor.training.cleancode.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Controller {
   public static void main(String[] args) {
       SpringApplication.run(Controller.class, args);
   }

   @Autowired
   private Biz biz;

   @GetMapping
   public ResponseEntity<Void> webEndpoint() {

      try {
         biz.applyDiscount(null, null);
      } catch (Exception e) {
         return ResponseEntity.badRequest().build();
      }
      return ResponseEntity.ok().build();
   }
}
