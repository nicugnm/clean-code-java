package victor.training.cleancode;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class UtilsVsVO {
   // Ford Focus:     [2012 ---- 2016]
   // Search:              [2014 ---- 2018]

   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {

      List<CarModel> results = models.stream()
          .filter(criteria::matchesYear)
          .collect(toList());


//      PageObjects
      // SRP->

      System.out.println("More filtering logic");
      return results;
   }


   public void method() {
      System.out.println(new Interval(1, 3).intersects(new Interval(2, 4)));
   }

}
//
//class ResultPart1 { // prima parte din form
//   @ById()
//   private TextField first;
//   private TextField password;
//   private Button button;
//   public void setUsername(String username) {
//      this.username.setValue(username);
//   }
//   public void login() {
//      this.button.click();
//   }
//}
//
//class LoginPage {
//   @ById()
//   private TextField username;
//   private TextField password;
//   private Button button;
//   private PagePart1 pagePart1;
//
//   public void setUsername(String username) {
//      this.username.setValue(username);
//   }
//   public void login() {
//      this.button.click();
//   }
//}
//class ProductPage {
//   List<WebElement> produses;
//
//   public void deleteProduct(Product product) {
//      webElement = findProduct();
//      WebElement.findSubElement("butDelete").click();
//   }
//   private WebElement findProduct(Product product) {
//      // sub /div[id=product]/ ,.. cu id = product.id
//
//   }
//}
//
//
//class PageObject {
//   ReadPageObject read; // selenium inauntru
//   // behavopir
//   click() {
//      read.set
//   }
//
//   public ReadPageObject getRead() {
//      return read;
//   }
//}

@Embeddable
class Interval {
   private int start;
   private int end;

   public Interval(int start, int end) {
      if (start > end) throw new IllegalArgumentException("start larger than end");
      this.start = start;
      this.end = end;
   }

   protected Interval() { // pentry ochii lui hibernate.

   }

   public Interval translate(int delta) {
      return new Interval(start + delta,      end + delta);
   }

   public boolean intersects(Interval other) {
      return start <= other.end && other.start <= end;
   }

   public int getStart() {
      return start;
   }

   public int getEnd() {
      return end;
   }
}

class MateUtil {
   public static void main(String[] args) {
      new CarSearchCriteria("Ford", new Interval(2007, 2012));
   }
}


class CarSearchCriteria {
   private final Interval yearInterval;

   private final String make;

   public CarSearchCriteria(String make, Interval yearInterval) {
      this.make = make;
      this.yearInterval = yearInterval;
   }

   public String getMake() {
      return make;
   }

   public Interval getYearInterval() {
      return yearInterval;
   }

   public boolean matchesYear(CarModel model) {
      return yearInterval.intersects(model.getYearInterval());
   }
}

@Entity
class CarModel {
   private  String make;
   private  String model;
   @Embedded
   private Interval yearInterval;


   private final int startYear;
   private final int endYear;

   public CarModel(String make, String model, int startYear, int endYear) {
      this.make = make;
      this.model = model;
      if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
      this.startYear = startYear;
      this.endYear = endYear;
   }

   public int getEndYear() {
      return endYear;
   }

   public int getStartYear() {
      return startYear;
   }

   public String getMake() {
      return make;
   }

   public String getModel() {
      return model;
   }

   public Interval getYearInterval() {
      return new Interval(getStartYear(), getEndYear());
   }
}