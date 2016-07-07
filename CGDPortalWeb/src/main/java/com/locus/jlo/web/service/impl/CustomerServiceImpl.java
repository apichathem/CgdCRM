package com.locus.jlo.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.locus.common.domain.ServiceResult;
import com.locus.common.domain.SimpleKeyValue;
import com.locus.common.jdbc.DynamicJdbcDao;
import com.locus.common.mapper.PrimitiveSafeBeanPropertyRowMapper;
import com.locus.jlo.web.service.CustomerService;
import com.locus.jlo.web.bean.criteria.AddressCriteria;
import com.locus.jlo.web.bean.criteria.ContactCriteria;
import com.locus.jlo.web.bean.criteria.CustomerCriteria;
import com.locus.jlo.web.bean.dto.AddressDTO;
import com.locus.jlo.web.bean.dto.ContactDTO;
import com.locus.jlo.web.bean.dto.CustomerAssetDTO;
import com.locus.jlo.web.bean.dto.CustomerComplaintDTO;
import com.locus.jlo.web.bean.dto.CustomerDTO;
//import com.locus.jlo.web.bean.dto.CustomerIncidentDTO;
import com.locus.jlo.web.bean.dto.CustomerServiceRequestDTO;
import com.locus.jlo.web.bean.dto.SlaDTO;

@Service
public class CustomerServiceImpl extends BaseService implements CustomerService {
	
	@Autowired
	@Qualifier("dynamicJdbcDao")
	private DynamicJdbcDao dynamicJdbcDao;
	
	private Logger logger = Logger.getLogger(getClass());
	
	public static final String SQL_FIND_CUSTOMER_LIST = "CUST.SQL_FIND_CUSTOMER_LIST";
	public static final String SQL_FIND_CUST_DETAIL_INDIVIDUAL = "CUST.SQL_FIND_CUST_DETAIL_INDIVIDUAL";
	public static final String SQL_FIND_CUST_DETAIL_CORPORATE = "CUST.SQL_FIND_CUST_DETAIL_CORPORATE";
	public static final String SQL_FIND_MORE_INFO_INDIVIDUAL = "CUST.SQL_FIND_MORE_INFO_INDIVIDUAL";
	public static final String SQL_INSERT_CUST_CORPORATION = "CUST.INSERT_CUST_CORPORATION";
	public static final String SQL_INSERT_CUST_INDIVIDUAL = "CUST.INSERT_CUST_INDIVIDUAL";
	public static final String SQL_INSERT_CUST_MASTER = "CUST.INSERT_CUST_MASTER";
	public static final String SQL_UPDATE_MORE_INFO = "CUST.UPDATE_MORE_INFO";
	public static final String SQL_UPDATE_CUST_INDIVIDUAL = "CUST.UPDATE_CUST_INDIVIDUAL";
	public static final String SQL_UPDATE_CUST_CORPORATION = "CUST.UPDATE_CUST_CORPORATION"; 
	public static final String SQL_FIND_CUSTOMER_BY_CODE = "CUST.SQL_FIND_CUSTOMER_BY_CODE";
	
	public static final String SQL_INSERT_CONTACT_WITH_IND = "CUST.SQL_INSERT_CONTACT_WITH_IND"; 
	
	// Contact
	public static final String SQL_FIND_CUSTOMER_CONTACT_LIST = "CONTACT.SQL_FIND_CUSTOMER_LIST_CONTACT";
	public static final String SQL_FIND_CUSTOMER_CONTACT = "CONTACT.SQL_FIND_CUSTOMER_CONTACT";
	public static final String SQL_COUNT_CUSTOMER_CONTACT = "CONTACT.SQL_COUNT_CUSTOMER_CONTACT";
	public static final String SQL_FIND_CONTACT_LIST = "CONTACT.SQL_FIND_LIST_CONTACT";
	public static final String SQL_INSERT_NEW_CONTACT = "CONTACT.INSERT_CONTACT";
	public static final String SQL_INSERT_CUSTOMER_CONTACT = "CONTACT.INSERT_CUSTOMER_CONTACT";
	public static final String SQL_DELETE_CUSTOMER_CONTACT = "CONTACT.DELETE_CUSTOMER_CONTACT";
	public static final String SQL_UPDATE_CONTACT = "CONTACT.UPDATE_CONTACT";
	public static final String SQL_FIND_CUSTOMER_CONTACT_BY_ID = "CONTACT.SQL_FIND_CUSTOMER_CONTACT_BY_ID";
	
