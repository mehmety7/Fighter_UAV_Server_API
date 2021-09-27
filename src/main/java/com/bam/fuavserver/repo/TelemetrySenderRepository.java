package com.bam.fuavserver.repo;

import com.bam.fuavserver.model.entity.TelemetrySender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelemetrySenderRepository extends JpaRepository<TelemetrySender, Long> {
}
