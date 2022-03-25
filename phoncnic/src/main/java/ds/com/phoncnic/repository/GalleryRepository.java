package ds.com.phoncnic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ds.com.phoncnic.entity.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    @Query("select g from Gallery g where gno = :gno")
    Gallery getGalleryByGno(Long gno);

    // @Query("select g, m, e,"+
    //         "count(case when e.emojiInfo.emojitype = '1' then 1 end) as 1, " +
    //         "count(case when e.emojiInfo.emojitype = '2' then 1 end) as 2, " +
    //         "count(case when e.emojiInfo.emojitype = '3' then 1 end) as 3, " +
    //         "count(case when e.emojiInfo.emojitype = '4' then 1 end) as 4 " +
    //         "from Gallery g left join g.artistid m  "+
    //         "left outer join Emoji e on e.gallery.gno = g "+
    //         "where g.gno= :gno group by e " )
    // Object getGallery(@Param("gno") Long gno);

    // @Query("select g, m, e, e.emojiInfo.emojitype, count(e.emojiInfo.emojitype) "+
    //         "from Gallery g left join g.artistid m  "+
    //         "left outer join Emoji e on e.gallery.gno = g "+
    //         "where g.gno= :gno " )
    //    Object getGalleryJOin(@Param("gno") Long gno);



    /* photoList (Paging처리) */
    @Query("select g from Gallery g where imagetype = false")
    Page<Gallery> getPhotoPage(Pageable pageable);
    
    //paintingList
    @Query("select g from Gallery g where imagetype = true")
    Page<Gallery> getPaintingPage(Pageable pageable);
    
    // galleryList
    @Query("select g from Gallery g where imagetype = :type")
    List<Gallery> getGalleryList(Boolean type);

    @Modifying
    @Query("delete from Gallery g where g.artistid.id=:id")
    void deleteByMemberId(String id);

    @Query("select g from Gallery g where g.artistid.id=:id")
    List<Gallery> findByMemberId(String id);


}
