package com.bam.fuavserver.repo;

import com.bam.fuavserver.model.entity.GPSSaati;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GPSClockRepository extends JpaRepository<GPSSaati, Long> {
}
