package com.bam.fuavserver.repo;

import com.bam.fuavserver.model.entity.CrashInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrashInfoRepository extends JpaRepository<CrashInfo, Long> {
}
