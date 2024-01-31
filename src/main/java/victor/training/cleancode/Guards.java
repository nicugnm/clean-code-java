package victor.training.cleancode;

import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

public class Guards {

  public static final int DEAD_PAY_AMOUNT = 1;

  public int getPayAmount(Marine marine, BonusPackage bonusPackage) {
    if (marine == null || (bonusPackage.getValue() <= 100 && bonusPackage.getValue() >= 10)) {
      throw new IllegalArgumentException("Not applicable!");
    }
    if (isDead(marine)) { // guard
      return DEAD_PAY_AMOUNT; // early return/throw
    }
    if (marine.isRetired()) {
      return retiredAmount();
    }
    if (marine.getYearsService() == null) {
      throw new IllegalArgumentException("Any marine should have the years of service set");
    }
    int result = marine.getYearsService() * 100 + bonusPackage.getValue();
    if (!marine.getAwards().isEmpty()) {
      result += 1000;
    }
    if (marine.getAwards().size() >= 3) {
      result += 2000;
    }
    // HEAVY core logic here, business-rules ...
    return result;
  }

  private boolean isDead(Marine marine) {
    return false;
  }

  private int retiredAmount() {
    return 2;
  }

}

@Value
class Marine {
  boolean dead;
  boolean retired;
  Integer yearsService;
  List<Award> awards = new ArrayList<>();
//Optional<String> awa= null;
}

@Data
class BonusPackage {
  int value;
}

class Award {

}