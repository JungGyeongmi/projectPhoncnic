package ds.com.phoncnic.repository.search;

import java.util.List;
import java.util.function.Consumer;
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

import ds.com.phoncnic.entity.Help;
import ds.com.phoncnic.entity.QHelp;
import ds.com.phoncnic.entity.QMember;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class SearchHelpRepositoryImpl 
    extends QuerydslRepositorySupport
    implements SearchHelpRepository {

  public SearchHelpRepositoryImpl() {
    super(Help.class);
  }

  @Override
  public Help search1() {
    log.info("serch1...........");
    QHelp help = QHelp.help;
    QMember member = QMember.member;
    JPQLQuery<Help> jpqlQuery = from(help);
    jpqlQuery.leftJoin(member).on(help.writer.eq(member));
    JPQLQuery<Tuple> tuple = jpqlQuery.select(
      help, member.id);
    tuple.groupBy(help);

    log.info("----------------------------");
    log.info(tuple);
    log.info("----------------------------");

    List<Help> result = jpqlQuery.fetch();
    log.info(result);
    return null;
  }

  @Override
  public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
    log.info("searchPage.....");
    QHelp help = QHelp.help;
    QMember member = QMember.member;

    JPQLQuery<Help> jpqlQuery = from(help);
    jpqlQuery.leftJoin(member).on(help.writer.eq(member));

    JPQLQuery<Tuple> tuple = jpqlQuery.select(
      help, member);

    BooleanBuilder builder = new BooleanBuilder();
    BooleanExpression expression = help.qno.gt(0L);
    builder.and(expression);

    if (type != null) {
      String[] typeArr = type.split("");
      BooleanBuilder conditionBuilder = new BooleanBuilder();
      for (String t : typeArr) {
        switch (t) {
          case "t":
            conditionBuilder.or(help.title.contains(keyword));
            break;
          case "w":
            conditionBuilder.or(member.id.contains(keyword));
            break;
          case "c":
            conditionBuilder.or(help.content.contains(keyword));
            break;
        }
      }
      builder.and(conditionBuilder);
    }
    tuple.where(builder);
    
    Sort sort = pageable.getSort();
    sort.stream().forEach(new Consumer<Sort.Order>() {
      @Override
      public void accept(Sort.Order order) {
        Order direction = order.isAscending()?Order.ASC:Order.DESC;
        String prop = order.getProperty();
        log.info("prop>>"+prop);
        PathBuilder orderByExpression = new PathBuilder<>(
          Help.class,"help");
        tuple.orderBy(new OrderSpecifier<>(direction, orderByExpression.get(prop)));
      }
    });

    tuple.groupBy(help);
    tuple.offset(pageable.getOffset());
    tuple.limit(pageable.getPageSize());

    List<Tuple> result = tuple.fetch();
    log.info(result);
    long count = tuple.fetchCount();
    log.info("COUNT: "+count);

    return new PageImpl<Object[]>(result.stream().map(t->t.toArray()).collect(Collectors.toList()),pageable,count);
  }
}