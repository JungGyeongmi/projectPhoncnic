package ds.com.phoncnic.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ds.com.phoncnic.entity.Dyning;

public interface SearchDyningRepository {
    Dyning search1();
    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}