package ds.com.phoncnic.repository.search;

import java.util.List;
import java.util.stream.Collectors;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
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
    QApplicationForm apply = QApplicationForm.applicationForm;

    JPQLQuery<Member> jpqlQuery = from(member);
    jpqlQuery.leftJoin(apply).on(apply.member.eq(member));

    JPQLQuery<Tuple> tuple = jpqlQuery.select(member, apply);
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
          conditionBuilder.or(member.nickname.contains(keyword));
          break;
      }
      builder.and(conditionBuilder);
    }
    tuple.where(builder);

    Sort sort = pageable.getSort();
    sort.stream().forEach(order-> {
      OrderSpecifier orders = new OrderSpecifier(
        order.isAscending() ? Order.ASC : Order.DESC,
        new PathBuilder(Member.class, order.getProperty())
       );
      tuple.orderBy(orders);
    });
    
    tuple.groupBy(member);
    tuple.offset(pageable.getOffset());
    tuple.limit(pageable.getPageSize());

    List<Tuple> result = tuple.fetch();
    log.info("TUPLE: " + result);
    long totalCount = tuple.fetchCount();
    log.info("COUNT: " + totalCount);

    return new PageImpl<Object[]>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, totalCount);
  
  }
}