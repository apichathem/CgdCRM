package com.locus.jlo.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.jlo.web.bean.dto.BranchDTO;
import com.locus.jlo.web.service.BranchService;

@Service
public class BranchServiceImpl extends BaseService implements BranchService {

	private Logger logger = Logger.getLogger(getClass());

	private enum SQL {
		FIND_ALL_BRANCH("BRANCH.SQL_FIND_ALL_BRANCH"),
		FIND_BY_BRANCHCODE("BRANCH.SQL_FIND_BY_BRANCHCODE"),
		UPDATE_BRANCH_ADDR_LATLNG("BRANCH.SQL_UPDATE_BRANCH_ADDR_LATLNG"),
		FIND_ALL_BRANCH_WITH_UNITNAME("BRANCH.SQL_FIND_ALL_BRANCH_WITH_UNITNAME");

		private String query;

		private SQL(String query) {
			this.query = query;
		}

		public String getQueryName() {
			return query;
		}

	}

	@Override
	public ServiceResult<List<BranchDTO>> findAllBranch() throws Exception {
		logger.debug("BranchServiceImpl : findAllBranch()");

		ServiceResult<List<BranchDTO>> serviceResult = null;

		try {
			final List<BranchDTO> result = dynamicJdbcDao.findForList(SQL.FIND_ALL_BRANCH.getQueryName(),
					BeanPropertyRowMapper.newInstance(BranchDTO.class));
			serviceResult = new ServiceResult<List<BranchDTO>>(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<>(e);
		}

		return serviceResult;
	}

	@Override
	public ServiceResult<BranchDTO> findByBranchCode(final String branchCode) throws Exception {
		logger.debug("BranchServiceImpl : findById()");
		
		ServiceResult<BranchDTO> serviceResult = null;
		
		try {
			final BranchDTO result = dynamicJdbcDao.findForObject(SQL.FIND_BY_BRANCHCODE.getQueryName(), 
					BeanPropertyRowMapper.newInstance(BranchDTO.class), new SimpleKeyValue(
							"branchCode", branchCode));
			serviceResult = new ServiceResult<BranchDTO>(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceResult = new ServiceResult<>(e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Long> updateBranchAddrLatLng(String contentId, String latitude, String longtitude, Integer userId)
			throws Exception {
		ServiceResult<Long> result = null;
		try{
			long out = dynamicJdbcDao.executeUpdate(SQL.UPDATE_BRANCH_ADDR_LATLNG.getQueryName(), 
					new SimpleKeyValue("branchCode", contentId),
					new SimpleKeyValue("lat", latitude),
					new SimpleKeyValue("lon",longtitude));
			result =  new ServiceResult<Long>(new Long(out));
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			result =  new ServiceResult<Long>(e);
		}
		return result;
	}

	@Override
	public ServiceResult<Map<String, List<BranchDTO>>> findAllBranchWithUnitName() {
		ServiceResult<Map<String, List<BranchDTO>>> result = null;
		try {
			final List<BranchDTO> branchDTOs = dynamicJdbcDao.findForList(SQL.FIND_ALL_BRANCH_WITH_UNITNAME.query,
					BeanPropertyRowMapper.newInstance(BranchDTO.class));
			Map<String, List<BranchDTO>> branchMap = new TreeMap<>();
			for (BranchDTO branchDTO : branchDTOs) {
				List<BranchDTO> branchGroup = branchMap.get(branchDTO.getUnitName());
				if (branchGroup == null) {
					branchGroup = new ArrayList<>();
					branchMap.put(branchDTO.getUnitName(), branchGroup);
				}
				branchGroup.add(branchDTO);
			}
			
			result = new ServiceResult<>(branchMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new ServiceResult<>(e);
		}
		return result;
	}
	
	

}
