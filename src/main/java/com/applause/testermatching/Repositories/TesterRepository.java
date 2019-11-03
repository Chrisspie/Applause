package com.applause.testermatching.Repositories;

import com.applause.testermatching.Model.Tester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TesterRepository extends JpaRepository<Tester, Long> {

    @Query("select t.firstName, t.lastName, count(b.bugId) as bugs from Tester t" +
            " join TesterDeviceMapping td on td.testerId=t.testerId" +
            " join Device d on d.deviceId= td.deviceId" +
            " join Bug b on b.deviceId=d.deviceId and b.testerId=t.testerId" +
            " where" +
            "((:countries) is null OR t.country in (:countries))" +
            " and ((:devices) is null OR d.description in (:devices))" +
            " group by t.testerId, t.firstName, t.lastName" +
            " order by bugs desc")
    List<Object[]> findTestersByCountryAndDeviceDescriptionOrderByBugs(@Param("countries") List<String> countries, @Param("devices") List<String> devicesDescriptions);
}