	// Address
	public static final String SQL_FIND_CUSTOMER_ADDRESS_LIST = "ADDRESS.SQL_FIND_CUSTOMER_LIST_ADDRESS";
	public static final String SQL_FIND_ADDRESS_LIST = "ADDRESS.SQL_FIND_LIST_ADDRESS";
	public static final String SQL_INSERT_NEW_ADDRESS = "ADDRESS.INSERT_ADDRESS";
	public static final String SQL_INSERT_CUSTOMER_ADDRESS = "ADDRESS.INSERT_CUSTOMER_ADDRESS";
	public static final String SQL_COUNT_CUSTOMER_ADDRESS = "ADDRESS.COUNT_CUSTOMER_ADDRESS";
	public static final String SQL_UPDATE_ADDRESS = "ADDRESS.UPDATE_ADDRESS";
	public static final String SQL_DELETE_CUSTOMER_ADDRESS = "ADDRESS.DELETE_CUSTOMER_ADDRESS";
	public static final String SQL_FIND_CUSTOMER_ADDRESS = "ADDRESS.FIND_CUSTOMER_ADDRESS";
	public static final String SQL_UPDATE_PRIMARY_ADDRESS = "ADDRESS.UPDATE_PRIMARY_ADDRESS";
	public static final String SQL_FIND_CUSTOMER_ADDRESS_BY_ID = "ADDRESS.FIND_CUSTOMER_ADDRESS_BY_ID";
	
	// Tab Service Request
	public static final String SQL_FIND_CUST_SERVICE_REQUEST_LIST = "CUST.SQL_FIND_CUST_SERVICE_REQUEST_LIST";
	public static final String SQL_FIND_CUST_COMPLAINT_LIST = "CUST.SQL_FIND_CUST_COMPLAINT_LIST"; 
	public static final String SQL_FIND_CUST_INCIDENT_LIST = "CUST.SQL_FIND_CUST_INCIDENT_LIST";
	public static final String SQL_FIND_CUST_ASSET_LIST = "CUST.SQL_FIND_CUST_ASSET_LIST";
	
	// for combo box
	public static final String SQL_FIND_GET_CONTACT_BY_CUST_ID_LIST = "CUST.GET_CONTACT_BY_CUST_ID_LIST";
	
	
	
