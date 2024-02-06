package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanFindTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);


  @Test
  @DisplayName("타입으로 조회 시 같은 타입이 둘 있으면 중복 오류 발생")
  void findBeanByDuplicate() {
    //MemberRepository memberRepository = ac.getBean(MemberRepository.class);
    Assertions.assertThrows(NoUniqueBeanDefinitionException.class, ()-> ac.getBean(MemberRepository.class));
  }

  @Test
  void findAllBeanByType() {
    Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
    for(String key : beansOfType.keySet()) {
      System.out.println("key -> " +key + "value -> " +beansOfType.get(key));
    }
  }
  @Configuration
  static class SameBeanConfig {
    @Bean
    public MemberRepository memberRepository1() {
      return new MemoryMemberRepository();
    }
    @Bean
    public MemberRepository memberRepository2() {
      return new MemoryMemberRepository();
    }
  }

}
