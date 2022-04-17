package ds.com.phoncnic.repository.search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.entity.QMember;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchMemberRepositoryImpl extends QuerydslRepositorySupport implements SearchMemberRepository {

  public SearchMemberRepositoryImpl() {
    super(Member.class);
  }

  @Override
  public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
    log.info("searchPage.....");

    QMember member = QMember.member;

    JPQLQuery<Member> obj = from(member).select(member);

    BooleanBuilder builder = new BooleanBuilder();
    BooleanExpression expression = member.id.contains("@");
    builder.and(expression);

    if (type != null) {
      BooleanBuilder conditionBuilder = new BooleanBuilder();
      switch (type) {
        case "i":
          conditionBuilder.or(member.id.contains(keyword));
          break;
        case "n":
          conditionBuilder.or(member.id.contains(keyword));
          break;
      }
      builder.and(conditionBuilder);
    }
    obj.where(builder);

    Sort sort = pageable.getSort();
    sort.stream().forEach(order-> {
      OrderSpecifier orders = new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC,
          new PathBuilder(Member.class, order.getProperty())
      );
      obj.orderBy(orders);
    });
    
    obj.offset(pageable.getOffset())
    .limit(pageable.getPageSize());

    List<Member> result = obj.fetch();

    long count = obj.fetchCount();

    return new PageImpl<Object[]>(result.stream().map(new Function<Member, Object[]>() {
      @Override
      public Object[] apply(Member t) {
        return new Object[] { t };
      }
    }).collect(Collectors.toList()), pageable, count);
  }

  private List<OrderSpecifier> getOrderSpecifier(Sort sort) {
    List<OrderSpecifier> orders = new ArrayList<>();
    // Sort
    sort.stream().forEach(order -> {
      Order direction = order.isAscending() ? Order.ASC : Order.DESC;
      String prop = order.getProperty();
      PathBuilder orderByExpression = new PathBuilder(Member.class, "member");
      orders.add(new OrderSpecifier(direction, orderByExpression.get(prop)));
    });
    return orders;
  }
}