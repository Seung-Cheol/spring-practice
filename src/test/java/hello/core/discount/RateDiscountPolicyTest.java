package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {
  RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

  @Test
  @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
  void vip_o() {
    Member member = new Member(1L, "memberVIP", Grade.VIP);
    int discount = discountPolicy.discount(member, 10000);
    Assertions.assertEquals(discount,1000);
  }

  @Test
  void vip_x() {
    Member member = new Member(1L, "memberVIP", Grade.BASIC);
    int discount = discountPolicy.discount(member, 10000);
    Assertions.assertEquals(discount,1000);
  }

}