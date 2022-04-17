package ds.com.phoncnic.dto.pageDTO;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class SearchMemberPageRequestDTO {
  private int page;
  private int size;
  private String type;
  private String keyword;
  private String sort;

  public SearchMemberPageRequestDTO() {
      this.page = 1;
      this.size = 5;
  }

  public Pageable getPageable(Sort sort) {
      return PageRequest.of(page - 1, size, sort);
  }
}
