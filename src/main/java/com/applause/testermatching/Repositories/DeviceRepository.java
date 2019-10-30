package com.applause.testermatching.Repositories;

import com.applause.testermatching.Model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface DeviceRepository extends JpaRepository<Device, Long>, QueryByExampleExecutor<Device> {
}
