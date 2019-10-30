package com.applause.testermatching.Repositories;

import com.applause.testermatching.Model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BugRepository extends JpaRepository<Bug,Long>, JpaSpecificationExecutor<Bug> {


}
