package ds.com.phoncnic.repository.search;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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
    JPQLQuery<Member> jpqlQuery = from(member);
    JPQLQuery<Member> obj = jpqlQuery.select(member);

    BooleanBuilder builder = new BooleanBuilder();

    if (type != null) {
      BooleanExpression expression = null;
      switch (type) {
        case "i":
          expression = member.id.contains(keyword);
          break;
        case "n":
          expression = member.nickname.contains(keyword);
          break;
      }
      builder.and(expression);
      
    } else {
      BooleanExpression expression = member.id.contains(keyword);
      builder.and(expression);
    }

    Sort sort = pageable.getSort();
    

    obj.orderBy(member.id.desc());
    obj.where(builder);
    obj.offset(pageable.getOffset());
    obj.limit(pageable.getPageSize());

    List<Member> result = obj.fetch();
    log.info(result);
    long count = obj.fetchCount();

    return new PageImpl<Object[]>(result.stream().map(new Function<Member, Object[]>() {

      @Override
      public Object[] apply(Member t) {
        return new Object[] { t };
      }

    }).collect(Collectors.toList()), pageable, count);
  }

}