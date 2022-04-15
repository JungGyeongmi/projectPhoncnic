package ds.com.phoncnic.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SearchHelpRepository {
    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}