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

import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.QDyning;
import ds.com.phoncnic.entity.QFollow;
import lombok.extern.log4j.Log4j2;
@Log4j2
public class SearchDyningRepositoryImpl extends QuerydslRepositorySupport
    implements SearchDyningRepository {


    /*
      JpaRepository의 부족한 부분은 바로 각 항목에 대한 
      max, min 값을 구하는 Predicate query와 
      다양한 update,delete를 하는 query들을 만들어주는 것이 불가능
      그래서 QuerydslRepositorySupport을 사용
    */

  public SearchDyningRepositoryImpl() {
    super(Dyning.class);
  }

  @Override
  public Dyning search1() {
    log.info("serch1...........");
    // 1. 사용하고자 하는 Q도메인을 선언
    QDyning dyning = QDyning.dyning;
    QFollow follow = QFollow.follow;

    // 2. JPQLQuery을 이용해서 서로 연관(조인) 시킴
    JPQLQuery<Dyning> jpqlQuery = from(dyning);

    // 3. 쿼리 대상(내용)을 정한다. Tuple은 Object[]과 같은 기능
    JPQLQuery<Tuple> tuple = jpqlQuery.select(
        dyning, follow.count());
    tuple.groupBy(dyning);

    log.info("----------------------------");
    log.info(tuple);
    log.info("----------------------------");

    List<Dyning> result = jpqlQuery.fetch();
    log.info(result);
    return null;
  }

  @Override
  public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
    log.info("searchPage.....");
    // 1. 사용하고자 하는 Q도메인을 선언(동적쿼리 호출을 위해 선언)
    QDyning dyning = QDyning.dyning;
    QFollow follow = QFollow.follow;


    // 2. JPQLQuery을 이용해서 서로 연관(조인) 시킴
    JPQLQuery<Dyning> jpqlQuery = from(dyning);

    // 3. 쿼리 대상(내용)을 정한다. Tuple은 Object[]과 같은 기능
    JPQLQuery<Tuple> tuple = jpqlQuery.select(dyning, follow.count());

    // 4. 검색 조건을 위한 객체 선언
    BooleanBuilder builder = new BooleanBuilder();
    BooleanExpression expression = dyning.dno.gt(0L);
    builder.and(expression);

    if (type != null) {
      String[] typeArr = type.split("");
      BooleanBuilder conditionBuilder = new BooleanBuilder();
      for (String t : typeArr) {
        switch (t) {
          case "n":
            conditionBuilder.or(dyning.dyningname.contains(keyword));
            break;
          case "h":
            conditionBuilder.or(dyning.hashtag.contains(keyword));
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
          Dyning.class,"dyning");
        tuple.orderBy(new OrderSpecifier<>(direction, orderByExpression.get(prop)));
      }
    });

    tuple.groupBy(dyning); //board의 목록에 따른 그룹
    tuple.offset(pageable.getOffset());
    tuple.limit(pageable.getPageSize());

    List<Tuple> result = tuple.fetch();
    log.info(result);
    long count = tuple.fetchCount();
    log.info("COUNT: "+count);

    return new PageImpl<Object[]>(result.stream().map(t->t.toArray()).collect(Collectors.toList()),pageable,count);
  }
}