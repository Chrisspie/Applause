package com.applause.testermatching.Repositories;

import com.applause.testermatching.Model.Tester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface TesterRepository extends JpaRepository<Tester, Long>{

    @Query(nativeQuery = true, value = "SELECT u.tester_Id, u.first_name from Tester u INNER JOIN TESTER_DEVICE td ON u.tester_Id=td.tester_Id INNER JOIN DEVICE d ON td.device_id=d.device_id")
    List<Tester> findXYZ();
}