	@Override
	public ServiceResult<Page<CustomerDTO>> searchCustomer(Pageable pageable, CustomerCriteria criteria, String langCd) {
		ServiceResult<Page<CustomerDTO>> serviceResult = new ServiceResult<Page<CustomerDTO>>();
		Page<CustomerDTO> result = null;
		try{
			result = dynamicJdbcDao.findForPage(SQL_FIND_CUSTOMER_LIST, PrimitiveSafeBeanPropertyRowMapper.newInstance(CustomerDTO.class), pageable, criteria, new SimpleKeyValue("langCd", langCd));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<CustomerDTO> searchCustomerDetailIndividual(Integer custId) {
		ServiceResult<CustomerDTO> serviceResult = new ServiceResult<CustomerDTO>();
		CustomerDTO detailDTO = new CustomerDTO();
		try {
			detailDTO = dynamicJdbcDao.findForObject(SQL_FIND_CUST_DETAIL_INDIVIDUAL, BeanPropertyRowMapper.newInstance(CustomerDTO.class), new SimpleKeyValue("custId", custId));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(detailDTO);
		} catch (Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<CustomerDTO> searchCustomerDetailCorporate(Integer custId) {
		ServiceResult<CustomerDTO> serviceResult = new ServiceResult<CustomerDTO>();
		CustomerDTO detailDTO = new CustomerDTO();
		try {
			detailDTO = dynamicJdbcDao.findForObject(SQL_FIND_CUST_DETAIL_CORPORATE, BeanPropertyRowMapper.newInstance(CustomerDTO.class), new SimpleKeyValue("custId", custId));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(detailDTO);
		} catch (Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<CustomerDTO> insertIndividual(CustomerDTO dto) {
		ServiceResult<CustomerDTO> serviceResult = new ServiceResult<CustomerDTO>();
		try {
			// insert TP_INDIVIDUAL
			Long indId = dynamicJdbcDao.executeInsert(SQL_INSERT_CUST_INDIVIDUAL, true, 
													 new SimpleKeyValue("titleCd", dto.getTitleCd()),
													 new SimpleKeyValue("firstName", dto.getFirstName()),
													 new SimpleKeyValue("midName", dto.getMidName()),
													 new SimpleKeyValue("lastName", dto.getLastName()),
													 new SimpleKeyValue("nickName", dto.getNickName()),
													 new SimpleKeyValue("citizenId", dto.getCitizenId()),
													 new SimpleKeyValue("passportNo", dto.getPassportNo()),
													 new SimpleKeyValue("genderCd", dto.getGenderCd()),
													 new SimpleKeyValue("birthDt", dto.getBirthDt()),
													 new SimpleKeyValue("nationalityCd", dto.getNationalityCd()),
													 new SimpleKeyValue("raceCd", dto.getRaceCd()),
													 new SimpleKeyValue("occupationCd", dto.getOccupationCd()),
													 new SimpleKeyValue("incomeCd", dto.getIncomeCd()),
													 new SimpleKeyValue("jobTitleCd", dto.getJobTitleCd()),
													 new SimpleKeyValue("educationCd", dto.getEducationCd()),
													 new SimpleKeyValue("maritalStatusCd", dto.getMaritalStatusCd()),
													 new SimpleKeyValue("householdSize", dto.getHouseholdSize()),
													 new SimpleKeyValue("interestCd", dto.getInterestCd()),
													 new SimpleKeyValue("mobileNo", dto.getMobileNo()),
													 new SimpleKeyValue("homePhone", dto.getHomePhone()),
													 new SimpleKeyValue("workPhone", dto.getWorkPhone()),

													 new SimpleKeyValue("mobileNoExt", dto.getMobileNoExt()),
													 new SimpleKeyValue("homePhoneExt", dto.getHomePhoneExt()),
													 new SimpleKeyValue("workPhoneExt", dto.getWorkPhoneExt()),
													 
													 
													 new SimpleKeyValue("faxPhone", dto.getFaxPhone()),
													 new SimpleKeyValue("extNo", dto.getExtNo()),
													 new SimpleKeyValue("addr1", dto.getAddr1()),
													 new SimpleKeyValue("addr2", dto.getAddr2()),
													 new SimpleKeyValue("subArea", dto.getSubArea()),
													 new SimpleKeyValue("area", dto.getArea()),
													 new SimpleKeyValue("province", dto.getProvince()),
													 new SimpleKeyValue("countryCd", dto.getCountryCd()),
													 new SimpleKeyValue("postalCode", dto.getPostalCode()),
													 new SimpleKeyValue("email", dto.getEmail()),
													 new SimpleKeyValue("ministry", dto.getMinistry()),
													 new SimpleKeyValue("department", dto.getDepartment()),
													 new SimpleKeyValue("divisionDept", dto.getDivisionDept()),
													 new SimpleKeyValue("position", dto.getPosition()),
													 new SimpleKeyValue("provinceId", dto.getProvinceId()),
													 new SimpleKeyValue("regId", dto.getRegId()),
													 new SimpleKeyValue("regDt", dto.getRegDt())
													);
			logger.info("[[ insert indId ]]"+indId);
			
			// insert TP_CUST_MASTER
			Long custId = dynamicJdbcDao.executeInsert(SQL_INSERT_CUST_MASTER, true, 
													 new SimpleKeyValue("indId", indId),
													 new SimpleKeyValue("corpId", dto.getCorpId()),
													 new SimpleKeyValue("custCode", dto.getCustCode()),
													 new SimpleKeyValue("custName", dto.getCustName()),
													 new SimpleKeyValue("typeCd", dto.getTypeCd()),
													 new SimpleKeyValue("segmentCd", dto.getSegmentCd()),
													 new SimpleKeyValue("statusCd", dto.getStatusCd()),
													 new SimpleKeyValue("contactChannelCd", dto.getContactChannelCd()),
													 new SimpleKeyValue("languageCd", dto.getLanguageCd()),
													 new SimpleKeyValue("totSpentAmt", dto.getTotSpentAmt()),
													 new SimpleKeyValue("lastPurchasedDt", dto.getLastPurchasedDt()),
													 new SimpleKeyValue("ownerId", dto.getOwnerId()),
													 new SimpleKeyValue("comment", dto.getComment()),
													 new SimpleKeyValue("notCallYn", dto.getNotCallYn()),
													 new SimpleKeyValue("notSmsYn", dto.getNotSmsYn()),
													 new SimpleKeyValue("notEmailYn", dto.getNotEmailYn()),
													 new SimpleKeyValue("notMailYn", dto.getNotMailYn()),
													 new SimpleKeyValue("regId", dto.getRegId()),
													 new SimpleKeyValue("regDt", dto.getRegDt())
													);
			logger.info("[[ insert custId ]]"+custId);
			
			// Insert TP_CONTACT
			Long contId = dynamicJdbcDao.executeInsert(SQL_INSERT_CONTACT_WITH_IND, true,
													 new SimpleKeyValue("indId", indId),
													 new SimpleKeyValue("regId", dto.getRegId())
													);
			logger.info("[[ insert contId ]]"+contId);
			
						
			dto.setCustId(custId);
			dto.setIndId(indId);
			serviceResult.setSuccess(true);
			serviceResult.setResult(dto);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<CustomerDTO> searchMoreInfoIndividual(Long custId) {
		ServiceResult<CustomerDTO> serviceResult = new ServiceResult<CustomerDTO>();
		CustomerDTO detailDTO = new CustomerDTO();
		try {
			detailDTO = dynamicJdbcDao.findForObject(SQL_FIND_MORE_INFO_INDIVIDUAL, BeanPropertyRowMapper.newInstance(CustomerDTO.class), new SimpleKeyValue("custId", custId));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(detailDTO);
		} catch (Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Integer> updateMoreInfo(CustomerDTO dto) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try {
			int result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_MORE_INFO, dto);
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(new Integer(result));
		} catch (Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		
		return serviceResult;
	}

	@Override
	public ServiceResult<Integer> updateIndividual(CustomerDTO dto) {
		logger.info("dto getDivisionDept : "+dto.getDivisionDept());
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try {
			int result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_CUST_INDIVIDUAL, dto);
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(new Integer(result));
		} catch (Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<CustomerDTO> insertCorporation(CustomerDTO dto) {
		ServiceResult<CustomerDTO> serviceResult = new ServiceResult<CustomerDTO>();
		try {
			Long corpId = dynamicJdbcDao.executeInsert(SQL_INSERT_CUST_CORPORATION, true, 
														new SimpleKeyValue("companyName", dto.getCompanyName()),
														new SimpleKeyValue("altName", dto.getAltName()),
														new SimpleKeyValue("registrationId", dto.getRegistrationId()),
														new SimpleKeyValue("taxId", dto.getTaxId()),
														new SimpleKeyValue("industryCd", dto.getIndustryCd()),
														new SimpleKeyValue("companySizeCd", dto.getCompanySizeCd()),
														new SimpleKeyValue("revenueCd", dto.getRevenueCd()),
														new SimpleKeyValue("workPhone", dto.getWorkPhone()),
														new SimpleKeyValue("workPhoneExt", dto.getWorkPhoneExt()),
														new SimpleKeyValue("faxPhone", dto.getFaxPhone()),
														new SimpleKeyValue("addr1", dto.getAddr1()),
														new SimpleKeyValue("addr2", dto.getAddr2()),
														new SimpleKeyValue("subArea", dto.getSubArea()),
														new SimpleKeyValue("area", dto.getArea()),
														new SimpleKeyValue("province", dto.getProvince()),
														new SimpleKeyValue("countryCd", dto.getCountryCd()),
														new SimpleKeyValue("postalCode", dto.getPostalCode()),
														new SimpleKeyValue("email", dto.getEmail()),
														new SimpleKeyValue("url", dto.getUrl()),
														new SimpleKeyValue("regId", dto.getRegId()),
														new SimpleKeyValue("regDt", dto.getRegDt())
													
													);
			logger.info("[[ insert corpId ]]"+corpId);
			
			Long custId = dynamicJdbcDao.executeInsert(SQL_INSERT_CUST_MASTER, true, 
														 new SimpleKeyValue("indId", dto.getIndId()),
														 new SimpleKeyValue("corpId", corpId),
														 new SimpleKeyValue("custCode", dto.getCustCode()),
														 new SimpleKeyValue("custName", dto.getCustName()),
														 new SimpleKeyValue("typeCd", dto.getTypeCd()),
														 new SimpleKeyValue("segmentCd", dto.getSegmentCd()),
														 new SimpleKeyValue("statusCd", dto.getStatusCd()),
														 new SimpleKeyValue("contactChannelCd", dto.getContactChannelCd()),
														 new SimpleKeyValue("languageCd", dto.getLanguageCd()),
														 new SimpleKeyValue("totSpentAmt", dto.getTotSpentAmt()),
														 new SimpleKeyValue("lastPurchasedDt", dto.getLastPurchasedDt()),
														 new SimpleKeyValue("ownerId", dto.getOwnerId()),
														 new SimpleKeyValue("comment", dto.getComment()),
														 new SimpleKeyValue("notCallYn", dto.getNotCallYn()),
														 new SimpleKeyValue("notSmsYn", dto.getNotSmsYn()),
														 new SimpleKeyValue("notEmailYn", dto.getNotEmailYn()),
														 new SimpleKeyValue("notMailYn", dto.getNotMailYn()),
														 new SimpleKeyValue("regId", dto.getRegId()),
														 new SimpleKeyValue("regDt", dto.getRegDt())														
													);
			
			logger.info("[[ insert custId ]]"+custId);
			dto.setCustId(custId);
			dto.setCorpId(corpId);
			
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(dto);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Integer> updateCorporation(CustomerDTO dto) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try {
			int result = dynamicJdbcDao.executeUpdate(SQL_UPDATE_CUST_CORPORATION, dto);
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(new Integer(result));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Page<ContactDTO>> searchContactList(Pageable pageable, ContactCriteria contactCriteria) {
		ServiceResult<Page<ContactDTO>> serviceResult = new ServiceResult<Page<ContactDTO>>();
		Page<ContactDTO> result = null;
		try{
			result = dynamicJdbcDao.findForPage(SQL_FIND_CONTACT_LIST, PrimitiveSafeBeanPropertyRowMapper.newInstance(ContactDTO.class), pageable, contactCriteria);
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<ContactDTO> insertContact(ContactDTO dto) {
		ServiceResult<ContactDTO> serviceResult = new ServiceResult<ContactDTO>();
		try {
			// insert TP_INDIVIDUAL
			Long custContId = dynamicJdbcDao.executeInsert(SQL_INSERT_NEW_CONTACT, true, 
													 new SimpleKeyValue("firstName", dto.getFirstName()),
													 new SimpleKeyValue("lastName", dto.getLastName()),
													 new SimpleKeyValue("mobileNo", dto.getMobileNo()),
													 new SimpleKeyValue("homePhone", dto.getHomePhone()),
													 new SimpleKeyValue("workPhone", dto.getWorkPhone()),
													 new SimpleKeyValue("faxPhone", dto.getFaxPhone()),
													 new SimpleKeyValue("email", dto.getEmail()),
													 new SimpleKeyValue("addr1", dto.getAddr1()),
													 new SimpleKeyValue("addr2", dto.getAddr2()),
													 new SimpleKeyValue("subArea", dto.getSubArea()),
													 new SimpleKeyValue("area", dto.getArea()),
													 new SimpleKeyValue("province", dto.getProvince()),
													 new SimpleKeyValue("postalCode", dto.getPostalCode()),
													 new SimpleKeyValue("countryCd", dto.getCountryCd()),
													 new SimpleKeyValue("relationCd", dto.getRelationCd()),
													 new SimpleKeyValue("regId", dto.getRegId()),
													 new SimpleKeyValue("custId", dto.getCustId())
													);
			
			
			// Find addr_id from cust_addr_id
			dto = dynamicJdbcDao.findForObject(SQL_FIND_CUSTOMER_CONTACT_BY_ID, PrimitiveSafeBeanPropertyRowMapper.newInstance(ContactDTO.class), new SimpleKeyValue("custContId", custContId));
			
			serviceResult.setSuccess(true);
			serviceResult.setResult(dto);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<ContactDTO> insertCustomerContact(ContactDTO contactDTO) {
		ServiceResult<ContactDTO> serviceResult = new ServiceResult<ContactDTO>();
		try {
			Long contId = dynamicJdbcDao.executeInsert(SQL_INSERT_CUSTOMER_CONTACT, true, 
										 new SimpleKeyValue("custId", contactDTO.getCustId()),
										 new SimpleKeyValue("contId", contactDTO.getContId()),
										 new SimpleKeyValue("relationCd", contactDTO.getRelationCd()),
										 new SimpleKeyValue("regId", contactDTO.getRegId())
										);
			contactDTO.setContId(contId.intValue());
			serviceResult.setSuccess(true);
			serviceResult.setResult(contactDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Page<ContactDTO>> searchCustomerContactList(Pageable pageable, Integer custId, String langCd) {
		ServiceResult<Page<ContactDTO>> serviceResult = new ServiceResult<Page<ContactDTO>>();
		Page<ContactDTO> result = null;
		try{
			result = dynamicJdbcDao.findForPage(SQL_FIND_CUSTOMER_CONTACT_LIST, PrimitiveSafeBeanPropertyRowMapper.newInstance(ContactDTO.class), pageable, new SimpleKeyValue("custId", custId), new SimpleKeyValue("langCd", langCd));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}
	 
	@Override
	public Boolean deleteCustomerContact(Integer custId, Integer contId) {
		try {
			dynamicJdbcDao.executeUpdate(SQL_DELETE_CUSTOMER_CONTACT, new SimpleKeyValue("custId", custId), new SimpleKeyValue("contId", contId));
			return Boolean.TRUE;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Boolean.FALSE;
			
		}
		
	}
	
	@Override
	public ServiceResult<ContactDTO> searchContactById(Integer custId, Integer contId) {
		ServiceResult<ContactDTO> serviceResult = new ServiceResult<ContactDTO>();
		ContactDTO result = null;
		try{
			result = dynamicJdbcDao.findForObject(SQL_FIND_CUSTOMER_CONTACT, PrimitiveSafeBeanPropertyRowMapper.newInstance(ContactDTO.class), new SimpleKeyValue("custId", custId), new SimpleKeyValue("contId", contId));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public int countCustomerContact(Integer custId, Integer contId) {
		try {
			return dynamicJdbcDao.findForLong(SQL_COUNT_CUSTOMER_CONTACT, new SimpleKeyValue("custId", custId), new SimpleKeyValue("contId", contId)).intValue();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}

	@Override
	public Boolean updateCustomerContact(ContactDTO contactDTO) {
		try {
			dynamicJdbcDao.executeUpdate(SQL_UPDATE_CONTACT, new SimpleKeyValue("firstName", contactDTO.getFirstName()),
					 new SimpleKeyValue("lastName", contactDTO.getLastName()),
					 new SimpleKeyValue("mobileNo", contactDTO.getMobileNo()),
					 new SimpleKeyValue("homePhone", contactDTO.getHomePhone()),
					 new SimpleKeyValue("workPhone", contactDTO.getWorkPhone()),
					 new SimpleKeyValue("faxPhone", contactDTO.getFaxPhone()),
					 new SimpleKeyValue("email", contactDTO.getEmail()),
					 new SimpleKeyValue("addr1", contactDTO.getAddr1()),
					 new SimpleKeyValue("addr2", contactDTO.getAddr2()),
					 new SimpleKeyValue("subArea", contactDTO.getSubArea()),
					 new SimpleKeyValue("area", contactDTO.getArea()),
					 new SimpleKeyValue("province", contactDTO.getProvince()),
					 new SimpleKeyValue("postalCode", contactDTO.getPostalCode()),
					 new SimpleKeyValue("countryCd", contactDTO.getCountryCd()),
					 new SimpleKeyValue("relationCd", contactDTO.getRelationCd()),
					 new SimpleKeyValue("chgId", contactDTO.getChgId()) ,
					 new SimpleKeyValue("custId", contactDTO.getCustId()) ,
					 new SimpleKeyValue("contId", contactDTO.getContId()) ,
					 new SimpleKeyValue("indId", contactDTO.getIndId()) 
			);
			
			return Boolean.TRUE;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Boolean.FALSE;
			
		}
	}
	
	@Override
	public ServiceResult<Page<AddressDTO>> searchCustomerAddressList(Pageable pageable, Integer custId, String langCd) {
		ServiceResult<Page<AddressDTO>> serviceResult = new ServiceResult<Page<AddressDTO>>();
		Page<AddressDTO> result = null;
		try{
			result = dynamicJdbcDao.findForPage(SQL_FIND_CUSTOMER_ADDRESS_LIST, PrimitiveSafeBeanPropertyRowMapper.newInstance(AddressDTO.class), pageable, new SimpleKeyValue("custId", custId), new SimpleKeyValue("langCd", langCd));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<AddressDTO> insertAddress(AddressDTO dto) {
		ServiceResult<AddressDTO> serviceResult = new ServiceResult<AddressDTO>();
		try {
			// insert TP_INDIVIDUAL
			Long custAddrId = dynamicJdbcDao.executeInsert(SQL_INSERT_NEW_ADDRESS, true, 
													 new SimpleKeyValue("addr1", dto.getAddr1()),
													 new SimpleKeyValue("addr2", dto.getAddr2()),
													 new SimpleKeyValue("subArea", dto.getSubArea()),
													 new SimpleKeyValue("area", dto.getArea()),
													 new SimpleKeyValue("province", dto.getProvince()),
													 new SimpleKeyValue("countryCd", dto.getCountryCd()),
													 new SimpleKeyValue("postalCode", dto.getPostalCode()),
													 new SimpleKeyValue("addrTypeCd", dto.getAddrTypeCd()),
													 new SimpleKeyValue("regId", dto.getRegId()),
													 new SimpleKeyValue("custId", dto.getCustId())
													);
			
			// Find addr_id from cust_addr_id
			dto = dynamicJdbcDao.findForObject(SQL_FIND_CUSTOMER_ADDRESS_BY_ID, PrimitiveSafeBeanPropertyRowMapper.newInstance(AddressDTO.class), new SimpleKeyValue("custAddrId", custAddrId));
			
			serviceResult.setSuccess(true);
			serviceResult.setResult(dto);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<AddressDTO> insertCustomerAddress(AddressDTO addressDTO) {
		ServiceResult<AddressDTO> serviceResult = new ServiceResult<AddressDTO>();
		try {
			dynamicJdbcDao.executeInsert(SQL_INSERT_CUSTOMER_ADDRESS, false, 
										 new SimpleKeyValue("custId", addressDTO.getCustId()),
										 new SimpleKeyValue("addrId", addressDTO.getAddrId()),
										 new SimpleKeyValue("regId", addressDTO.getRegId())
										);
			serviceResult.setSuccess(true);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public int countCustomerAddress(Integer custId, Integer addrId) {
		try {
			return dynamicJdbcDao.findForLong(SQL_COUNT_CUSTOMER_ADDRESS, new SimpleKeyValue("custId", custId), new SimpleKeyValue("addrId", addrId)).intValue();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}

	@Override
	public Boolean deleteCustomerAddress(Integer custId, Integer addrId) {
		try {
			dynamicJdbcDao.executeUpdate(SQL_DELETE_CUSTOMER_ADDRESS, new SimpleKeyValue("custId", custId), new SimpleKeyValue("addrId", addrId));
			return Boolean.TRUE;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean updateCustomerAddress(AddressDTO dto) {
		try {
			dynamicJdbcDao.executeUpdate(SQL_UPDATE_ADDRESS,
					 new SimpleKeyValue("addr1", dto.getAddr1()),
					 new SimpleKeyValue("addr2", dto.getAddr2()),
					 new SimpleKeyValue("subArea", dto.getSubArea()),
					 new SimpleKeyValue("area", dto.getArea()),
					 new SimpleKeyValue("province", dto.getProvince()),
					 new SimpleKeyValue("countryCd", dto.getCountryCd()),
					 new SimpleKeyValue("postalCode", dto.getPostalCode()),
					 new SimpleKeyValue("addrTypeCd", dto.getAddrTypeCd()),
					 new SimpleKeyValue("chgId", dto.getChgId()),
					 new SimpleKeyValue("addrId", dto.getAddrId())
			);
			
			return Boolean.TRUE;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Boolean.FALSE;
			
		}
	}

	@Override
	public ServiceResult<AddressDTO> searchAddressById(Integer custId, Integer addrId) {
		ServiceResult<AddressDTO> serviceResult = new ServiceResult<AddressDTO>();
		AddressDTO result = null;
		try{
			result = dynamicJdbcDao.findForObject(SQL_FIND_CUSTOMER_ADDRESS, PrimitiveSafeBeanPropertyRowMapper.newInstance(AddressDTO.class), new SimpleKeyValue("custId", custId), new SimpleKeyValue("addrId", addrId));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public Boolean updatePrimaryAddress(Integer custId, Integer addrId) {
		try {
			dynamicJdbcDao.executeUpdate(SQL_UPDATE_PRIMARY_ADDRESS, new SimpleKeyValue("custId", custId), new SimpleKeyValue("addrId", addrId));
			return Boolean.TRUE;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
	}

	@Override
	public ServiceResult<Page<AddressDTO>> searchAddressList(Pageable pageable, AddressCriteria addressCriteria, String langCd) {
		ServiceResult<Page<AddressDTO>> serviceResult = new ServiceResult<Page<AddressDTO>>();
		Page<AddressDTO> result = null;
		try{
			result = dynamicJdbcDao.findForPage(SQL_FIND_ADDRESS_LIST, PrimitiveSafeBeanPropertyRowMapper.newInstance(AddressDTO.class), pageable, addressCriteria, new SimpleKeyValue("langCd", langCd) );
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Page<CustomerServiceRequestDTO>> searchCustomerServiceRequestList(Pageable pageable, Integer custId, String langCd) {
		ServiceResult<Page<CustomerServiceRequestDTO>> serviceResult = new ServiceResult<Page<CustomerServiceRequestDTO>>();
		Page<CustomerServiceRequestDTO> result = null;
		try{logger.info("custId -> " + custId);
			result = dynamicJdbcDao.findForPage(SQL_FIND_CUST_SERVICE_REQUEST_LIST, 
												PrimitiveSafeBeanPropertyRowMapper.newInstance(CustomerServiceRequestDTO.class), 
												pageable, 
												new SimpleKeyValue("custId", custId), 
												new SimpleKeyValue("langCd", langCd) );
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Page<CustomerComplaintDTO>> searchCustomerComplaintList(Pageable pageable, Integer custId, String langCd) {
		ServiceResult<Page<CustomerComplaintDTO>> serviceResult = new ServiceResult<Page<CustomerComplaintDTO>>();
		Page<CustomerComplaintDTO> result = null;
		try{
			result = dynamicJdbcDao.findForPage(SQL_FIND_CUST_COMPLAINT_LIST, 
												PrimitiveSafeBeanPropertyRowMapper.newInstance(CustomerComplaintDTO.class), 
												pageable, 
												new SimpleKeyValue("custId", custId), 
												new SimpleKeyValue("langCd", langCd) );
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	/*@Override
	public ServiceResult<Page<CustomerIncidentDTO>> searchCustomerIncidentList(Pageable pageable, Integer custId, String langCd) {
		ServiceResult<Page<CustomerIncidentDTO>> serviceResult = new ServiceResult<Page<CustomerIncidentDTO>>();
		Page<CustomerIncidentDTO> result = null;
		try{
			result = dynamicJdbcDao.findForPage(SQL_FIND_CUST_INCIDENT_LIST, 
												PrimitiveSafeBeanPropertyRowMapper.newInstance(CustomerIncidentDTO.class), 
												pageable, 
												new SimpleKeyValue("custId", custId), 
												new SimpleKeyValue("langCd", langCd) );
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}*/

	@Override
	public ServiceResult<Page<CustomerAssetDTO>> searchCustomerAssetList(Pageable pageable, Integer custId, String langCd) {
		ServiceResult<Page<CustomerAssetDTO>> serviceResult = new ServiceResult<Page<CustomerAssetDTO>>();
		Page<CustomerAssetDTO> result = null;
		try{
			result = dynamicJdbcDao.findForPage(SQL_FIND_CUST_ASSET_LIST, 
												PrimitiveSafeBeanPropertyRowMapper.newInstance(CustomerAssetDTO.class), 
												pageable, 
												new SimpleKeyValue("custId", custId), 
												new SimpleKeyValue("langCd", langCd) );
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(result);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<CustomerDTO> searchCustomerDetailByCode(String custCode) {
		ServiceResult<CustomerDTO> serviceResult = new ServiceResult<CustomerDTO>();
		CustomerDTO detailDTO = new CustomerDTO();
		try {
			detailDTO = dynamicJdbcDao.findForObject(SQL_FIND_CUSTOMER_BY_CODE, BeanPropertyRowMapper.newInstance(CustomerDTO.class), new SimpleKeyValue("custCode", custCode));
			serviceResult.setSuccess(Boolean.TRUE);
			serviceResult.setResult(detailDTO);
		} catch (Exception e){
			logger.error(e.getMessage(), e);
			setErrorResult(serviceResult, e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<List<ContactDTO>> getContactByCustIdList(Integer custId) {
		ServiceResult<List<ContactDTO>> result = new ServiceResult<List<ContactDTO>>();
		try {
			List<ContactDTO> res = dynamicJdbcDao.findForList(SQL_FIND_GET_CONTACT_BY_CUST_ID_LIST, PrimitiveSafeBeanPropertyRowMapper.newInstance(ContactDTO.class),new SimpleKeyValue("custId", custId));
			result.setSuccess(Boolean.TRUE);
			result.setResult(res);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			setErrorResult(result, e);
		}

		return result;

	}
}
