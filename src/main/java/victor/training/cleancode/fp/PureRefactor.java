package victor.training.cleancode.fp;

import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import victor.training.cleancode.fp.support.*;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
class PureRefactor {
  private final CustomerRepo customerRepo;
  private final ThirdPartyPrices thirdPartyPrices;
  private final CouponRepo couponRepo;
  private final ProductRepo productRepo;

  // TODO extract a pure function with as much logic possible
  public Map<Long, Double> computePrices(long customerId, List<Long> productIds, Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId);
    List<Product> products = productRepo.findAllById(productIds);

    Map<Long, Double> resolvedPrices = fetchPrices(internalPrices, products);

    PriceCalculationResult result = calculate(products, resolvedPrices, customer.getCoupons());

    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.finalPrices();
  }

  private Map<Long, Double> fetchPrices(Map<Long, Double> internalPrices, List<Product> products) {
    Map<Long, Double> resolvedPrices = new HashMap<>();
    for (Product product : products) {
      Double price = internalPrices.get(product.getId());
      if (price == null) {
        price = thirdPartyPrices.fetchPrice(product.getId());
      }
      resolvedPrices.put(product.getId(), price);
    }
    return resolvedPrices;
  }

  @VisibleForTesting // doar @Test au voie sa o cheme din afara clasei asteia. Alte apeluri crapa Sonaru/Checkstyle/IntelliJ
  // sau, mai ortodox, o muti in alta clasa (dar uneori nu merita)
  static PriceCalculationResult calculate(List<Product> products, Map<Long, Double> resolvedPrices, List<Coupon> coupons) {
    List<Coupon> usedCoupons = new ArrayList<>();
    Map<Long, Double> finalPrices = new HashMap<>();
    for (Product product : products) {
      Double price = resolvedPrices.get(product.getId());
      for (Coupon coupon : coupons) {
        if (coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon)) {
          price = coupon.apply(product, price);
          usedCoupons.add(coupon);
        }
      }
      finalPrices.put(product.getId(), price);
    }
    return new PriceCalculationResult(usedCoupons, finalPrices);
  }

  private record PriceCalculationResult(List<Coupon> usedCoupons, Map<Long, Double> finalPrices) {
  }
}

