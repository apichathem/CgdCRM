package com.locus.jlo.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.locus.common.domain.ServiceResult;
import com.locus.common.utils.CollectionUtil;
import com.locus.common.utils.DateTimeUtils;
import com.locus.common.utils.JsonUtil;
import com.locus.common.utils.StringUtils;
import com.locus.jlo.web.bean.criteria.AddressCriteria;
import com.locus.jlo.web.bean.criteria.ContactCriteria;
import com.locus.jlo.web.bean.criteria.CustomerCriteria;
import com.locus.jlo.web.bean.dto.AddressDTO;
import com.locus.jlo.web.bean.dto.CallListDTO;
import com.locus.jlo.web.bean.dto.ConsultingDTO;
import com.locus.jlo.web.bean.dto.ContactDTO;
import com.locus.jlo.web.bean.dto.CustomerDTO;
import com.locus.jlo.web.bean.modelbean.AddressModelBean;
import com.locus.jlo.web.bean.modelbean.ContactModelBean;
import com.locus.jlo.web.bean.modelbean.CustomerModelBean;
import com.locus.jlo.web.bean.modeljson.DatatableModelBean;
import com.locus.jlo.web.bean.modeljson.JsonResultBean;
import com.locus.jlo.web.constant.ActionType;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.constant.SessionUserConstant;
import com.locus.jlo.web.service.ConsultingService;
import com.locus.jlo.web.service.CustomerService;
import com.locus.jlo.web.service.UserManagementService;
//import com.locus.jlo.web.bean.dto.CustomerIncidentDTO;
import com.locus.jlo.web.bean.dto.CustomerServiceRequestDTO;

/**
 * @author Rawiwan
 * 
 */
