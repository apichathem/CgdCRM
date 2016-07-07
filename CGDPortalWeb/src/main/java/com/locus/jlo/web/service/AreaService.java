package com.locus.jlo.web.service;

import java.util.List;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.AmphurDTO;
import com.locus.jlo.web.bean.dto.CityDTO;
import com.locus.jlo.web.bean.dto.ProvinceDTO;
import com.locus.jlo.web.bean.dto.RegionDTO;

public interface AreaService {

	ServiceResult<List<ProvinceDTO>> searchProvinceList() throws Exception;
	ServiceResult<List<ProvinceDTO>> searchProvinceById(String provinceId) throws Exception;
	ServiceResult<List<AmphurDTO>> searchAmphurListByProvinceId(String provinceId) throws Exception;
	ServiceResult<AmphurDTO> searchAmphurAmphurId(String amphurId) throws Exception;
	ServiceResult<List<RegionDTO>> getRegion() throws Exception;
	ServiceResult<List<CityDTO>> searchCityListByDistrictId(String districtId) throws Exception;
	ServiceResult<List<String>> searchZipcodeListByCityId(String cityId) throws Exception;

}
