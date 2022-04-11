package victor.training.pure.basic;

import com.google.common.collect.ImmutableList;
import lombok.Value;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

//@Slf4j
public class ImmutableBasic {
//   private static final Logger log = LoggerFactory.getLogger(ImmutableBasic.class);
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = ImmutableList.copyOf(Stream.of(1, 2, 3, 4, 5).collect(toList()));

      Immutable immutable = new Immutable(2, numbers, new Other(13));


      System.out.println(immutable);
//numbers.clear();
      immutable = undevaAdanc(immutable);

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);
   }

   private static Immutable undevaAdanc(Immutable immutable) {
//      immutable.getNumbers().add(-1);
      return immutable.withX(immutable.getX() + 1);
   }

}

  // love 💘  obiect imutabil nativ in limbaj in Java 17
//record Immutable(int x, ImmutableList<Integer> numbers, Other other) {}

//@Data // hate
@Value // love 💘
class Immutable {
//   @With
   int x;
   ImmutableList<Integer> numbers; // NU MERGE CU HIBERNATE. merge cu Cassandra, Mongo, jooq pe relational DB
   Other other;

   public Immutable withX(int newX) {
      return new Immutable(newX, numbers, other);
   }
}
//class Immutable { // deeply immutable
//   private final int x;
//   private final ImmutableList<Integer> numbers; // NU MERGE CU HIBERNATE. merge cu Cassandra, Mongo, jooq pe relational DB
//   private final Other other;
//
//   Immutable(int x, ImmutableList<Integer> numbers, Other other) {
//      this.x = x;
//      this.numbers = numbers;
//      this.other = other;
//   }
//   public String toString() {
//      return String.format("Immutable{x=%d, numbers=%s, other=%s}", getX(), getNumbers(), getOther());
//   }
//   public int getX() {
//      return x;
//   }
//
//   public ImmutableList<Integer> getNumbers() {
//      return numbers;
//   }
//
//   //   public List<Integer> getNumbers() {
//////      return new ArrayList<>(numbers);// Solutia 1: waste of mem + surpriza la caller ca-l lasa sa puna add
////      return Collections.unmodifiableList(numbers); // Solutia 2: merge pe Hibernate entity; nu face malloc (), crapa cu exceptie
////   }
//   public Other getOther() {
//      return other;
//   }
//}

class Other {
   private final int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }

//   public void setA(int a) {
//      this.a = a;
//   }
}
