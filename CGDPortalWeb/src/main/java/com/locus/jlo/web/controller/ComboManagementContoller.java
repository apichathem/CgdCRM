/**
 * 
 */
package com.locus.jlo.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.locus.common.domain.ServiceResult;
import com.locus.common.utils.JsonUtil;
import com.locus.common.utils.StringUtils;
import com.locus.jlo.web.bean.dto.AmphurDTO;
import com.locus.jlo.web.bean.dto.CityDTO;
import com.locus.jlo.web.bean.dto.ComboDTO;
import com.locus.jlo.web.bean.dto.ContactDTO;
import com.locus.jlo.web.bean.dto.KbContentCatDTO;
import com.locus.jlo.web.bean.dto.ProvinceDTO;
import com.locus.jlo.web.bean.dto.SlaDTO;
import com.locus.jlo.web.bean.modelbean.CodebookModelBean;
import com.locus.jlo.web.service.AreaService;
import com.locus.jlo.web.service.CustomerService;
import com.locus.jlo.web.service.KbContentCatService;
import com.locus.jlo.web.service.SlaService;
import com.locus.jlo.web.util.CodeBookHelper;

/**
 * 
 * @author Mr.BoonOom
 * 
 */
@Controller
public class ComboManagementContoller extends BaseController {

	@Autowired
	private AreaService areaService;

	@Autowired
	private KbContentCatService kbContentCatService;
	
	@Autowired
	private SlaService slaService;
	
	@Autowired 
	private CustomerService customerService;

	private Logger logger = Logger.getLogger(getClass());

