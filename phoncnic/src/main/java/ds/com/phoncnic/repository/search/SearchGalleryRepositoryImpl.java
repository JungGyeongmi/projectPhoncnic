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

import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.QGallery;
import ds.com.phoncnic.entity.QMember;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchGalleryRepositoryImpl extends QuerydslRepositorySupport implements SearchGalleryRepository {

  public SearchGalleryRepositoryImpl() {
    super(Gallery.class);
  }

  @Override
  public Gallery search1() {
    log.info("serch1...........");
    QGallery gallery = QGallery.gallery;
    QMember member = QMember.member;

    JPQLQuery<Gallery> jpqlQuery = from(gallery);
    jpqlQuery.leftJoin(member).on(gallery.artistid.eq(member));

    JPQLQuery<Tuple> tuple = jpqlQuery.select( gallery, member.id);
    tuple.groupBy(gallery);

    log.info("----------------------------");
    log.info(tuple);
    log.info("----------------------------");

    List<Gallery> result = jpqlQuery.fetch();
    log.info(result);
    return null;
  }

  @Override
  public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
    log.info("searchPage.....");
    QGallery gallery = QGallery.gallery;
    QMember member = QMember.member;

    JPQLQuery<Gallery> jpqlQuery = from(gallery);
    jpqlQuery.leftJoin(member).on(gallery.artistid.eq(member));

    JPQLQuery<Tuple> tuple = jpqlQuery.select(gallery, member);

    BooleanBuilder builder = new BooleanBuilder();
    BooleanExpression expression = gallery.gno.gt(0L);
    builder.and(expression);

    if (type != null) {
      String[] typeArr = type.split("");
      BooleanBuilder conditionBuilder = new BooleanBuilder();
      for (String t : typeArr) {
        switch (t) {
          case "t":
            conditionBuilder.or(gallery.title.contains(keyword));
            break;
          case "w":
            conditionBuilder.or(member.nickname.contains(keyword));
            break;
          case "c":
            conditionBuilder.or(gallery.content.contains(keyword));
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
        Order direction = order.isAscending() ? Order.ASC : Order.DESC;
        String prop = order.getProperty();        
        PathBuilder orderByExpression = new PathBuilder<>(Gallery.class, "gallery");
        tuple.orderBy(new OrderSpecifier<>(direction, orderByExpression.get(prop)));
      }
    });

    tuple.groupBy(gallery);
    tuple.offset(pageable.getOffset());
    tuple.limit(pageable.getPageSize());

    List<Tuple> result = tuple.fetch();
    log.info(result);
    long count = tuple.fetchCount();
    log.info("COUNT: " + count);

    return new PageImpl<Object[]>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
  }
}