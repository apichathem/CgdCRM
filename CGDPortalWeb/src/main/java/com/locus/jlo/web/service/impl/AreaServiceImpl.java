/**
 * 
 */
package com.locus.jlo.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.jlo.web.bean.dto.AmphurDTO;
import com.locus.jlo.web.bean.dto.CityDTO;
import com.locus.jlo.web.bean.dto.ProvinceDTO;
import com.locus.jlo.web.bean.dto.RegionDTO;
import com.locus.jlo.web.service.AreaService;

/**
 * @author Mr.BoonOom
 *
 */
@Service
public class AreaServiceImpl extends BaseService implements AreaService {
	
	private Logger logger = Logger.getLogger(getClass());
	
	public static final String SQL_FIND_PROVINCE_ALL = "AREA.SQL_SEARCH_PROVINCE_ALL";
	public static final String SQL_SEARCH_CITY_BY_DISTRICT_ID = "AREA.SQL_SEARCH_CITY_BY_DISTRICT_ID";
	public static final String SQL_SEARCH_PROVINCE_BY_PROVICE_ID = "AREA.SQL_SEARCH_PROVINCE_BY_PROVICE_ID";
	public static final String SQL_SEARCH_AMPHUR_BY_PROVICE_ID = "AREA.SQL_SEARCH_AMPHUR_BY_PROVICE_ID";
	public static final String SQL_SEARCH_PROVINCE_AMPHUR_BY_AMPHUR_ID = "AREA.SQL_SEARCH_PROVINCE_AMPHUR_BY_AMPHUR_ID";
	public static final String SQL_SEARCH_ZIPCODE_BY_TUMBON_CODE = "AREA.SQL_SEARCH_ZIPCODE_BY_TUMBON_CODE";
	
	@Override
	public ServiceResult<List<ProvinceDTO>> searchProvinceList() throws Exception {
		
		logger.info("+++++++++++++ In searchProvinceList ++++++++++++++++++++");
		ServiceResult<List<ProvinceDTO>> serviceResult = new ServiceResult<List<ProvinceDTO>>();
		List<ProvinceDTO> result = null;		
		
		try{
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");
			
			result = dynamicJdbcDao.findForList(SQL_FIND_PROVINCE_ALL
									,BeanPropertyRowMapper.newInstance(ProvinceDTO.class));
			
			logger.info("+++++++++++++ In Befor result :"+result.size());
			
			serviceResult = new ServiceResult<List<ProvinceDTO>>(result);
			
			logger.info("Element result :: "+serviceResult.getResult().size());
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<ProvinceDTO>>(e);
		}
		return serviceResult;
	}
	
	@Override
	public ServiceResult<List<CityDTO>> searchCityListByDistrictId(String districtId) throws Exception {
		
		logger.info("+++++++++++++ In searchCityListByDistrictId ++++++++++++++++++++");
		ServiceResult<List<CityDTO>> serviceResult = new ServiceResult<List<CityDTO>>();
		List<CityDTO> result = null;		
		
		try{
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");
			
			result = dynamicJdbcDao.findForList(SQL_SEARCH_CITY_BY_DISTRICT_ID
									,BeanPropertyRowMapper.newInstance(CityDTO.class)
									, new SimpleKeyValue("districtId", districtId));
			
			logger.info("+++++++++++++ In Befor result :"+result.size());
			
			serviceResult = new ServiceResult<List<CityDTO>>(result);
			
			logger.info("Element result :: "+serviceResult.getResult().size());
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<CityDTO>>(e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<List<ProvinceDTO>> searchProvinceById(String provinceId) throws Exception {
		
		logger.info("+++++++++++++ In searchProvinceListById ++++++++++++++++++++");
		ServiceResult<List<ProvinceDTO>> serviceResult = new ServiceResult<List<ProvinceDTO>>();
		List<ProvinceDTO> result = null;		
		
		try{
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");
			
			result = dynamicJdbcDao.findForList(SQL_SEARCH_PROVINCE_BY_PROVICE_ID
									,BeanPropertyRowMapper.newInstance(ProvinceDTO.class)
									,new SimpleKeyValue("provinceId", provinceId));
			
			logger.info("+++++++++++++ In Befor result :"+result.size());
			
			serviceResult = new ServiceResult<List<ProvinceDTO>>(result);
			
			logger.info("Element result :: "+serviceResult.getResult().size());
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<ProvinceDTO>>(e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<List<AmphurDTO>> searchAmphurListByProvinceId(String provinceId) throws Exception {
		
		logger.info("+++++++++++++ In searchAmphurListByProvinceId ++++++++++++++++++++");
		
		ServiceResult<List<AmphurDTO>> serviceResult = new ServiceResult<List<AmphurDTO>>();
		List<AmphurDTO> result = null;		
		
		try{
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");
			
			result = dynamicJdbcDao.findForList(SQL_SEARCH_AMPHUR_BY_PROVICE_ID,BeanPropertyRowMapper.newInstance(AmphurDTO.class)
					,new SimpleKeyValue("provinceId", provinceId));
			
			logger.info("+++++++++++++ In Befor result :"+result.size());
			
			serviceResult = new ServiceResult<List<AmphurDTO>>(result);
			
			logger.info("Element result :: "+serviceResult.getResult().size());
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<List<AmphurDTO>>(e);
		}
		
		return serviceResult;
	}

	@Override
	public ServiceResult<AmphurDTO> searchAmphurAmphurId(String amphurId) throws Exception {
		
		ServiceResult<AmphurDTO> serviceResult = new ServiceResult<AmphurDTO>();
		AmphurDTO result = null;		
		
		try{
			logger.info("+++++++++++++ In Befor findForPage ++++++++++++++++++++");
			
			result = dynamicJdbcDao.findForObject(SQL_SEARCH_PROVINCE_AMPHUR_BY_AMPHUR_ID	,BeanPropertyRowMapper.newInstance(AmphurDTO.class)
									,new SimpleKeyValue("amphurId", amphurId));
			
			logger.info("+++++++++++++ In Befor result :"+result);
			
			serviceResult =  new ServiceResult<AmphurDTO>(result);
			
			logger.info("Element result :: "+serviceResult.getResult());
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<AmphurDTO>(e);
		}
		
		return serviceResult;
	}

	@Override
	public ServiceResult<List<RegionDTO>> getRegion() throws Exception {
		ServiceResult<List<RegionDTO>> serviceResult = null;
		try{
			List<RegionDTO> list = dynamicJdbcDao.findForList("REGION.FIND_ALL"
					, BeanPropertyRowMapper.newInstance(RegionDTO.class));
			serviceResult = new ServiceResult<List<RegionDTO>>(list);
		}catch(Exception e){
			serviceResult = new ServiceResult<List<RegionDTO>>();
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<List<String>> searchZipcodeListByCityId(String cityId) throws Exception {
		ServiceResult<List<String>> serviceResult = null;
		try{
			List<String> list = dynamicJdbcDao.findForList(SQL_SEARCH_ZIPCODE_BY_TUMBON_CODE
					, String.class
					, new SimpleKeyValue("tumbon_code",cityId));
			serviceResult = new ServiceResult<List<String>>(list);
		}catch(Exception e){
			serviceResult = new ServiceResult<List<String>>();
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}
}