	/**
	 * 
	 * @param request
	 * @param response
	 * @param codetype
	 * @return json string
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCodeBookByCodeType", headers = "Accept=application/json")
	public @ResponseBody
	String getCodeBookByCodeType(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "codetype", required = true) String codetype) throws Exception {

		logger.info("++++++++++++++++ In Get CodeBook By CodeType ++++++++++++++++");

		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;
		logger.info("codetype request param :" + codetype);

		try {
			resultTypeList = new ArrayList<ComboDTO>();
			Map<String, List<CodebookModelBean>> mcode = CodeBookHelper.getCodeBookList();
			List<CodebookModelBean> codebookList = mcode.get(codetype);
			logger.info("List size :" + codebookList.size());

			for (CodebookModelBean cmb : codebookList) {

				boxModel = new ComboDTO();
				boxModel.setValue(String.valueOf(cmb.getCodeId()));
				boxModel.setLabel(cmb.getCodeName());
				resultTypeList.add(boxModel);

			}

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param codetype
	 * @return json string
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCodeBookByParentTypeAndParentId", headers = "Accept=application/json")
	public @ResponseBody
	String getCodeBookByParentTypeAndParentId(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "parentCodeType", required = true) String parentCodeType,
			@RequestParam(value = "codeType", required = true) String codeType, @RequestParam(value = "id", required = true) String parentId) throws Exception {

		logger.info("++++++++++++++++ In Get CodeBook By CodeType ++++++++++++++++");

		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;
		logger.info("parentId request param :" + parentId);

		try {
			resultTypeList = new ArrayList<ComboDTO>();

			Map<String, List<CodebookModelBean>> mcode = CodeBookHelper.getCodeBookList();
			List<CodebookModelBean> codebookList = mcode.get(codeType);

			for (CodebookModelBean tpCodebook : codebookList) {

				if (parentId.equalsIgnoreCase(tpCodebook.getParentId()) && parentCodeType.equalsIgnoreCase(tpCodebook.getParentType())) {

					boxModel = new ComboDTO();
					boxModel.setValue(String.valueOf(tpCodebook.getCodeId()));
					boxModel.setLabel(tpCodebook.getCodeName());
					resultTypeList.add(boxModel);
				}
			}

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);

	}

	@RequestMapping(value = "/getProvinceList", headers = "Accept=application/json")
	public @ResponseBody
	String getProvinceList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;
		List<ProvinceDTO> provinceDTO = new ArrayList<ProvinceDTO>();
		try {
			resultTypeList = new ArrayList<ComboDTO>();
			ServiceResult<List<ProvinceDTO>> serviceResult = areaService.searchProvinceList();
			provinceDTO = serviceResult.getResult();

			for (ProvinceDTO prov : provinceDTO) {
				boxModel = new ComboDTO();
				boxModel.setValue(String.valueOf(prov.getProvinceId()));
				boxModel.setLabel(prov.getProvinceName());
				resultTypeList.add(boxModel);
			}

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);
	}

	@RequestMapping(value = "/getCityByDistrictIdListDefaultVal", headers = "Accept=application/json")
	public @ResponseBody
	String getCityByAmpurIdListDefaultVal(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "districtId", required = true) String districtId) throws Exception {

		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;

		List<CityDTO> cityDTO = new ArrayList<>();
		try {
			resultTypeList = new ArrayList<ComboDTO>();
			ServiceResult<List<CityDTO>> serviceResult = areaService.searchCityListByDistrictId(districtId);
			cityDTO = serviceResult.getResult();

			for (CityDTO city : cityDTO) {

				boxModel = new ComboDTO();
				boxModel.setValue(String.valueOf(city.getTumbonCode()));
				boxModel.setLabel(city.getName());
				resultTypeList.add(boxModel);
			}

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);
	}

	@RequestMapping(value = "/getZipcodeByCityIdListDefaultVal", headers = "Accept=application/json")
	public @ResponseBody
	String getZipcodeByCityIdListDefaultVal(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "cityId", required = true) String cityId) throws Exception {

		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;

		List<String> zipcodeList = new ArrayList<>();
		try {
			resultTypeList = new ArrayList<ComboDTO>();
			ServiceResult<List<String>> serviceResult = areaService.searchZipcodeListByCityId(cityId);
			zipcodeList = serviceResult.getResult();

			for (String zipcode : zipcodeList) {

				boxModel = new ComboDTO();
				boxModel.setValue(String.valueOf(zipcode));
				boxModel.setLabel(zipcode);
				resultTypeList.add(boxModel);
			}

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);
	}

	@RequestMapping(value = "/getProvinceById", headers = "Accept=application/json")
	public @ResponseBody
	String getProvinceById(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "provinceId", required = true) String provinceId) throws Exception {
		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;

		List<ProvinceDTO> provinceDTO = new ArrayList<ProvinceDTO>();
		try {
			resultTypeList = new ArrayList<ComboDTO>();
			ServiceResult<List<ProvinceDTO>> serviceResult = areaService.searchProvinceById(provinceId);
			provinceDTO = serviceResult.getResult();

			for (ProvinceDTO prov : provinceDTO) {

				boxModel = new ComboDTO();
				boxModel.setValue(String.valueOf(prov.getProvinceId()));
				boxModel.setLabel(prov.getProvinceName());
				resultTypeList.add(boxModel);
			}

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);
	}

	@RequestMapping(value = "/getAmpurByProvinceId", headers = "Accept=application/json")
	public @ResponseBody
	String getAmpurByProvinceId(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "provinceId", required = true) String provinceId) throws Exception {
		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;

		List<AmphurDTO> amphurDTO = new ArrayList<AmphurDTO>();
		try {
			resultTypeList = new ArrayList<ComboDTO>();
			ServiceResult<List<AmphurDTO>> serviceResult = areaService.searchAmphurListByProvinceId(provinceId);
			amphurDTO = serviceResult.getResult();

			for (AmphurDTO prov : amphurDTO) {

				boxModel = new ComboDTO();
				boxModel.setValue(String.valueOf(prov.getAmpurId()));
				boxModel.setLabel(prov.getAmpurName());
				resultTypeList.add(boxModel);
			}

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);
	}

	@RequestMapping(value = "/getAmpurById", headers = "Accept=application/json")
	public @ResponseBody
	String getAmpurById(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "amphurId", required = true) String amphurId) throws Exception {
		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;

		AmphurDTO amphurDTO = new AmphurDTO();
		try {
			resultTypeList = new ArrayList<ComboDTO>();
			ServiceResult<AmphurDTO> serviceResult = areaService.searchAmphurAmphurId(amphurId);
			amphurDTO = serviceResult.getResult();

			boxModel = new ComboDTO();
			boxModel.setValue(String.valueOf(amphurDTO.getAmpurId()));
			boxModel.setLabel(amphurDTO.getAmpurName());
			resultTypeList.add(boxModel);

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);
	}

	@RequestMapping(value = "/getTypeProblem1List", headers = "Accept=application/json")
	public @ResponseBody
	String getTypeProblem1List(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;
		List<KbContentCatDTO> KbContentCatDTO = new ArrayList<KbContentCatDTO>();
		try {
			resultTypeList = new ArrayList<ComboDTO>();
			ServiceResult<List<KbContentCatDTO>> serviceResult = kbContentCatService.getKbContentCat1List();
			KbContentCatDTO = serviceResult.getResult();

			for (KbContentCatDTO content : KbContentCatDTO) {
				boxModel = new ComboDTO();
				boxModel.setValue(String.valueOf(content.getContentCatId()));
				boxModel.setLabel(content.getCatName());
				resultTypeList.add(boxModel);
			}

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);
	}
	
	@RequestMapping(value = "/getTypeProblem2ByParentIdList", headers = "Accept=application/json")
	public @ResponseBody
	String getTypeProblem2ByParentIdList(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "parentCatId", required = true) String parentCatId) throws Exception {
		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;

		List<KbContentCatDTO> KbContentCatDTO = new ArrayList<KbContentCatDTO>();
		try {
			resultTypeList = new ArrayList<ComboDTO>();
			ServiceResult<List<KbContentCatDTO>> serviceResult = kbContentCatService.getKbContentCat2ByParentCatIdList(parentCatId);
			KbContentCatDTO = serviceResult.getResult();

			for (KbContentCatDTO content : KbContentCatDTO) {

				boxModel = new ComboDTO();
				boxModel.setValue(String.valueOf(content.getContentCatId()));
				boxModel.setLabel(content.getCatName());
				resultTypeList.add(boxModel);
			}

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);
	}
	
	
	@RequestMapping(value = "/getTypeProblem3ByParentIdList", headers = "Accept=application/json")
	public @ResponseBody
	String getTypeProblem3ByParentIdList(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "parentCatId", required = true) String parentCatId) throws Exception {
		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;

		List<KbContentCatDTO> KbContentCatDTO = new ArrayList<KbContentCatDTO>();
		try {
			resultTypeList = new ArrayList<ComboDTO>();
			ServiceResult<List<KbContentCatDTO>> serviceResult = kbContentCatService.getKbContentCat3ByParentCatIdList(parentCatId);
			KbContentCatDTO = serviceResult.getResult();

			for (KbContentCatDTO content : KbContentCatDTO) {

				boxModel = new ComboDTO();
				boxModel.setValue(String.valueOf(content.getContentCatId()));
				boxModel.setLabel(content.getCatName());
				resultTypeList.add(boxModel);
			}

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);
	}
	
	
	@RequestMapping(value = "/getTypeProblem4ByParentIdList", headers = "Accept=application/json")
	public @ResponseBody
	String getTypeProblem4ByParentIdList(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "parentCatId", required = true) String parentCatId) throws Exception {
		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;

		List<KbContentCatDTO> KbContentCatDTO = new ArrayList<KbContentCatDTO>();
		try {
			resultTypeList = new ArrayList<ComboDTO>();
			ServiceResult<List<KbContentCatDTO>> serviceResult = kbContentCatService.getKbContentCat4ByParentCatIdList(parentCatId);
			KbContentCatDTO = serviceResult.getResult();

			for (KbContentCatDTO content : KbContentCatDTO) {

				boxModel = new ComboDTO();
				boxModel.setValue(String.valueOf(content.getContentCatId()));
				boxModel.setLabel(content.getCatName());
				resultTypeList.add(boxModel);
			}

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);
	}
	
	@RequestMapping(value = "/getTypeProblem5ByParentIdList", headers = "Accept=application/json")
	public @ResponseBody
	String getTypeProblem5ByParentIdList(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "parentCatId", required = true) String parentCatId) throws Exception {
		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;

		List<KbContentCatDTO> KbContentCatDTO = new ArrayList<KbContentCatDTO>();
		try {
			resultTypeList = new ArrayList<ComboDTO>();
			ServiceResult<List<KbContentCatDTO>> serviceResult = kbContentCatService.getKbContentCat5ByParentCatIdList(parentCatId);
			KbContentCatDTO = serviceResult.getResult();

			for (KbContentCatDTO content : KbContentCatDTO) {

				boxModel = new ComboDTO();
				boxModel.setValue(String.valueOf(content.getContentCatId()));
				boxModel.setLabel(content.getCatName());
				resultTypeList.add(boxModel);
			}

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);
	}

	@RequestMapping(value = "/getSLAList", headers = "Accept=application/json")
	public @ResponseBody
	String getSLAList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;

		List<SlaDTO> salDTO = new ArrayList<SlaDTO>();
		try {
			resultTypeList = new ArrayList<ComboDTO>();
			ServiceResult<List<SlaDTO>> serviceResult = slaService.getSlaList();
			salDTO = serviceResult.getResult();

			for (SlaDTO sla : salDTO) {

				boxModel = new ComboDTO();
				boxModel.setValue(String.valueOf(sla.getSlaId()));
				boxModel.setLabel(sla.getSlaName());
				resultTypeList.add(boxModel);
			}

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);
	}
	
	
	
	@RequestMapping(value = "/getContactByCustIdList", headers = "Accept=application/json")
	public @ResponseBody
	String getContactByCustIdList(HttpServletRequest request, HttpServletResponse response ,@RequestParam(value = "custId", required = true) String custId) throws Exception {
		List<ComboDTO> resultTypeList = null;
		ComboDTO boxModel = null;

		Integer custIdIn = !StringUtils.isNullOrEmpty(custId) ? Integer.valueOf(custId) :  -1;  
		
		List<ContactDTO> contDTO = new ArrayList<ContactDTO>();
		try {
			resultTypeList = new ArrayList<ComboDTO>();
			ServiceResult<List<ContactDTO>> serviceResult = customerService.getContactByCustIdList(custIdIn);
			contDTO = serviceResult.getResult();

			for (ContactDTO cont : contDTO) {
				boxModel = new ComboDTO();
				boxModel.setValue(String.valueOf(cont.getContId()));
				boxModel.setLabel(cont.getFullName());
				resultTypeList.add(boxModel);
			}

			logger.info(" for return size: " + resultTypeList.size());

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonUtil.toJSON(resultTypeList, true);
	}
	
}
