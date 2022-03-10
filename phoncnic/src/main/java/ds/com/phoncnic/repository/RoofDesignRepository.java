package ds.com.phoncnic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ds.com.phoncnic.entity.RoofDesign;

public interface RoofDesignRepository extends JpaRepository<RoofDesign, Long> {
    
    // @Query("select r, ri from RoofDesign r left outer join RoofDesignInfo ri on r = ri.roofdesign ")
    // List<Object[]> getRoofDesignInfo()

}
