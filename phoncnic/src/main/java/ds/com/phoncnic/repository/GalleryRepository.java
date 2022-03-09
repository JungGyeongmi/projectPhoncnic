package ds.com.phoncnic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    @Query("select g from Gallery g where gno = :gno")
    Gallery getGalleryByGno(Long gno);

    // photoList
    @Query("select g from Gallery g where imagetype = false")
    Page<Gallery> getPhotoPage(Pageable pageable);

    //paintingList
    @Query("select g from Gallery g where imagetype = true")
    Page<Gallery> getPaintingPage(Pageable pageable);

}
