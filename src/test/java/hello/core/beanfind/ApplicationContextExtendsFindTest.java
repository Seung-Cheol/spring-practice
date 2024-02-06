package hello.core.beanfind;

import hello.core.beanfind.ApplicationContextSameBeanFindTest.SameBeanConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextExtendsFindTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);


  @Test
  @DisplayName("부모타입으로 조회 시 자식이 둘 이상 있으면 빈 이름을 지정하면 된다")
  void findBeanByParentTypeDuplicate() {
    DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
    Assertions.assertInstanceOf(RateDiscountPolicy.class,rateDiscountPolicy);
  }

  @Test
  @DisplayName("부모타입으로 조회 시 자식이 둘 이상 있으면 빈 이름을 지정하면 된다")
  void findBeanBySubType() {
    DiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
    Assertions.assertInstanceOf(RateDiscountPolicy.class,rateDiscountPolicy);
  }

  @Test
  @DisplayName("모두 조회")
  void findAllBean() {
    Map<String,DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
    Assertions.assertEquals(beansOfType.size(),2);
  }

  @Configuration
  static class TestConfig {
    @Bean
    public DiscountPolicy rateDiscountPolicy() {
      return new RateDiscountPolicy();
    }

    @Bean
    public DiscountPolicy fixDiscountPolicy() {
      return new FixDiscountPolicy();
    }
  }

}
