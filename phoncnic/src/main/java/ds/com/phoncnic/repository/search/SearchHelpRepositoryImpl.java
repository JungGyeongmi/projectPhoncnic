package ds.com.phoncnic.repository.search;

import java.util.List;
import java.util.function.Consumer;
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
  public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
    log.info("searchPage.....");
   
    QHelp help = QHelp.help;
    JPQLQuery<Help> jpqlQuery = from(help);
    JPQLQuery<Help> obj = jpqlQuery.select(help);

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
            conditionBuilder.or(help.writeremail.contains(keyword));
            break;
          case "c":
            conditionBuilder.or(help.content.contains(keyword));
            break;
        }
      }
      builder.and(conditionBuilder);
    }
    obj.where(builder);

    Sort sort = pageable.getSort();
    sort.stream().forEach(new Consumer<Sort.Order>() {
      @Override
      public void accept(Sort.Order order) {
        Order direction = order.isAscending() ? Order.ASC : Order.DESC;
        String prop = order.getProperty();

        log.info("prop>>" + prop);

        PathBuilder orderByExpression = new PathBuilder<>(
            Help.class, "help");
          obj.orderBy(new OrderSpecifier<>(direction, orderByExpression.get(prop)));
      }
    });

    obj.groupBy(help);
    obj.offset(pageable.getOffset());
    obj.limit(pageable.getPageSize());

    List<Help> result = obj.fetch();
    log.info(result);
    long count = obj.fetchCount();
    log.info("COUNT: " + count);

    return new PageImpl<Object[]>(result.stream().map(new Function<Help, Object[]>() {
      @Override
      public Object[] apply(Help t) {
        return new Object[] { t };
      }
    }).collect(Collectors.toList()), pageable, count);
  }
}