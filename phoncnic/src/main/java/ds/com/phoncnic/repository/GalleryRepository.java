package ds.com.phoncnic.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.repository.search.SearchGalleryRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long>, SearchGalleryRepository {

    /* gno 값으로 특정 gallery entity 추출 */
    @Query("select g from Gallery g where gno = :gno")
    Gallery getGalleryByGno(Long gno);

    /* gallerylist paging처리 */
    @Query("select g from Gallery g")
    Page<Gallery> getGalleryPage(Pageable pageable);

    /* photoList (Paging처리) */
    @Query("select g from Gallery g where imagetype = false")
    Page<Gallery> getPhotoPage(Pageable pageable);
    
    //paintingList
    @Query("select g from Gallery g where imagetype = true")
    Page<Gallery> getPaintingPage(Pageable pageable);
    
    // galleryList
    @Query("select g from Gallery g where imagetype = :type order by g.gno desc")
    List<Gallery> getGalleryList(Boolean type);

    // deledt gallery
    @Modifying
    @Query("delete from Gallery g where g.artistid.id=:id")
    void deleteByMemberId(String id);

    // getGalleryList by artist id - list
    @Query("select g from Gallery g where g.artistid.id=:id")
    List<Gallery> findByMemberId(String id);

}
