package ds.com.phoncnic.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
<<<<<<< HEAD
=======

    @Query("select g from Gallery g where gno = :gno")
    Gallery getGalleryByGno(Long gno);
>>>>>>> gallery_ng

    @Query("select g from Gallery g where gno = :gno")
    Gallery getGalleryByGno(Long gno);

    /* photoList (Paging처리) */
    @Query("select g from Gallery g where imagetype = false")
    Page<Gallery> getPhotoPage(Pageable pageable);
    
    //paintingList
    @Query("select g from Gallery g where imagetype = true")
    Page<Gallery> getPaintingPage(Pageable pageable);
    
    // galleryList
    @Query("select g from Gallery g where imagetype = :type")
    List<Gallery> getGalleryList(Boolean type);

}