@Controller
public class CustomerController extends BaseController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ConsultingService consultingService;

	@Autowired
	private UserManagementService userManagementService;

	private Logger logger = Logger.getLogger(getClass());

	private static final String CUST_MODEL = "custModelBean";
	private static final String CONT_MODEL = "contactDto";
	private static final String ADDR_MODEL = "addressDto";
	private static final String CUST_INDIVIDUAL = "I";
	private static final String CUST_CORPORATE = "C";
	private static final String TAB_MOREINFO = "moreInfo";
	private static final String TAB_CONTACT = "contact";
	private static final String TAB_ADDRESS = "address";
	private static final String TAB_SR = "serviceRequest";

	private static final String BOOLEAN_Y = "Y";
	private static final String BOOLEAN_N = "N";

	@RequestMapping(value = "/customerMain")
	public ModelAndView customerMain(Model model, HttpServletRequest request, Locale local, @RequestParam(value = "module", required = false) String module,
			@RequestParam(value = "custId", required = false) String custId,
			@RequestParam(value = "phoneNo", required = false) String phoneNoCic) {

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.customerMain +++ ");

		CustomerModelBean custModelBean = new CustomerModelBean();
		// Set menuId
		setMenuId(request);

		model.addAttribute("pageTitle", messageSource.getMessage("customer.title", null, local));
		
		
		model.addAttribute("module", module);
		model.addAttribute("custId", custId);
		
		if(!StringUtils.isEmpty(module)){
			custModelBean.setModule(module);
			custModelBean.setCustPhoneCic(phoneNoCic);
			
			if ("cic".equalsIgnoreCase(module)) {
				
				String strUrlCIC = phoneNoCic;
				
				if(StringUtils.isNotEmpty(strUrlCIC)){
					try {
						strUrlCIC = URLDecoder.decode(strUrlCIC, "UTF-8");
						
						String[] strArrCic1 = strUrlCIC.split("@");
						String[] strArrCic2 = strArrCic1[0].split(":");
						custModelBean.setCustPhoneCic(strArrCic2[1]);
					} catch (UnsupportedEncodingException e) {
						logger.error(e.getMessage(), e);
						custModelBean.setCustPhoneCic("");
					}
					
				}else{
					
					custModelBean.setCustPhoneCic("");
				}
				
				
				
				if (getSessionAttr(request, SessionUserConstant.SESSION_USER_CONSULTING_OBJ) != null) {
					// Update ending time for consulting history
					ConsultingDTO consultingDTO = (ConsultingDTO) getSessionAttr(request, SessionUserConstant.SESSION_USER_CONSULTING_OBJ);
					consultingDTO.setEndDt(new Date());
					consultingDTO.setStatusCd("02");
					
					ServiceResult<Long> serviceResult = consultingService.update(consultingDTO);
					if (serviceResult.isSuccess()) {
						// Remove session
						removeSessionAttr(request, SessionUserConstant.SESSION_USER_CONSULTING_OBJ);
						removeSessionAttr(request, SessionUserConstant.SESSION_USER_CALL_LIST_OBJ);
						removeSessionAttr(request, "CONSULTING_ID");
					}
				}
			}
			
		}
		
		
		
		return new ModelAndView("customerMain", CUST_MODEL, custModelBean);
	}

	@RequestMapping(value = "/customerDetailByCustCode", method = RequestMethod.POST, headers = { "Accept=application/json; charset=UTF-8" })
	public @ResponseBody
	String customerDetailByCustCode(Model model, HttpServletRequest request, Locale local, @RequestParam(value = "custCode", required = false) String custCode) throws Exception {
		logger.info("*****************************************************");
		logger.info("+++ CustomerController.customerDetailByCustCode +++ ");
		logger.info("custCode : " + custCode);

		JsonResultBean jsonRes = new JsonResultBean();
		CallListDTO callListDTO = new CallListDTO();

		// Find customer detail from CUST_CODE (must be unique)
		ServiceResult<CustomerDTO> serviceResult = customerService.searchCustomerDetailByCode(custCode);
		if (serviceResult.isSuccess()) {
			CustomerDTO customerDTO = serviceResult.getResult();

			if (customerDTO != null) {
				String custId = String.valueOf(customerDTO.getCustId());
				String custType = customerDTO.getTypeCd();
				logger.info("custId : " + custId + " custType : " + custType);
				callListDTO.setCustId(custId);
				callListDTO.setCustType(custType);

				jsonRes.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonRes.setModel(callListDTO);
			} else {
				jsonRes.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonRes.setResultMessage("Cannot get customer detail");
			}

		} else {
			jsonRes.setResultCode(serviceResult.getResponseCode());
			jsonRes.setResultMessage(serviceResult.getResponseDescription());
		}

		return JsonUtil.toJSON(jsonRes, Boolean.TRUE);
	}

	@RequestMapping(value = "/customerDetail", method = RequestMethod.POST)
	public ModelAndView customerDetail(Model model, HttpServletRequest request, Locale local, @RequestParam(value = "module", required = false) String module) throws Exception {

		model.addAttribute("pageTitle", messageSource.getMessage("customer.title", null, local));

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.customerDetail +++ ");

		String custId = request.getParameter("id");
		String custType = request.getParameter("type");

		logger.info("custId:" + custId + " custType:" + custType);

		return getCustomerDetail(custId, custType, module, request, local);

	}

	private ModelAndView getCustomerDetail(String custId, String custType, String module, HttpServletRequest request, Locale local) {
		ServiceResult<CustomerDTO> serviceResult = null;
		CustomerModelBean modelBean = new CustomerModelBean();
		String returnView = "";

		if (CUST_INDIVIDUAL.equals(custType)) {
			returnView = "customerIndividualDetail";
			modelBean.setType(CUST_INDIVIDUAL);
			serviceResult = customerService.searchCustomerDetailIndividual(new Integer(custId));
		} else if (CUST_CORPORATE.equals(custType)) {
			returnView = "customerCorporateDetail";
			modelBean.setType(CUST_CORPORATE);
			serviceResult = customerService.searchCustomerDetailCorporate(new Integer(custId));
		}
		// get customer detail

		if (serviceResult.isSuccess()) {
			CustomerDTO dto = serviceResult.getResult();
			// set modelBean
			if (CUST_INDIVIDUAL.equals(custType)) {
				modelBean.setIndId(String.valueOf(dto.getIndId()));
				modelBean.setCustHomePhone(dto.getHomePhone());
				modelBean.setCustHomePhoneExt(dto.getHomePhoneExt());
				modelBean.setCustTitle(dto.getTitleCd());
				modelBean.setCustFirstNameDtl(dto.getFirstName());
				modelBean.setCustLastNameDtl(dto.getLastName());
				modelBean.setCustMiddleName(dto.getMidName());
				modelBean.setCustCitizenId(dto.getCitizenId());
				
				
				modelBean.setCustMinistry(dto.getMinistry());
				modelBean.setCustDepartment(dto.getDepartment());
				modelBean.setCustDivisionDept(dto.getDivisionDept());
				modelBean.setCustPosition(dto.getPosition());
				modelBean.setCustProvinceId(dto.getProvinceId());
				

			} else if (CUST_CORPORATE.equals(custType)) {
				modelBean.setCorpId(String.valueOf(dto.getCorpId()));
				modelBean.setCustCompany(dto.getCompanyName());
				modelBean.setCustIndustryDtl(dto.getIndustryCd());
				modelBean.setCustRegistIdDtl(dto.getRegistrationId());
				modelBean.setCustTaxIdDtl(dto.getTaxId());
				modelBean.setCustAnnualRevenue(dto.getRevenueCd());
				modelBean.setCustUrl(dto.getUrl());
				modelBean.setCustNoOfEmp(dto.getCompanySizeCd());
			}

			modelBean.setCustId(String.valueOf(dto.getCustId()));
			modelBean.setCustCategory(dto.getTypeCd());
			modelBean.setCustAddressLine1(dto.getAddr1());
			modelBean.setCustCode(dto.getCustCode());
			modelBean.setCustOffice(dto.getWorkPhone());
			modelBean.setCustMobile(dto.getMobileNo());
			
			modelBean.setCustOfficeExt(dto.getWorkPhoneExt());
			modelBean.setCustMobileExt(dto.getMobileNoExt());
			
			modelBean.setCustFax(dto.getFaxPhone());
			modelBean.setCustAddressLine2(dto.getAddr2());
			modelBean.setCustSubArea(dto.getSubArea());
			modelBean.setCustArea(dto.getArea());
			modelBean.setCustEmail(dto.getEmail());
			modelBean.setCustProvince(dto.getProvince());
			modelBean.setCustOwnerDtl(dto.getOwnerName());
			modelBean.setCustOwnerId(String.valueOf(dto.getOwnerId()));
			modelBean.setCustPostal(dto.getPostalCode());
			modelBean.setCustStatusDtl(dto.getStatusCd());
			modelBean.setCustContactMethod(dto.getContactChannelCd());
			modelBean.setCustRemark(dto.getComment());
			modelBean.setCustCountry(dto.getCountryCd());
			if (BOOLEAN_Y.equals(dto.getNotCallYn())) {
				modelBean.setCustNotCall(true);
			} else {
				modelBean.setCustNotCall(false);
			}
			if (BOOLEAN_Y.equals(dto.getNotSmsYn())) {
				modelBean.setCustNotSms(true);
			} else {
				modelBean.setCustNotSms(false);
			}
			if (BOOLEAN_Y.equals(dto.getNotEmailYn())) {
				modelBean.setCustNotEmail(true);
			} else {
				modelBean.setCustNotEmail(false);
			}
			if (BOOLEAN_Y.equals(dto.getNotMailYn())) {
				modelBean.setCustNotMail(true);
			} else {
				modelBean.setCustNotMail(false);
			}
			modelBean.setMode(JLOWebConstant.MODE_UPDATE);

			if (!StringUtils.isEmpty(module)) {
				modelBean.setModule(module.toLowerCase());
			}

		} else {
			showWebMessage(request, JLOWebConstant.FAIL_CODE, JLOWebConstant.FAIL_DESC, messageSource.getMessage("customer.title", null, local));
		}

		return new ModelAndView(returnView, CUST_MODEL, modelBean);
	}

	@RequestMapping(value = "/getCustList", headers = { "Accept=application/json" })
	public @ResponseBody
	String getCustList(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.getCustList +++");

		// criteria
		String custType = request.getParameter("custType");
		String custFullName = request.getParameter("custFullName");
		String custIndustry = request.getParameter("custIndustry");
		String custOwner = request.getParameter("custOwnerId");
		String custFirstName = request.getParameter("custFirstName");
		String custRegistId = request.getParameter("custRegistId");
		String custStatus = request.getParameter("custStatus");
		String custLastName = request.getParameter("custLastName");
		String custTaxId = request.getParameter("custTaxId");
		String custMobile =  request.getParameter("custMobile");
		String custOffice =  request.getParameter("custOffice");
		String custCitizenId = request.getParameter("custCitizenId");
		String custJobTitle = request.getParameter("custJobTitle");

		DatatableModelBean datatableModelBean = new DatatableModelBean();

		// Get owner filter
		List<String> ownerFilter = getOwnerGroup(request, ActionType.READ.getActionCode());
		// logger.info("ownerFilter : " + ownerFilter);

		if (ownerFilter != null) {

			CustomerCriteria criteria = new CustomerCriteria();
			criteria.setOwnerGroup(ownerFilter);

			if (CUST_INDIVIDUAL.equals(custType) || CUST_CORPORATE.equals(custType))
				criteria.setCustType(custType);

			if (StringUtils.isNotEmpty(custFullName)) {
				criteria.setCustFullName(custFullName);
			}

			if (StringUtils.isNotEmpty(custIndustry)) {
				criteria.setCustIndustry(custIndustry);
			}

			if (StringUtils.isNotEmpty(custOwner)) {
				criteria.setCustOwner(custOwner);
			}

			if (StringUtils.isNotEmpty(custFirstName)) {
				criteria.setCustFirstName(custFirstName);
			}

			if (StringUtils.isNotEmpty(custRegistId)) {
				criteria.setCustRegistId(custRegistId);
			}

			if (StringUtils.isNotEmpty(custStatus)) {
				criteria.setCustStatus(custStatus);
			}

			if (StringUtils.isNotEmpty(custLastName)) {
				criteria.setCustLastName(custLastName);
			}

			if (StringUtils.isNotEmpty(custTaxId)) {
				criteria.setCustTaxId(custTaxId);
			}
			
			if (StringUtils.isNotEmpty(custMobile)) {
				criteria.setCustMobile(StringUtils.formatPhoneDB(custMobile));
			}
			
			if (StringUtils.isNotEmpty(custOffice)) {
				criteria.setCustOffice(StringUtils.formatPhoneDB(custOffice));
			}
			

			if (StringUtils.isNotEmpty(custCitizenId)) {
				criteria.setCustCitizenId(StringUtils.formatPhoneDB(custCitizenId));
			}

			if (StringUtils.isNotEmpty(custJobTitle)) {
				criteria.setCustJobTitle(custJobTitle);
			}

			String sEchoTxt = request.getParameter("sEcho");
			Integer sEcho = (!StringUtils.isNullOrEmpty(sEchoTxt)) ? Integer.valueOf(sEchoTxt) : 0;

			Pageable pageable = getPagableFromRequest(request);

			ServiceResult<Page<CustomerDTO>> serviceResult = customerService.searchCustomer(pageable, criteria, getLanguageCode(locale));
			if (serviceResult.isSuccess()) {
				Page<CustomerDTO> result = serviceResult.getResult();
				Integer totalRecords = (int) (long) result.getTotalElements();

				// Visibility
				List<CustomerDTO> customerDTOList = result.getContent();
				if (customerDTOList != null && customerDTOList.size() > 0) {
					for (CustomerDTO customerDTO : customerDTOList) {
						
						if (checkVisibility(request, ActionType.EDIT.getActionCode(), customerDTO.getOwnerId())) {
							StringBuffer url = new StringBuffer();
							url.append("<a href='#' onclick='postAction(\"/customerDetail.htm?id=" + customerDTO.getCustId() + "&type=" + customerDTO.getCustTypeCd() + "\")'>");
							url.append("<i class='fa fa-pencil'></i>");
							url.append("</a>");
							customerDTO.setEditUrl(url.toString());
						} else {
							customerDTO.setEditUrl("");
						}
					}
				}

				// Set server prop return result
				datatableModelBean.setsEcho(sEcho);
				datatableModelBean.setiTotalDisplayRecords(totalRecords);
				datatableModelBean.setiTotalRecords(totalRecords);
				datatableModelBean.setAaData(result.getContent());
			} else {
				showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription(), messageSource.getMessage("customer.title", null, locale));
			}
		}

		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);
	}

	@RequestMapping(value = "/getContactList", headers = { "Accept=application/json" })
	public @ResponseBody
	String getContactList(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.getCustList +++");

		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean datatableModelBean = new DatatableModelBean();

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String mobile = request.getParameter("mobile");

		ContactCriteria contactCriteria = new ContactCriteria();
		contactCriteria.setLangCd(getLanguageCode(locale));

		if (StringUtils.isNotEmpty(firstName)) {
			contactCriteria.setFirstName(firstName);
		}

		if (StringUtils.isNotEmpty(lastName)) {
			contactCriteria.setLastName(lastName);
		}

		if (StringUtils.isNotEmpty(mobile)) {
			contactCriteria.setMobileNo(mobile);
		}

		ServiceResult<Page<ContactDTO>> serviceResult = customerService.searchContactList(pageable, contactCriteria);
		if (serviceResult.isSuccess()) {
			Page<ContactDTO> result = serviceResult.getResult();
			Integer totalRecords = (int) (long) result.getTotalElements();

			// Set server prop return result
			datatableModelBean.setsEcho(getSecho(request));
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(result.getContent());
		} else {
			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription(), messageSource.getMessage("customer.title", null, locale));
		}

		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);
	}

	@RequestMapping(value = "/getAddressList", headers = { "Accept=application/json" })
	public @ResponseBody
	String getAddressList(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.getAddressList +++");

		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean datatableModelBean = new DatatableModelBean();

		String addr = request.getParameter("addr");
		String subDistrict = request.getParameter("subDistrict");
		String district = request.getParameter("district");
		String province = request.getParameter("province");
		String postalCode = request.getParameter("postalCode");
		String country = request.getParameter("country");

		AddressCriteria addressCriteria = new AddressCriteria();
		if (StringUtils.isNotEmpty(addr)) {
			addressCriteria.setAddr(addr);
		}

		if (StringUtils.isNotEmpty(subDistrict)) {
			addressCriteria.setSubArea(subDistrict);
		}

		if (StringUtils.isNotEmpty(district)) {
			addressCriteria.setArea(district);
		}

		if (StringUtils.isNotEmpty(province)) {
			addressCriteria.setProvince(province);
		}

		if (StringUtils.isNotEmpty(postalCode)) {
			addressCriteria.setPostalCode(postalCode);
		}

		if (StringUtils.isNotEmpty(country)) {
			addressCriteria.setCountryCd(country);
		}

		ServiceResult<Page<AddressDTO>> serviceResult = customerService.searchAddressList(pageable, addressCriteria, getLanguageCode(locale));
		if (serviceResult.isSuccess()) {
			Page<AddressDTO> result = serviceResult.getResult();
			Integer totalRecords = (int) (long) result.getTotalElements();

			// Set server prop return result
			datatableModelBean.setsEcho(getSecho(request));
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(result.getContent());
		} else {
			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription(), messageSource.getMessage("customer.title", null, locale));
		}

		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);
	}

	@RequestMapping(value = "/getCustContactList", headers = { "Accept=application/json" })
	public @ResponseBody
	String getCustContactList(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.getCustContactList +++");

		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean datatableModelBean = new DatatableModelBean();

		String custId = request.getParameter("custId");
		String langCd = getLanguageCode(locale);
		
		List<ContactModelBean> conListModelBean = new ArrayList<ContactModelBean>();
		ContactModelBean contactModelBean = new  ContactModelBean();
		
		
		ServiceResult<Page<ContactDTO>> serviceResult = customerService.searchCustomerContactList(pageable, Integer.valueOf(custId), langCd);
		if (serviceResult.isSuccess()) {
			Page<ContactDTO> result = serviceResult.getResult();
			Integer totalRecords = (int) (long) result.getTotalElements();
			 
			for(ContactDTO dto : result.getContent()){
				contactModelBean = new  ContactModelBean();
				contactModelBean.setCustId(dto.getCustId());
				contactModelBean.setContId(dto.getContId());
				contactModelBean.setRelationName(dto.getRelationName());
				contactModelBean.setRelationCd(dto.getRelationCd());
				contactModelBean.setFirstName(dto.getFirstName());
				contactModelBean.setLastName(dto.getLastName());
				contactModelBean.setMobileNo(dto.getMobileNo());
				contactModelBean.setHomePhone(dto.getHomePhone());
				contactModelBean.setMobileNo(dto.getMobileNo());
				contactModelBean.setEmail(dto.getEmail());
				contactModelBean.setRegId(dto.getRegId());
				contactModelBean.setChgId(dto.getChgId());
				
				if (!StringUtils.isEmpty(String.valueOf(dto.getRegId()))) {
					if (checkVisibility(request, ActionType.EDIT.getActionCode(), Integer.valueOf(dto.getRegId()))) {
						contactModelBean.setEditUrl("EDIT");
					} else {
						contactModelBean.setEditUrl("");
					}
				} else {
					contactModelBean.setEditUrl("");
				}
				
				
				if (!StringUtils.isEmpty(String.valueOf(dto.getRegId()))) {
					if (checkVisibility(request, ActionType.DELETE.getActionCode(), Integer.valueOf(dto.getRegId()))) {
						contactModelBean.setDeleteUrl("DELETE");
					} else {
						contactModelBean.setDeleteUrl("");
					}
				} else {
					contactModelBean.setDeleteUrl("");
				}
				
				conListModelBean.add(contactModelBean);
			}

			// Set server prop return result
			datatableModelBean.setsEcho(getSecho(request));
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(conListModelBean);
			
		} else {
			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription(), messageSource.getMessage("customer.title", null, locale));
		}

		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);
	}

	@RequestMapping(value = "/getCustAddressList", headers = { "Accept=application/json" })
	public @ResponseBody
	String getCustAddressList(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.getCustAddressList +++");

		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean datatableModelBean = new DatatableModelBean();
		String custId = request.getParameter("custId");
		String langCd = getLanguageCode(locale);
	
		
		List<AddressModelBean> addrListModelBean = new ArrayList<AddressModelBean>();
		AddressModelBean addrModelBean = new  AddressModelBean();
		
		ServiceResult<Page<AddressDTO>> serviceResult = customerService.searchCustomerAddressList(pageable, Integer.valueOf(custId), langCd);
		if (serviceResult.isSuccess()) {
			Page<AddressDTO> result = serviceResult.getResult();
			Integer totalRecords = (int) (long) result.getTotalElements();
			for(AddressDTO dto : result.getContent()){
				
				addrModelBean = new  AddressModelBean();
				 
				 addrModelBean.setAddrId(dto.getAddrId());
				 addrModelBean.setAddr1(dto.getAddr1());
				 addrModelBean.setAddr2(dto.getAddr2());
				 addrModelBean.setSubArea(dto.getSubArea());
				 addrModelBean.setArea(dto.getArea());
				 addrModelBean.setProvince(dto.getProvince());
				 addrModelBean.setCountryCd(dto.getCountryCd());
				 addrModelBean.setCountryName(dto.getCountryName());
				 addrModelBean.setPostalCode(dto.getPostalCode());
				 addrModelBean.setAddrTypeCd(dto.getAddrTypeCd());
				 addrModelBean.setAddrTypeName(dto.getAddrTypeName());
				 addrModelBean.setRegId(dto.getRegId());
				 addrModelBean.setChgId(dto.getChgId());
				 addrModelBean.setPrimaryYn(dto.getPrimaryYn());
				 addrModelBean.setCustId(dto.getCustId());
				 
				 if (!StringUtils.isEmpty(String.valueOf(dto.getRegId()))) {
						if (checkVisibility(request, ActionType.EDIT.getActionCode(), Integer.valueOf(dto.getRegId()))) {
							addrModelBean.setEditUrl("EDIT");
						} else {
							addrModelBean.setEditUrl("");
						}
					} else {
						addrModelBean.setEditUrl("");
					}
					
					
					if (!StringUtils.isEmpty(String.valueOf(dto.getRegId()))) {
						if (checkVisibility(request, ActionType.DELETE.getActionCode(), Integer.valueOf(dto.getRegId()))) {
							addrModelBean.setDeleteUrl("DELETE");
						} else {
							addrModelBean.setDeleteUrl("");
						}
					} else {
						addrModelBean.setDeleteUrl("");
					}
				 
				 
				 addrListModelBean.add(addrModelBean);
			
			}
			
	 
			
			
			// Set server prop return result
			datatableModelBean.setsEcho(getSecho(request));
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(addrListModelBean);
		} else {
			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription(), messageSource.getMessage("customer.title", null, locale));
		}

		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);
	}

	@RequestMapping(value = "/customerDetailTab", method = RequestMethod.GET)
	public ModelAndView customerDetailTab(Model model, HttpServletRequest request, Locale locale, @RequestParam(value = "tab", required = true) String tab) {

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.customerDetailTab +++");

		CustomerModelBean modelBean = new CustomerModelBean();
		String returnPage = "";

		// get param
		String custId = request.getParameter("id");
		String mode = request.getParameter("mode");
		modelBean.setMode(mode);

		logger.info("tab select:" + tab);
		if (tab != null) {
			if (TAB_MOREINFO.equals(tab)) {
				returnPage = "custDetailMoreInfo";
				if (modelBean.isUpdateMode()) {
					ServiceResult<CustomerDTO> serviceResult = customerService.searchMoreInfoIndividual(new Long(custId));
					if (serviceResult.isSuccess()) {
						CustomerDTO dto = serviceResult.getResult();
						modelBean.setCustId(String.valueOf(dto.getCustId()));
						modelBean.setIndId(String.valueOf(dto.getIndId()));
						modelBean.setCustGender(dto.getGenderCd());
						modelBean.setCustMarriageStatus(dto.getMaritalStatusCd());
						modelBean.setCustRace(dto.getRaceCd());
						modelBean.setCustNationality(dto.getNationalityCd());
						modelBean.setCustBirthDate(DateTimeUtils.formatDate(dto.getBirthDt()));
						modelBean.setCustCitizenIdTab(dto.getCitizenId());
						modelBean.setCustSegmentation(dto.getSegmentCd());
						modelBean.setCustEducation(dto.getEducationCd());
						modelBean.setCustSalary(dto.getIncomeCd());
						modelBean.setCustOccupation(dto.getOccupationCd());
						modelBean.setCustJobTitleTab(dto.getJobTitleCd());
					} else {
						showWebMessage(request, JLOWebConstant.FAIL_CODE, JLOWebConstant.FAIL_DESC, messageSource.getMessage("customer.title", null, locale));
					}
				}
			} else if (TAB_CONTACT.equals(tab)) {
				returnPage = "custDetailContact";
			} else if (TAB_ADDRESS.equals(tab)) {
				returnPage = "custDetailAddress";
			} else if (TAB_SR.equals(tab)) {
				returnPage = "custDetailSr";
			}
		}

		return new ModelAndView(returnPage, CUST_MODEL, modelBean);
	}

	@RequestMapping(value = "/getCustSrList", headers = { "Accept=application/json" })
	public @ResponseBody
	String getCustSrList(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.getCustSrList +++");

		Pageable pageable = getPagableFromRequest(request);
		DatatableModelBean datatableModelBean = new DatatableModelBean();

		String custId =	 StringUtils.isNotEmpty(request.getParameter("custId")) ? request.getParameter("custId") : "-1";
		String tabMenuId = request.getParameter("tabMenuId");
		String langCd = getLanguageCode(locale);
		logger.info("Cust Id : " + custId);

		ServiceResult<Page<CustomerServiceRequestDTO>> serviceResult = customerService.searchCustomerServiceRequestList(pageable, Integer.parseInt(custId), langCd);
		if (serviceResult.isSuccess()) {
			Page<CustomerServiceRequestDTO> result = serviceResult.getResult();
			Integer totalRecords = (int) (long) result.getTotalElements();

			// Visibility
			List<CustomerServiceRequestDTO> customerServiceRequestDTOList = result.getContent();
			if (CollectionUtil.isNotEmpty(customerServiceRequestDTOList)) {
				for (CustomerServiceRequestDTO dto : customerServiceRequestDTOList) {

					if (checkVisibility(request, ActionType.EDIT.getActionCode(), dto.getSrOwnerId(), tabMenuId)) {
						StringBuffer url = new StringBuffer();
						url.append("<a href='#' onclick='postAction(\"/serviceRequestDetail.htm?module=customer&mode=update&sr_number=" + dto.getSrNumber() + "&custId=" + custId + "&custType="
								+ dto.getCustType() + "&custName=" + dto.getCustName() + "\")'>");
						url.append("<i class='fa fa-pencil'></i>");
						url.append("</a>");
						dto.setEditUrl(url.toString());
					} else {
						dto.setEditUrl("");
					}
				}
			}

			// Set server prop return result
			datatableModelBean.setsEcho(getSecho(request));
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(result.getContent());
		} else {
			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription(), messageSource.getMessage("customer.title", null, locale));
		}

		return JsonUtil.toJSON(datatableModelBean, Boolean.TRUE);
	}

	@RequestMapping(value = "/initCustomer", method = RequestMethod.GET)
	public ModelAndView initCustomer(Model model, HttpServletRequest request, Locale locale) {

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.initCustomer +++");

		model.addAttribute("pageTitle", messageSource.getMessage("customer.title", null, locale));
		
		CustomerModelBean modelBean = new CustomerModelBean();
		String returnView = "";
		Integer userId =  getUserId(request);
		String fName = StringUtils.isNullOrEmpty(getUserInfo(request).getFirstName()) ? " " :  getUserInfo(request).getFirstName();
		String lName = StringUtils.isNullOrEmpty(getUserInfo(request).getLastName()) ? " " : getUserInfo(request).getLastName() ;
		String ownerName  = fName + " " + lName;
		
		String custStatus = "A";
		
		String custType = request.getParameter("type");
		if (CUST_INDIVIDUAL.equals(custType)) {
			returnView = "customerIndividualDetail";
			modelBean.setType(CUST_INDIVIDUAL);
			modelBean.setCustStatusDtl(custStatus);
			modelBean.setCustCategory(CUST_INDIVIDUAL);
			
			modelBean.setCustOwnerId(String.valueOf(userId));
			modelBean.setCustOwnerDtl(ownerName);
			
		} else if (CUST_CORPORATE.equals(custType)) {
			returnView = "customerCorporateDetail";
			modelBean.setType(CUST_CORPORATE);
			modelBean.setCustCategory(CUST_CORPORATE);
			modelBean.setCustStatusDtl(custStatus);
			modelBean.setCustOwnerId(String.valueOf(userId));
			modelBean.setCustOwnerDtl(ownerName);
		}
		
		modelBean.setMode(JLOWebConstant.MODE_INSERT);
		return new ModelAndView(returnView, CUST_MODEL, modelBean);
	}

	@RequestMapping(value = "/saveIndividual", method = RequestMethod.GET)
	public ModelAndView saveIndividualGet(@ModelAttribute(CUST_MODEL) CustomerModelBean modelBean, Model model, HttpServletRequest request, Locale locale) {
		// Prevent Post-Redirect
		return new ModelAndView("customerIndividualDetail", CUST_MODEL, modelBean);
	}

	@RequestMapping(value = "/saveIndividual", method = RequestMethod.POST)
	public ModelAndView saveIndividual(@ModelAttribute(CUST_MODEL) CustomerModelBean modelBean, Model model, HttpServletRequest request, Locale locale) {
			
		logger.info("*****************************************************");
		logger.info("+++ CustomerController.saveCustomer +++");
		model.addAttribute("pageTitle", messageSource.getMessage("customer.title", null, locale));
		
		String custType = modelBean.getType();
		modelBean.setCustCategory(custType);
		logger.info("[[ isUpdateMode ]]" + modelBean.isUpdateMode());
		logger.info("[[ isInsertMode ]]" + modelBean.isInsertMode());

		CustomerDTO dto = new CustomerDTO();

		// TP_CUST_MASTER
		dto.setCorpId(null);
		dto.setCustCode(modelBean.getCustCode());
		//String custName = modelBean.getCustFirstNameDtl() + " " + modelBean.getCustMiddleName() + " " + modelBean.getCustLastNameDtl();
		
		StringBuilder strFullName = new  StringBuilder("");

		if(StringUtils.isNotEmpty(modelBean.getCustFirstNameDtl())){
			strFullName.append(StringUtils.trim(modelBean.getCustFirstNameDtl()));
		}
		
		if(StringUtils.isNotEmpty(modelBean.getCustMiddleName())){
			strFullName.append(" ");
			strFullName.append(StringUtils.trim(modelBean.getCustMiddleName()));
		}
		
		if(StringUtils.isNotEmpty(modelBean.getCustLastNameDtl())){
			strFullName.append(" ");
			strFullName.append(StringUtils.trim(modelBean.getCustLastNameDtl()));
		}
		
		String custName = strFullName.toString();
		
		dto.setCustName(custName);
		dto.setTypeCd(custType);
		dto.setStatusCd(modelBean.getCustStatusDtl());
		dto.setContactChannelCd(modelBean.getCustContactMethod());
		
		if (StringUtils.isNotEmpty(modelBean.getCustOwnerId()) && !"null".equals(modelBean.getCustOwnerId())) {
			dto.setOwnerId(new Integer(modelBean.getCustOwnerId()));
		} else {
			dto.setOwnerId(null);
		}
		dto.setComment(modelBean.getCustRemark());
		if (modelBean.isCustNotCall()) {
			dto.setNotCallYn(BOOLEAN_Y);
		} else {
			dto.setNotCallYn(BOOLEAN_N);
		}
		if (modelBean.isCustNotSms()) {
			dto.setNotSmsYn(BOOLEAN_Y);
		} else {
			dto.setNotSmsYn(BOOLEAN_N);
		}
		if (modelBean.isCustNotEmail()) {
			dto.setNotEmailYn(BOOLEAN_Y);
		} else {
			dto.setNotEmailYn(BOOLEAN_N);
		}
		if (modelBean.isCustNotMail()) {
			dto.setNotMailYn(BOOLEAN_Y);
		} else {
			dto.setNotMailYn(BOOLEAN_N);
		}

		// TP_INDIVIDUAL
		dto.setTitleCd(modelBean.getCustTitle());
		dto.setFirstName(modelBean.getCustFirstNameDtl());
		dto.setMidName(modelBean.getCustMiddleName());
		dto.setLastName(modelBean.getCustLastNameDtl());
		dto.setMobileNo(StringUtils.formatPhoneDB(modelBean.getCustMobile()));
		dto.setHomePhone(StringUtils.formatPhoneDB(modelBean.getCustHomePhone()));
		dto.setWorkPhone(StringUtils.formatPhoneDB(modelBean.getCustOffice()));
		
		dto.setMobileNoExt(modelBean.getCustMobileExt());
		dto.setHomePhoneExt(modelBean.getCustHomePhoneExt());
		dto.setWorkPhoneExt(modelBean.getCustOfficeExt());
		
		dto.setCitizenId(StringUtils.formatPhoneDB(modelBean.getCustCitizenId()));
		
		dto.setFaxPhone(StringUtils.formatPhoneDB(modelBean.getCustFax()));
		dto.setAddr1(modelBean.getCustAddressLine1());
		dto.setAddr2(modelBean.getCustAddressLine2());
		dto.setSubArea(modelBean.getCustSubArea());
		dto.setArea(modelBean.getCustArea());
		dto.setProvince(modelBean.getCustProvince());
		dto.setCountryCd(modelBean.getCustCountry());
		dto.setPostalCode(modelBean.getCustPostal());
		dto.setEmail(modelBean.getCustEmail());
		
		dto.setMinistry(modelBean.getCustMinistry());
		dto.setDepartment(modelBean.getCustDepartment());
		dto.setDivisionDept(modelBean.getCustDivisionDept());
		dto.setPosition(modelBean.getCustPosition());
		dto.setProvinceId(modelBean.getCustProvinceId());
		
		
		if (modelBean.isUpdateMode()) {
			// update
			logger.info("[[ custId ]]" + modelBean.getCustId());
			logger.info("[[ indId ]]" + modelBean.getIndId());
			dto.setCustId(new Long(modelBean.getCustId()));
			dto.setIndId(new Long(modelBean.getIndId()));
			dto.setChgId(getUserId(request));
			dto.setChgDt(new Date());

			ServiceResult<Integer> result = customerService.updateIndividual(dto);
			if (result.isSuccess() && result.getResult() > 0) {
				showWebMessage(request, JLOWebConstant.UPDATE_SUCCESS_CODE, messageSource.getMessage("lbl.save.success", null, locale), messageSource.getMessage("cust.head.individual", null, locale));
			} else {
				showWebMessage(request, JLOWebConstant.UPDATE_FAIL_CODE, messageSource.getMessage("lbl.save.fail", null, locale), messageSource.getMessage("cust.head.individual", null, locale));
			}

		} else if (modelBean.isInsertMode()) {
			// insert
			dto.setRegId(getUserId(request));
			dto.setRegDt(new Date());

			ServiceResult<CustomerDTO> result = customerService.insertIndividual(dto);
			if (result.isSuccess()) {
				dto = result.getResult();
				modelBean.setCustId(String.valueOf(dto.getCustId()));
				modelBean.setIndId(String.valueOf(dto.getIndId()));
				modelBean.setMode(JLOWebConstant.MODE_UPDATE);
				showWebMessage(request, JLOWebConstant.INSERT_SUCCESS_CODE, messageSource.getMessage("lbl.save.success", null, locale), messageSource.getMessage("cust.head.individual", null, locale));
			} else {
				showWebMessage(request, JLOWebConstant.INSERT_FAIL_CODE, messageSource.getMessage("lbl.save.fail", null, locale), messageSource.getMessage("cust.head.individual", null, locale));
			}
		}

		return new ModelAndView("customerIndividualDetail", CUST_MODEL, modelBean);
	}

	@RequestMapping(value = "/saveMoreInfo", method = RequestMethod.POST)
	public @ResponseBody
	String saveMoreInfo(@ModelAttribute(CUST_MODEL) CustomerModelBean modelBean, Model model, HttpServletRequest request, Locale locale) {

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.saveMoreInfo +++");

		CustomerDTO dto = new CustomerDTO();
		logger.info("[[ cust_id ]]" + modelBean.getCustId());
		logger.info("[[ ind_id ]]" + modelBean.getIndId());
		dto.setCustId(new Long(modelBean.getCustId()));
		dto.setIndId(new Long(modelBean.getIndId()));
		dto.setGenderCd(modelBean.getCustGender());
		dto.setMaritalStatusCd(modelBean.getCustMarriageStatus());
		dto.setRaceCd(modelBean.getCustRace());
		dto.setNationalityCd(modelBean.getCustNationality());
		if (StringUtils.isNotEmpty(modelBean.getCustBirthDate())) {
			dto.setBirthDt(DateTimeUtils.parseDate(modelBean.getCustBirthDate(), DateTimeUtils.DATE_FORMAT));
		}
		
		// TODO CityzenId
		//dto.setCitizenId(StringUtils.formatPhoneDB(modelBean.getCustCitizenIdTab()));
		
		dto.setSegmentCd(modelBean.getCustSegmentation());
		dto.setEducationCd(modelBean.getCustEducation());
		dto.setIncomeCd(modelBean.getCustSalary());
		dto.setOccupationCd(modelBean.getCustOccupation());
		dto.setJobTitleCd(modelBean.getCustJobTitleTab());
		dto.setChgId(getUserId(request));
		dto.setChgDt(new Date());

		JsonResultBean result = new JsonResultBean();
		ServiceResult<Integer> serviceResult = customerService.updateMoreInfo(dto);
		if (serviceResult.isSuccess()) {
			modelBean.setMode(JLOWebConstant.MODE_UPDATE);
			result.setResultCode(JLOWebConstant.UPDATE_SUCCESS_CODE);
			result.setResultMessage(JLOWebConstant.UPDATE_SUCCESS_DESC);
			result.setModel(modelBean);
		} else {
			result.setResultCode(JLOWebConstant.UPDATE_FAIL_CODE);
			result.setResultMessage(JLOWebConstant.UPDATE_FAIL_DESC);
		}

		return JsonUtil.toJSON(result, Boolean.TRUE);
	}

	@RequestMapping(value = "/saveCorporation", method = RequestMethod.POST)
	public ModelAndView saveCorporation(@ModelAttribute(CUST_MODEL) CustomerModelBean modelBean, Model model, HttpServletRequest request, Locale locale) {

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.saveCorporation +++");

		logger.info("[[ isUpdateMode ]]" + modelBean.isUpdateMode());
		logger.info("[[ isInsertMode ]]" + modelBean.isInsertMode());
		model.addAttribute("pageTitle", messageSource.getMessage("customer.title", null, locale));
		CustomerDTO dto = new CustomerDTO();
		String custType = modelBean.getType();
		dto.setTypeCd(custType);
		modelBean.setCustCategory(custType);
		dto.setCustCode(modelBean.getCustCode());
		dto.setCompanyName(modelBean.getCustCompany());
		dto.setCustName(modelBean.getCustCompany());
		dto.setIndustryCd(modelBean.getCustIndustryDtl());
		dto.setStatusCd(modelBean.getCustStatusDtl());
		dto.setContactChannelCd(modelBean.getCustContactMethod());
		dto.setWorkPhone(StringUtils.formatPhoneDB(modelBean.getCustOffice()));
		dto.setWorkPhoneExt(modelBean.getCustOfficeExt());
		dto.setFaxPhone(StringUtils.formatPhoneDB(modelBean.getCustFax()));
		if (StringUtils.isNotEmpty(modelBean.getCustOwnerId()) && !"null".equals(modelBean.getCustOwnerId())) {
			dto.setOwnerId(new Integer(modelBean.getCustOwnerId()));
		} else {
			dto.setOwnerId(null);
		}
		dto.setEmail(modelBean.getCustEmail());
		dto.setUrl(modelBean.getCustUrl());
		dto.setAddr1(modelBean.getCustAddressLine1());
		dto.setAddr2(modelBean.getCustAddressLine2());
		dto.setSubArea(modelBean.getCustSubArea());
		dto.setArea(modelBean.getCustArea());
		dto.setProvince(modelBean.getCustProvince());
		dto.setPostalCode(modelBean.getCustPostal());
		dto.setCountryCd(modelBean.getCustCountry());
		dto.setRegistrationId(modelBean.getCustRegistIdDtl());
		logger.info("Tax " + modelBean.getCustTaxIdDtl());
		logger.info("Tax " + StringUtils.removeMaskEmpty(modelBean.getCustTaxIdDtl()));
		dto.setTaxId(StringUtils.removeMaskEmpty(modelBean.getCustTaxIdDtl()));
		dto.setRevenueCd(modelBean.getCustAnnualRevenue());
		dto.setCompanySizeCd(modelBean.getCustNoOfEmp());
		dto.setComment(modelBean.getCustRemark());

		if (modelBean.isCustNotCall()) {
			dto.setNotCallYn(BOOLEAN_Y);
		} else {
			dto.setNotCallYn(BOOLEAN_N);
		}
		if (modelBean.isCustNotSms()) {
			dto.setNotSmsYn(BOOLEAN_Y);
		} else {
			dto.setNotSmsYn(BOOLEAN_N);
		}
		if (modelBean.isCustNotEmail()) {
			dto.setNotEmailYn(BOOLEAN_Y);
		} else {
			dto.setNotEmailYn(BOOLEAN_N);
		}
		if (modelBean.isCustNotMail()) {
			dto.setNotMailYn(BOOLEAN_Y);
		} else {
			dto.setNotMailYn(BOOLEAN_N);
		}

		if (modelBean.isUpdateMode()) {
			// update
			logger.info("[[ corpId ]]" + modelBean.getCorpId());
			logger.info("[[ custId ]]" + modelBean.getCustId());

			dto.setChgId(getUserId(request));
			dto.setChgDt(new Date());
			dto.setCorpId(new Long(modelBean.getCorpId()));
			dto.setCustId(new Long(modelBean.getCustId()));

			ServiceResult<Integer> result = customerService.updateCorporation(dto);
			if (result.isSuccess() && result.getResult() > 0) {
				showWebMessage(request, JLOWebConstant.UPDATE_SUCCESS_CODE, messageSource.getMessage("lbl.save.success", null, locale), messageSource.getMessage("cust.head.corporate", null, locale));
			} else {
				showWebMessage(request, JLOWebConstant.UPDATE_FAIL_CODE, messageSource.getMessage("lbl.save.fail", null, locale), messageSource.getMessage("cust.head.corporate", null, locale));
			}

		} else if (modelBean.isInsertMode()) {
			// insert
			dto.setRegId(getUserId(request));
			dto.setRegDt(new Date());

			ServiceResult<CustomerDTO> result = customerService.insertCorporation(dto);
			if (result.isSuccess()) {
				dto = result.getResult();
				modelBean.setCustId(String.valueOf(dto.getCustId()));
				modelBean.setCorpId(String.valueOf(dto.getCorpId()));
				modelBean.setMode(JLOWebConstant.MODE_UPDATE);
				showWebMessage(request, JLOWebConstant.INSERT_SUCCESS_CODE, messageSource.getMessage("lbl.save.success", null, locale), messageSource.getMessage("cust.head.corporate", null, locale));
			} else {
				showWebMessage(request, JLOWebConstant.INSERT_FAIL_CODE, messageSource.getMessage("lbl.save.fail", null, locale), messageSource.getMessage("cust.head.corporate", null, locale));
			}
		}
		return new ModelAndView("customerCorporateDetail", CUST_MODEL, modelBean);
	}

	@RequestMapping(value = "/createNewContact", headers = { "Accept=application/json" })
	public @ResponseBody
	String createNewContact(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("*****************************************************");
		logger.info("+++ CustomerController.createNewContact +++");
		JsonResultBean jsonResultBean = new JsonResultBean();

		// Set parameter to DTO
		ContactDTO contactDTO = new ContactDTO();
		setForm2ContactBean(request, contactDTO);

		if (contactDTO.getIndId() != null) {
			contactDTO.setChgId(getUserId(request));

			// Update
			if (customerService.updateCustomerContact(contactDTO)) {
				jsonResultBean.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("lbl.save.success", null, locale));
				jsonResultBean.setModel(contactDTO);
			} else {
				jsonResultBean.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("lbl.save.fail", null, locale));
			}
		} else {
			contactDTO.setRegId(getUserId(request));

			// Insert
			ServiceResult<ContactDTO> res = customerService.insertContact(contactDTO);
			if (res.isSuccess()) {
				jsonResultBean.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("lbl.save.success", null, locale));
				jsonResultBean.setModel(res.getResult());
			} else {
				jsonResultBean.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("lbl.save.fail", null, locale));
			}
		}

		return JsonUtil.toJSON(jsonResultBean, Boolean.TRUE);

	}

	@RequestMapping(value = "/deleteCustomerContact", headers = { "Accept=application/json" })
	public @ResponseBody
	String deleteCustomerContact(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("*****************************************************");
		logger.info("+++ CustomerController.deleteCustomerContact +++");

		String custId = request.getParameter("custId");
		String contId = request.getParameter("contId");
		logger.info("Delete Customer Id : " + custId + " Contact Id : " + contId);

		JsonResultBean jsonResultBean = new JsonResultBean();
		if (!StringUtils.isNullOrEmpty(custId) && !StringUtils.isNullOrEmpty(contId)) {

			if (customerService.deleteCustomerContact(Integer.parseInt(custId), Integer.parseInt(contId))) {
				jsonResultBean.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("lbl.delete.success", null, locale));
			} else {
				jsonResultBean.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("lbl.delete.fail", null, locale));
			}

		} else {
			jsonResultBean.setResultCode(JLOWebConstant.FAIL_CODE);
			jsonResultBean.setResultMessage("Key is required");
		}

		return JsonUtil.toJSON(jsonResultBean, Boolean.TRUE);

	}

	private void setForm2ContactBean(HttpServletRequest request, ContactDTO contactDTO) {
		String countryCd = request.getParameter("countryCd");
		String postalCode = request.getParameter("postalCode");
		String province = request.getParameter("province");
		String area = request.getParameter("area");
		String subArea = request.getParameter("subArea");
		String addr2 = request.getParameter("addr2");
		String addr1 = request.getParameter("addr1");
		String email = request.getParameter("email");
		String faxPhone = request.getParameter("faxPhone");
		String workPhone = request.getParameter("workPhone");
		String homePhone = request.getParameter("homePhone");
		String mobileNo = request.getParameter("mobileNo");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String relationCd = request.getParameter("relationCd");
		String indId = request.getParameter("indId");
		String custId = request.getParameter("custId");
		String contId = request.getParameter("contId");

		logger.info("addr1 : " + addr1);
		logger.info("countryCd : " + countryCd);
		logger.info("postalCode : " + postalCode);
		logger.info("province : " + province);
		logger.info("area : " + area);
		logger.info("subArea : " + subArea);
		logger.info("addr2 : " + addr2);
		logger.info("addr1 : " + addr1);
		logger.info("email : " + email);
		logger.info("faxPhone : " + faxPhone);
		logger.info("workPhone : " + workPhone);
		logger.info("homePhone : " + homePhone);
		logger.info("mobileNo : " + mobileNo);
		logger.info("lastName : " + lastName);
		logger.info("firstName : " + firstName);
		logger.info("custId : " + custId);

		contactDTO.setIndId(!(StringUtils.isNullOrEmpty(indId)) ? Integer.parseInt(indId) : null);
		contactDTO.setCustId(!(StringUtils.isNullOrEmpty(custId)) ? Integer.parseInt(custId) : null);
		contactDTO.setContId(!(StringUtils.isNullOrEmpty(contId)) ? Integer.parseInt(contId) : null);
		contactDTO.setAddr1(addr1);
		contactDTO.setAddr2(addr2);
		contactDTO.setArea(area);
		contactDTO.setCountryCd(countryCd);
		contactDTO.setEmail(email);
		contactDTO.setFaxPhone(StringUtils.formatPhoneDB(faxPhone));
		contactDTO.setFirstName(firstName);
		contactDTO.setHomePhone(StringUtils.formatPhoneDB(homePhone));
		contactDTO.setLastName(lastName);
		contactDTO.setMobileNo(StringUtils.formatPhoneDB(mobileNo));
		contactDTO.setPostalCode(postalCode);
		contactDTO.setProvince(province);
		contactDTO.setRelationCd(relationCd);
		contactDTO.setSubArea(subArea);
		contactDTO.setWorkPhone(StringUtils.formatPhoneDB(workPhone));
	}

	private void setForm2AddressBean(HttpServletRequest request, AddressDTO addressDTO) {
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String subArea = request.getParameter("subArea");
		String area = request.getParameter("area");
		String province = request.getParameter("province");
		String countryCd = request.getParameter("countryCd");
		String postalCode = request.getParameter("postalCode");
		String primaryYn = request.getParameter("primaryYn");
		String addrTypeCd = request.getParameter("addrTypeCd");
		String custId = request.getParameter("custId");
		String addrId = request.getParameter("addrId");

		logger.info("addr1 : " + addr1);
		logger.info("addr2 : " + addr2);
		logger.info("subArea : " + subArea);
		logger.info("area : " + area);
		logger.info("province : " + province);
		logger.info("countryCd : " + countryCd);
		logger.info("postalCode : " + postalCode);
		logger.info("primaryYn : " + primaryYn);
		logger.info("addrTypeCd : " + postalCode);
		logger.info("custId : " + custId);
		logger.info("addrId : " + addrId);

		addressDTO.setCustId(!(StringUtils.isNullOrEmpty(custId)) ? Integer.parseInt(custId) : null);
		addressDTO.setAddrId(!(StringUtils.isNullOrEmpty(addrId)) ? Integer.parseInt(addrId) : null);
		addressDTO.setAddr1(addr1);
		addressDTO.setAddr2(addr2);
		addressDTO.setArea(area);
		addressDTO.setSubArea(subArea);
		addressDTO.setProvince(province);
		addressDTO.setCountryCd(countryCd);
		addressDTO.setPostalCode(postalCode);
		addressDTO.setPrimaryYn(primaryYn);
		addressDTO.setAddrTypeCd(addrTypeCd);
	}

	@RequestMapping(value = "/saveCustomerContact", headers = { "Accept=application/json" })
	public @ResponseBody
	String updateCustomerContact(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("*****************************************************");
		logger.info("+++ CustomerController.updateCustomerContact +++");

		String custId = request.getParameter("custId");
		String contId = request.getParameter("contId");
		String relationCd = request.getParameter("relationCd");

		// Set parameter to DTO
		ContactDTO contactDTO = new ContactDTO();
		contactDTO.setContId(Integer.parseInt(contId));
		contactDTO.setCustId(Integer.parseInt(custId));
		contactDTO.setRelationCd(relationCd);
		contactDTO.setRegId(getUserId(request));

		JsonResultBean jsonResultBean = new JsonResultBean();
		ServiceResult<ContactDTO> res = customerService.insertCustomerContact(contactDTO);
		if (res.isSuccess()) {
			jsonResultBean.setResultCode(JLOWebConstant.SUCCESS_CODE);
			jsonResultBean.setResultMessage(messageSource.getMessage("lbl.save.success", null, locale));
		} else {
			jsonResultBean.setResultCode(JLOWebConstant.FAIL_CODE);
			jsonResultBean.setResultMessage(messageSource.getMessage("lbl.save.fail", null, locale));
		}

		return JsonUtil.toJSON(jsonResultBean, Boolean.TRUE);
	}

	@RequestMapping(value = "/checkDuplicateContact", headers = { "Accept=application/json" })
	public @ResponseBody
	String checkDuplicateContact(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("*****************************************************");
		logger.info("+++ CustomerController.checkDuplicateContact +++");

		String custId = request.getParameter("custId");
		String contId = request.getParameter("contId");
		int res = customerService.countCustomerContact(Integer.parseInt(custId), Integer.parseInt(contId));
		if (res > 0) {
			return JsonUtil.toJSON(Boolean.TRUE, Boolean.TRUE);
		} else {
			return JsonUtil.toJSON(Boolean.FALSE, Boolean.TRUE);
		}

	}

	@RequestMapping(value = "/contactDialogMain")
	public ModelAndView contactDialogMain(Model model, Locale local, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageModalName", required = true) String pageModalName, @RequestParam(value = "modalHeaderCode", required = true) String modalHeaderCaption,
			@RequestParam(value = "callbackfn", required = false) String callbackfn, @RequestParam(value = "strParam", required = false) String strParam) {

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.contactDialogMain +++ ");

		model.addAttribute("headerTitle", messageSource.getMessage(modalHeaderCaption, null, local));
		model.addAttribute("callbackfn", callbackfn);
		ContactDTO contactDTO = null;

		logger.info("Param : " + strParam);
		String custId = "";
		String contId = "";
		String[] param = strParam.split(",");
		if (param.length > 1) {
			custId = param[0];
			contId = param[1];
		} else {
			custId = param[0];
		}

		logger.info("custId : " + custId + " , contId : " + contId);

		if (!StringUtils.isNullOrEmpty(contId)) {
			ServiceResult<ContactDTO> res = customerService.searchContactById(Integer.parseInt(custId), Integer.parseInt(contId));
			if (res.isSuccess()) {
				contactDTO = res.getResult();

			} else {
				contactDTO = new ContactDTO();
			}
		} else {
			contactDTO = new ContactDTO();
			contactDTO.setCustId(Integer.parseInt(custId));
		}

		return new ModelAndView(pageModalName, CONT_MODEL, contactDTO);
	}

	@RequestMapping(value = "/addressDialogMain")
	public ModelAndView addressDialogMain(Model model, Locale local, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageModalName", required = true) String pageModalName, @RequestParam(value = "modalHeaderCode", required = true) String modalHeaderCaption,
			@RequestParam(value = "callbackfn", required = false) String callbackfn, @RequestParam(value = "strParam", required = false) String strParam) {

		logger.info("*****************************************************");
		logger.info("+++ CustomerController.addressDialogMain +++ ");

		model.addAttribute("headerTitle", messageSource.getMessage(modalHeaderCaption, null, local));
		model.addAttribute("callbackfn", callbackfn);
		AddressDTO addressDTO = null;

		logger.info("Param : " + strParam);
		String custId = "";
		String addrId = "";
		String[] param = strParam.split(",");
		if (param.length > 1) {
			custId = param[0];
			addrId = param[1];
		} else {
			custId = param[0];
		}

		logger.info("custId : " + custId + " , addrId : " + addrId);

		if (!StringUtils.isNullOrEmpty(addrId)) {

			ServiceResult<AddressDTO> res = customerService.searchAddressById(Integer.parseInt(custId), Integer.parseInt(addrId));
			if (res.isSuccess()) {
				addressDTO = res.getResult();
				addressDTO.setCustId(Integer.parseInt(custId));

			} else {
				addressDTO = new AddressDTO();
			}
		} else {
			addressDTO = new AddressDTO();
			addressDTO.setCustId(Integer.parseInt(custId));
		}

		return new ModelAndView(pageModalName, ADDR_MODEL, addressDTO);
	}

	@RequestMapping(value = "/createNewAddress", headers = { "Accept=application/json" })
	public @ResponseBody
	String createNewAddress(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("*****************************************************");
		logger.info("+++ CustomerController.createNewAddress +++");
		JsonResultBean jsonResultBean = new JsonResultBean();

		// Set parameter to DTO
		AddressDTO addressDTO = new AddressDTO();
		setForm2AddressBean(request, addressDTO);

		if (addressDTO.getAddrId() != null) {
			addressDTO.setChgId(getUserId(request));

			// Update
			if (customerService.updateCustomerAddress(addressDTO)) {
				jsonResultBean.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("lbl.save.success", null, locale));
				jsonResultBean.setModel(addressDTO);
			} else {
				jsonResultBean.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("lbl.save.fail", null, locale));
			}
		} else {
			addressDTO.setRegId(getUserId(request));

			// Insert
			ServiceResult<AddressDTO> res = customerService.insertAddress(addressDTO);
			if (res.isSuccess()) {
				jsonResultBean.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("lbl.save.success", null, locale));
				jsonResultBean.setModel(res.getResult());
			} else {
				jsonResultBean.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("lbl.save.fail", null, locale));
			}
		}

		return JsonUtil.toJSON(jsonResultBean, Boolean.TRUE);

	}

	@RequestMapping(value = "/saveCustomerAddress", headers = { "Accept=application/json" })
	public @ResponseBody
	String saveCustomerAddress(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("*****************************************************");
		logger.info("+++ CustomerController.saveCustomerAddress +++");
		JsonResultBean jsonResultBean = new JsonResultBean();

		String custId = request.getParameter("custId");
		String addrId = request.getParameter("addrId");

		if (StringUtils.isNullOrEmpty(custId) || StringUtils.isNullOrEmpty(addrId)) {
			jsonResultBean.setResultCode(JLOWebConstant.FAIL_CODE);
			jsonResultBean.setResultMessage(messageSource.getMessage("cust.message.result.keyIsNull", null, locale));
		} else {
			AddressDTO addressDTO = new AddressDTO();
			addressDTO.setCustId(Integer.parseInt(custId));
			addressDTO.setAddrId(Integer.parseInt(addrId));
			addressDTO.setRegId(getUserId(request));

			ServiceResult<AddressDTO> serviceResult = customerService.insertCustomerAddress(addressDTO);
			if (serviceResult.isSuccess()) {
				jsonResultBean.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("lbl.save.success", null, locale));
			} else {
				jsonResultBean.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("lbl.save.fail", null, locale));
			}
		}

		return JsonUtil.toJSON(jsonResultBean, Boolean.TRUE);
	}

	@RequestMapping(value = "/checkDuplicateAddress", headers = { "Accept=application/json" })
	public @ResponseBody
	String checkDuplicateAddress(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("*****************************************************");
		logger.info("+++ CustomerController.checkDuplicateAddress +++");

		String custId = request.getParameter("custId");
		String contId = request.getParameter("addrId");
		int res = customerService.countCustomerAddress(Integer.parseInt(custId), Integer.parseInt(contId));
		if (res > 0) {
			return JsonUtil.toJSON(Boolean.TRUE, Boolean.TRUE);
		} else {
			return JsonUtil.toJSON(Boolean.FALSE, Boolean.TRUE);
		}

	}

	@RequestMapping(value = "/deleteCustomerAddress", headers = { "Accept=application/json" })
	public @ResponseBody
	String deleteCustomerAddress(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("*****************************************************");
		logger.info("+++ CustomerController.deleteCustomerAddress +++");

		String custId = request.getParameter("custId");
		String addrId = request.getParameter("addrId");
		logger.info("Delete Customer Id : " + custId + " Address Id : " + addrId);

		JsonResultBean jsonResultBean = new JsonResultBean();
		if (!StringUtils.isNullOrEmpty(custId) && !StringUtils.isNullOrEmpty(addrId)) {

			if (customerService.deleteCustomerAddress(Integer.parseInt(custId), Integer.parseInt(addrId))) {
				jsonResultBean.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("lbl.delete.success", null, locale));
			} else {
				jsonResultBean.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("lbl.delete.fail", null, locale));
			}

		} else {
			jsonResultBean.setResultCode(JLOWebConstant.FAIL_CODE);
			jsonResultBean.setResultMessage(messageSource.getMessage("cust.message.result.keyIsNull", null, locale));
		}

		return JsonUtil.toJSON(jsonResultBean, Boolean.TRUE);

	}

	@RequestMapping(value = "/choosePrimary", headers = { "Accept=application/json" })
	public @ResponseBody
	String choosePrimary(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("*****************************************************");
		logger.info("+++ CustomerController.choosePrimary +++");

		String custId = request.getParameter("custId");
		String addrId = request.getParameter("addrId");
		logger.info("Customer Id : " + custId + " Address Id : " + addrId);

		JsonResultBean jsonResultBean = new JsonResultBean();
		if (!StringUtils.isNullOrEmpty(custId) && !StringUtils.isNullOrEmpty(addrId)) {

			if (customerService.updatePrimaryAddress(Integer.parseInt(custId), Integer.parseInt(addrId))) {
				jsonResultBean.setResultCode(JLOWebConstant.SUCCESS_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("cust.message.result.choosePrimary.success", null, locale));
			} else {
				jsonResultBean.setResultCode(JLOWebConstant.FAIL_CODE);
				jsonResultBean.setResultMessage(messageSource.getMessage("cust.message.result.choosePrimary.fail", null, locale));
			}

		} else {
			jsonResultBean.setResultCode(JLOWebConstant.FAIL_CODE);
			jsonResultBean.setResultMessage(messageSource.getMessage("cust.message.result.keyIsNull", null, locale));
		}

		return JsonUtil.toJSON(jsonResultBean, Boolean.TRUE);

	}
}
