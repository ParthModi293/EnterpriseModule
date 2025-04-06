package com.example.enterprisemodule.repo;

import com.example.enterprisemodule.entity.EnterpriseModuleMapping;
import com.example.enterprisemodule.entity.EnterpriseModuleMappingId;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EnterpriseModuleMappingRepository extends JpaRepository<EnterpriseModuleMapping, EnterpriseModuleMappingId> {
    List<EnterpriseModuleMapping> findById_EnterpriseId(@NotNull UUID idEnterpriseId);
}