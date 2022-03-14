package videostore.horror;

//@Service
//@RequiredArgsConstructor
//class SomeService {
//   private final SomeDep dep;
//
//
//}

public final class Rental {
   private final Movie movie;
   private final int daysRented;

   public Rental(Movie movie, int daysRented) {
      this.movie = movie;
      this.daysRented = daysRented;
   }

//   @Autowired
//   messageSender

   public double price() {
//      priceComputationCounter ++;
      // INSERT POST SEND MESSAGE < NEVER HAPPEN, as they require Spring dep
      // SELECT GET < NEVER HAPPEN, as they require Spring dep
      // change FIELD < NO: I am in an immutable object. :)
      // time-based stuff >>> RISKY (16 years : never saw time-based logic deep inside a data object)
      double price = 0;
      switch (movie.priceCode()) {
         case REGULAR:
            price += 2;
            if (daysRented > 2) {
               price += (daysRented - 2) * 1.5;
            }
            break;
         case NEW_RELEASE:
            price += daysRented * 3;
            break;
         case CHILDREN:
            price += 1.5;
            if (daysRented > 3) {
               price += (daysRented - 3) * 1.5;
            }
            break;
      }
      return price;
   }

   public int calculateFrequentRenterPoints() {
      if (movie.isNewRelease() && daysRented >= 2) {
         return 2;
      } else {
         return 1;
      }
   }

   public Movie movie() {
      return movie;
   }
}
