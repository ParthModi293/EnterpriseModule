package com.example.enterprisemodule.repo;

import com.example.enterprisemodule.entity.SubModulePriceMapping;
import com.example.enterprisemodule.entity.SubModulePriceMappingId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubModulePriceMappingRepository extends JpaRepository<SubModulePriceMapping, SubModulePriceMappingId> {

    List<SubModulePriceMapping> findById_SubModuleId(Integer subModuleId);

    List<SubModulePriceMapping> findById_SubModuleIdAndId_TimePeriod(Integer idSubModuleId, String idTimePeriod);
}