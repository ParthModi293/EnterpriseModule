package com.example.enterprisemodule.service;


import com.example.enterprisemodule.dto.ModuleResponse;
import com.example.enterprisemodule.dto.SubModuleResponse;
import com.example.enterprisemodule.entity.EnterpriseModuleMapping;
import com.example.enterprisemodule.entity.ModuleMaster;
import com.example.enterprisemodule.entity.SubModuleMaster;
import com.example.enterprisemodule.entity.SubModulePriceMapping;
import com.example.enterprisemodule.repo.EnterpriseModuleMappingRepository;
import com.example.enterprisemodule.repo.ModuleMasterRepository;
import com.example.enterprisemodule.repo.SubModuleMasterRepository;
import com.example.enterprisemodule.repo.SubModulePriceMappingRepository;

import java.math.BigDecimal;
import java.util.*;

public class ModuleService {

    private final EnterpriseModuleMappingRepository enterpriseModuleMappingRepository;
    private final ModuleMasterRepository moduleMasterRepository;
    private final SubModuleMasterRepository subModuleMasterRepository;
    private final SubModulePriceMappingRepository subModulePriceMappingRepository;

    public ModuleService(EnterpriseModuleMappingRepository enterpriseModuleMappingRepository,
                         ModuleMasterRepository moduleMasterRepository,
                         SubModuleMasterRepository subModuleMasterRepository,
                         SubModulePriceMappingRepository subModulePriceMappingRepository) {
        this.enterpriseModuleMappingRepository = enterpriseModuleMappingRepository;
        this.moduleMasterRepository = moduleMasterRepository;
        this.subModuleMasterRepository = subModuleMasterRepository;
        this.subModulePriceMappingRepository = subModulePriceMappingRepository;
    }

    public Map<String, List<ModuleResponse>> getModulesAndSubModules(UUID enterpriseId, String timePeriod) {
        // Fetch all records for the enterpriseId
        List<EnterpriseModuleMapping> mappings = enterpriseModuleMappingRepository.findById_EnterpriseId((enterpriseId));

        // Group mappings by moduleMasterId
        Map<Integer, ModuleResponse> moduleMap = new HashMap<>();

        // Iterate over mappings to populate the module and submodule details
        for (EnterpriseModuleMapping mapping : mappings) {

            Integer moduleId = mapping.getId().getModuleMasterId();
            Integer subModuleId = mapping.getId().getSubModuleId();


            // Get or create the module response
            ModuleResponse moduleResponse = moduleMap.computeIfAbsent(moduleId, k -> {
                // Fetch module details from the module_master table
                ModuleMaster moduleMaster = moduleMasterRepository.findById(moduleId)
                        .orElseThrow(() -> new RuntimeException("Module not found for ID " + moduleId));
                ModuleResponse response = new ModuleResponse();
                response.setModuleMasterId(moduleId);
                response.setModuleName(moduleMaster.getName());
                response.setDescription(moduleMaster.getDescription());
                response.setIsDefault(moduleMaster.isDefault());
                response.setSubModules(new ArrayList<>());
                response.setTotalPrice(BigDecimal.ZERO);  // Initialize total price
                return response;
            });

            // Add submodule to the module
            SubModuleMaster subModuleMaster = subModuleMasterRepository.findById(subModuleId)
                    .orElseThrow(() -> new RuntimeException("SubModule not found for ID " + subModuleId));

            SubModuleResponse subModuleResponse = new SubModuleResponse();
            subModuleResponse.setSubModuleId(subModuleId);
            subModuleResponse.setSubModuleName(subModuleMaster.getName());
            subModuleResponse.setDescription(subModuleMaster.getDescription());

            moduleResponse.getSubModules().add(subModuleResponse);

            // Fetch the price for this submodule (we always assume it is for 1 month)
//            List<SubModulePriceMapping> priceMappings = subModulePriceMappingRepository.findById_SubModuleId(subModuleId);

            // Fetch only the price for 'monthly'
            List<SubModulePriceMapping> priceMappings =
                    subModulePriceMappingRepository.findById_SubModuleIdAndId_TimePeriod(subModuleId ,"monthly");


            // Iterate through the price mappings and always use the price for 1 month
            for (SubModulePriceMapping priceMapping : priceMappings) {
                // Directly use the price for 1 month (no need to filter for totalMonths = 1)
                BigDecimal price = priceMapping.getPrice();

                // Add the price of this submodule to the module's total price
                moduleResponse.setTotalPrice(moduleResponse.getTotalPrice().add(price));
            }
        }

        // Separate modules into default and custom lists
        List<ModuleResponse> defaultModules = new ArrayList<>();
        List<ModuleResponse> customModules = new ArrayList<>();

        for (ModuleResponse module : moduleMap.values()) {
            if (module.getIsDefault()) {
                defaultModules.add(module);
            } else {
                customModules.add(module);
            }
        }

        // Return a map with two keys: "defaultModules" and "customModules"
        Map<String, List<ModuleResponse>> categorizedModules = new HashMap<>();
        categorizedModules.put("defaultModules", defaultModules);
        categorizedModules.put("customModules", customModules);

        return categorizedModules;
    }
}