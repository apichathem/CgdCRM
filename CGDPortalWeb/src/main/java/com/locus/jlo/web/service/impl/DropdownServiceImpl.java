package com.locus.jlo.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.jdbc.DynamicJdbcDao;
import com.locus.jlo.web.bean.dto.SlaDTO;
import com.locus.jlo.web.bean.dto.UserInfoDTO;
import com.locus.jlo.web.bean.modelbean.RoleModelBean;
import com.locus.jlo.web.service.DropdownService;
import com.locus.jlo.web.service.RoleService;
import com.locus.jlo.web.service.SlaService;
import com.locus.jlo.web.service.UserManagementService;

@Service
public class DropdownServiceImpl extends BaseService implements DropdownService {
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserManagementService userManagementService;
	
	@Autowired
	private SlaService slaService;
	
	@Autowired
	@Qualifier("dynamicJdbcDao")
	private DynamicJdbcDao dynamicJdbcDao;

	private Logger logger = Logger.getLogger(getClass());
	//private static final String SQL_GET_CORPORATION = "CUST.SQL_GET_CORPORATION";
	
//	private static final String ACTIVITY_TYPE_CODE_TYPE = "ACT_TYPE";
//	private static final String POSITION_CODE_TYPE = "POSITION";

	@Override
	public ServiceResult<Map<Integer, String>> getRoleSelect() {
		ServiceResult<Map<Integer, String>> result = new ServiceResult<Map<Integer, String>>();

		try {
			Map<Integer, String> tpRoleRes = new HashMap<Integer, String>();
			ServiceResult<List<RoleModelBean>> resultList = roleService.findListByEnabled();
			if(resultList.isSuccess()&& resultList.getResult().size() > 0){
				for(int i = 0 ; i < resultList.getResult().size() ; i ++){
					tpRoleRes.put(resultList.getResult().get(i).getRoleId(), resultList.getResult().get(i).getRoleName());
				}
			}
			result.setSuccess(Boolean.TRUE);
			result.setResult(tpRoleRes);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		return result;
	}


	@Override
	public ServiceResult<Map<String, String>> getDepartmentSelect() {
		ServiceResult<Map<String, String>> result = new ServiceResult<Map<String, String>>();
		
		try {
//			Map<String, String> tpDepartmentRes = new HashMap<String, String>();
//			Sort sort = new Sort(Sort.Direction.ASC, "deptCode");
//			Iterable<TpDepartment> res = tpDepartmentRepository.findAll(sort);
//			
//			if (res != null) {
//				Iterator<TpDepartment> tpDepartmentIter = res.iterator();
//				while (tpDepartmentIter.hasNext()) {
//					TpDepartment tpDepartment = (TpDepartment) tpDepartmentIter.next();
//					tpDepartmentRes.put(tpDepartment.getDeptCode(), tpDepartment.getDeptName());
//				}
//			}
			
//			result.setSuccess(Boolean.TRUE);
//			result.setResult(tpDepartmentRes);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}
		
		return result;
	}
	

	@Override
	public ServiceResult<Map<Integer, String>> getUserSelect() {
		ServiceResult<Map<Integer, String>> result = new ServiceResult<Map<Integer, String>>();

		try {
			Map<Integer, String> userRes = new HashMap<Integer, String>();
			ServiceResult<List<UserInfoDTO>> resultList = userManagementService.getUserList();
			if(resultList.isSuccess()&& resultList.getResult().size() > 0){
				
				List<UserInfoDTO> userInfoDTOList = resultList.getResult();
				for(UserInfoDTO userInfoDTO : userInfoDTOList){
					userRes.put(userInfoDTO.getUserId(), userInfoDTO.getFirstName() + " " + userInfoDTO.getLastName());
				}
			}
			result.setSuccess(Boolean.TRUE);
			result.setResult(userRes);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}

	@Override
	public ServiceResult<Map<Integer, String>> getSlaSelect() {
		ServiceResult<Map<Integer, String>> result = new ServiceResult<Map<Integer, String>>();

		try {
			Map<Integer, String> userRes = new HashMap<Integer, String>();
			ServiceResult<List<SlaDTO>> resultList = slaService.getSlaList();
			if(resultList.isSuccess()&& resultList.getResult().size() > 0){
				
				List<SlaDTO> slaDTOList = resultList.getResult();
				for(SlaDTO slaDTO : slaDTOList){
					userRes.put(slaDTO.getSlaId(), slaDTO.getSlaName());
				}
			}
			result.setSuccess(Boolean.TRUE);
			result.setResult(userRes);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;
	}
}
