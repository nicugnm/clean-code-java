package victor.training.cleancode;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ManyParamsVO {
   public static void main(String[] args) {
      new ManyParamsVO().placeOrder(new FullName("John", "Doe"), "St. Albergue", "Paris", 99);
   }

   public void placeOrder(FullName fullName, String city, String streetName, Integer streetNumber) {
      if (fullName.getFirstName() == null || fullName.getLastName() == null) throw new IllegalArgumentException();

      System.out.println("Some Logic");
      System.out.println("Shipping to " + city + " on St. " + streetName + " " + streetNumber);

   }
}

class AnotherClass {
   public void otherMethod(FullName fullName, int x) {
      if (fullName.getFirstName() == null || fullName.getLastName() == null) throw new IllegalArgumentException();

      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
   }
}

//@Entity
//@Data
class Person {
//   @Id
//   @GeneratedValue
//   @Setter(AccessLevel.NONE)
   private Long id;
   private FullName fullName;
   private String phone;

   public Person() {
   }

   public void marryHim(Person husband) {
      this.fullName = fullName.withLastName(husband.fullName.getLastName());
   }

}
