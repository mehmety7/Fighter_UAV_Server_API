package com.bam.fuavserver.repo;

import com.bam.fuavserver.model.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login, Long> {

    Login getLoginByKadi(String kadi);

    @Query(nativeQuery = true, value = "UPDATE LOGIN SET IN_OUT=FALSE")
    void setFalseAllLogin();

    @Query(nativeQuery = true, value = "UPDATE LOGIN l SET IN_OUT=TRUE WHERE l.id = :id")
    void setTrueLoginById(@Param("id") Long id);

}
