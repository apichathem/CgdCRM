package com.locus.jlo.web.controller;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.locus.common.constant.WebPortalConstant;
import com.locus.common.domain.ServiceResult;
import com.locus.common.utils.CollectionUtil;
import com.locus.common.utils.DateTimeUtils;
import com.locus.common.utils.FileUtil;
import com.locus.common.utils.JsonUtil;
import com.locus.common.utils.StringUtils;
import com.locus.jlo.rest.response.ServiceResponse;
import com.locus.jlo.web.bean.dto.UserInfoDTO;
import com.locus.jlo.web.bean.modelbean.ChangePasswordModelBean;
import com.locus.jlo.web.bean.modelbean.UserLoginInfoModelBean;
import com.locus.jlo.web.bean.modeljson.DatatableModelBean;
import com.locus.jlo.web.bean.modeljson.MultiSelectBean;
import com.locus.jlo.web.constant.JLOWebConstant;
import com.locus.jlo.web.service.DropdownService;
import com.locus.jlo.web.service.UserManagementService;
import com.locus.jlo.web.util.SelectorContainer;

@Controller
public class UserManagementController extends BaseController {

	@Value(value = "${home.path}")
	private String homePath;
	
	@Autowired
	private UserManagementService userManagementService;

	@Autowired
	private DropdownService dropdownService;
	
	@Autowired
	ServletContext context;

	private static final String USER_MODEL = "userModelBean";
	private static final String USER_RESULT_LIST = "userList";
	private static final String ROLE_SELECT = "roles";

	private Logger logger = Logger.getLogger(getClass());

	private void clearSession(HttpServletRequest request) {
		setSessionAttr(request, USER_RESULT_LIST, null);
	}

	@RequestMapping(value = "/userManagement")
	public ModelAndView userManagement(Model model, HttpServletRequest request, Locale local) {
		model.addAttribute("pageTitle", messageSource.getMessage("userManagement.title", null, local));
		logger.info("UserManagementController - userManagement");
		logger.info("*******************************************");
		clearSession(request);
		setMenuId(request);
		return  new ModelAndView("userManagement",USER_MODEL, new UserInfoDTO());
	}

	@RequestMapping(value = "/getUserList", headers = { "Accept=application/json" })
	public @ResponseBody
	String getUserList(HttpServletRequest request, HttpServletResponse response, Locale local) throws Exception {
		logger.info("UserManagementController - getUserList");
		logger.info("*****************************************************");
		DatatableModelBean datatableModelBean = new DatatableModelBean();
		
		Pageable pageable = getPagableFromRequest(request);
		
		UserInfoDTO bean = new UserInfoDTO();
		bean.setAgentNo(request.getParameter("agentNo"));
		bean.setFirstName(request.getParameter("firstName"));
		bean.setLastName(request.getParameter("lastName"));
		bean.setLoginId(request.getParameter("loginId"));
		bean.setRoleId(request.getParameter("roleId"));
		ServiceResult<Page<UserInfoDTO>> serviceResult = userManagementService.searchByCriteria(bean, pageable, getLanguageCode(local));

		if (serviceResult.isSuccess()) {
			Page<UserInfoDTO> tpUsrMasterResult = serviceResult.getResult();
			Integer totalRecords = (int) (long) tpUsrMasterResult.getTotalElements();
			UserInfoDTO userInfoDTO = null;
			List<UserInfoDTO> userInfoDomainList = tpUsrMasterResult.getContent();
			List<UserInfoDTO> tpUsrMasterDtoList = new ArrayList<UserInfoDTO>();
			
			if (userInfoDomainList != null && userInfoDomainList.size() > 0) {
				for (UserInfoDTO tpUsrMaster : userInfoDomainList) {
					userInfoDTO = new UserInfoDTO();
					userInfoDTO.setAgentNo(tpUsrMaster.getAgentNo());
					userInfoDTO.setFirstName(tpUsrMaster.getFirstName());
					userInfoDTO.setLastName(tpUsrMaster.getLastName());
					userInfoDTO.setEmail(tpUsrMaster.getEmail());
					userInfoDTO.setMobileNo(tpUsrMaster.getMobileNo());
					userInfoDTO.setLoginId(tpUsrMaster.getLoginId());
					userInfoDTO.setUseYn(tpUsrMaster.getUseYn());
					userInfoDTO.setUserId(tpUsrMaster.getUserId());
					userInfoDTO.setRoleId(tpUsrMaster.getRoleId());
					userInfoDTO.setRoleName(tpUsrMaster.getRoleName());
					userInfoDTO.setDepartmentCd(tpUsrMaster.getDepartmentCd());
					userInfoDTO.setDepartmentName(tpUsrMaster.getDepartmentName());
					
					StringBuffer url = new StringBuffer();
					url.append("<a href='#' onclick='postAction(\"/initManageUser.htm?id=" + userInfoDTO.getUserId() + "\")'>");
					url.append("<i class='fa fa-pencil'></i>");
					url.append("</a>");
					userInfoDTO.setEditUrl(url.toString());
					tpUsrMasterDtoList.add(userInfoDTO);
				}
			}
			datatableModelBean.setsEcho(getSecho(request));
			datatableModelBean.setiTotalDisplayRecords(totalRecords);
			datatableModelBean.setiTotalRecords(totalRecords);
			datatableModelBean.setAaData(tpUsrMasterDtoList);
		} else {
			showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription(),messageSource.getMessage("userManage.topic", null, local));
		}
		
		return JsonUtil.toJSON(datatableModelBean, true);
	}
	
	@RequestMapping(value = "/initManageUser")
	public ModelAndView initManageUser(@ModelAttribute(USER_MODEL) UserLoginInfoModelBean userModelBean, @RequestParam("id") Integer idx,
			Model model, HttpServletRequest request, Locale locale, Pageable pageable) {

		logger.info("UserManagementController - initManageUser [ " + idx + "]");
		logger.info("*******************************************");
		model.addAttribute("pageTitle", messageSource.getMessage("userManagement.title", null, locale));

		// Dropdown 
		Map<Integer, String> roleSelect = new HashMap<Integer, String>();
		ServiceResult<Map<Integer, String>> roleServiceResult = dropdownService.getRoleSelect();
		if (roleServiceResult.isSuccess()) {
			roleSelect = roleServiceResult.getResult();
		}

		/*Map<String, String> departmentSelect = new HashMap<String, String>();
		departmentSelect.put("", messageSource.getMessage("message.please.select", null, local));
		ServiceResult<Map<String, String>> departmentServiceResult = dropdownService.getDepartmentSelect();
		if (departmentServiceResult.isSuccess()) {
			departmentSelect = departmentServiceResult.getResult();
		}*/
		
		setSessionAttr(request, SelectorContainer.YN, selectorContainer.getYnSelect());
		setSessionAttr(request, ROLE_SELECT, roleSelect);
		//setSessionAttr(request, DEPARTMENT_SELECT, departmentSelect);

		if (idx != null) {
			// Edit mode
			// Query TP_USR_MASTER from database
			ServiceResult<UserInfoDTO> serviceResult = userManagementService.searchById(idx, getLanguageCode(locale));

			if (serviceResult.isSuccess()) {
				UserInfoDTO dto = serviceResult.getResult();
				dto.setPassword(new String(Base64.decodeBase64(dto.getPassword().getBytes())));
				// DTO <--> Bean
				setDto2Bean(dto, userModelBean);
				userModelBean.setMode(JLOWebConstant.MODE_UPDATE);
			} else {
				showWebMessage(request, serviceResult.getResponseCode(), serviceResult.getResponseDescription(), messageSource.getMessage("userManege.detail.topic", null, locale));
			}
		} else {
			// Insert mode
			userModelBean = new UserLoginInfoModelBean();
			userModelBean.setUseYn("Y");
			userModelBean.setMode(JLOWebConstant.MODE_INSERT);
			
		}

		return new ModelAndView("userDetailManagement", USER_MODEL, userModelBean);
	}

	private void setDto2Bean(UserInfoDTO dto, UserLoginInfoModelBean bean) {
		bean.setUserId(dto.getUserId());
		bean.setLoginId(dto.getLoginId());
		bean.setPassword(dto.getPassword());
		bean.setUseYn(dto.getUseYn());
		bean.setRoleId(dto.getRoleId());
		bean.setAgentId(dto.getAgentId());
		bean.setAgentNo(dto.getAgentNo());
		bean.setFirstName(dto.getFirstName());
		bean.setLastName(dto.getLastName());
		bean.setUserPic(dto.getUserPic());
		bean.setEmail(dto.getEmail());
		bean.setMobileNo(dto.getMobileNo());
		bean.setReportTo(dto.getReportTo());
		bean.setReportName(dto.getReportName());
		bean.setDepartmentCd(dto.getDepartmentCd());
		bean.setDepartmentName(dto.getDepartmentName());
		bean.setPositionCd(dto.getPositionCd());
		bean.setPositionName(dto.getPositionName());
		bean.setCreateBy(dto.getCreateBy());
		bean.setCreateDate(DateTimeUtils.formatDateTime(dto.getRegDt()));
		bean.setUpdateBy(dto.getUpdateBy());
		bean.setUpdateDate(DateTimeUtils.formatDateTime(dto.getChgDt()));
	}
	
	private void setBean2Dto(UserLoginInfoModelBean bean, UserInfoDTO dto) {
		dto.setLoginId(bean.getLoginId());
		dto.setPassword(bean.getPassword());
		dto.setUseYn(bean.getUseYn());
		dto.setRoleId(bean.getRoleId());
		dto.setAgentId(bean.getAgentId());
		dto.setAgentNo(bean.getAgentNo());
		dto.setFirstName(bean.getFirstName());
		dto.setLastName(bean.getLastName());
		dto.setUserPic(bean.getUserPic());
		dto.setEmail(bean.getEmail());
		dto.setMobileNo(bean.getMobileNo());
		dto.setReportTo(bean.getReportTo());
		dto.setReportName(bean.getReportName());
		dto.setDepartmentCd(bean.getDepartmentCd());
		dto.setDepartmentName(bean.getDepartmentName());
		dto.setPositionCd(bean.getPositionCd());
		dto.setPositionName(bean.getPositionName());
	}
	
	@RequestMapping(value = {"/insertUser","/updateUser"}, method = RequestMethod.GET)
	public ModelAndView saveUserGet(@ModelAttribute(USER_MODEL) UserLoginInfoModelBean userModelBean, Model model, HttpServletRequest request,
			Locale local, Pageable pageable) {
		//Prevent Post-Redirect
		return new ModelAndView("userDetailManagement", USER_MODEL, userModelBean);
	}
	
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody String insertUser(@ModelAttribute(USER_MODEL) UserLoginInfoModelBean userModelBean, Model model, MultipartHttpServletRequest request,
			Locale locale, Pageable pageable) {
		logger.info("UserManagementController - insertUser ");
		return saveUser(userModelBean, model, request, locale, pageable);
	}
	
	@RequestMapping(value = "updateUser", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody String updateUser(@ModelAttribute(USER_MODEL) UserLoginInfoModelBean userModelBean, Model model, MultipartHttpServletRequest request,
			Locale locale, Pageable pageable) {
		logger.info("UserManagementController - updateUser - " + userModelBean.getUserId());
		return saveUser(userModelBean, model, request, locale, pageable);
	}
	
	@RequestMapping(value = "changePassword")
	public  ModelAndView changePassword(HttpServletRequest request) {
		logger.info("UserManagementController - changePassword ");
		return new ModelAndView("changePassword");
	}
	
	public String saveUser(UserLoginInfoModelBean userModelBean, Model model, MultipartHttpServletRequest request,
			Locale locale, Pageable pageable) {
		logger.info("*******************************************");

		try {

			UserInfoDTO tpUsrMaster = new UserInfoDTO();
			// Bean <--> DTO
			setBean2Dto(userModelBean, tpUsrMaster);
			if (JLOWebConstant.MODE_UPDATE.equals(userModelBean.getMode())) {
				logger.info("UserManagementController - saveUser - Update mode");
				
				setBean2Dto(userModelBean, tpUsrMaster);
				tpUsrMaster.setUserId(userModelBean.getUserId());
				tpUsrMaster.setUseYn(userModelBean.getUseYn());
				tpUsrMaster.setUpdateBy(getUserId(request).toString());
				tpUsrMaster.setChgDt(Calendar.getInstance().getTime());
				userModelBean.setMode(JLOWebConstant.MODE_UPDATE);
				
			} else if (JLOWebConstant.MODE_INSERT.equals(userModelBean.getMode())) {
				logger.info("UserManagementController - saveUser - Insert mode");

				tpUsrMaster.setUseYn("Y");
				tpUsrMaster.setCreateBy(getUserId(request).toString());
				tpUsrMaster.setRegDt(Calendar.getInstance().getTime());
				tpUsrMaster.setAgentId(null);
				userModelBean.setMode(JLOWebConstant.MODE_INSERT);
				
			}
			
			// Save image
			saveUserImgFile(request, tpUsrMaster);
			
			byte[] bytesEncoded = Base64.encodeBase64(userModelBean.getPassword().getBytes());
			byte[] bytesDncoded = Base64.decodeBase64(bytesEncoded);
			logger.info("Encoded value is " + new String(bytesEncoded));
			logger.info("Decoded value is " + new String(bytesDncoded));
			tpUsrMaster.setPassword(new String(bytesEncoded));
			tpUsrMaster.setPositionCd(userModelBean.getPositionCd());
			tpUsrMaster.setDepartmentCd(userModelBean.getDepartmentCd());

			tpUsrMaster = userManagementService.saveUsrMaster(tpUsrMaster);
			userModelBean.setUserPic(tpUsrMaster.getUserPic());
			userModelBean.setUserId(tpUsrMaster.getUserId());
			
			logger.info("User UpdateBy : " + userModelBean.getUpdateBy());
			logger.info("User login : " + getUserLoginId(request));
			logger.info("Edit login : " + tpUsrMaster.getLoginId());
			if (getUserLoginId(request).equals(tpUsrMaster.getLoginId())) {
				setUser2Session(tpUsrMaster, request, locale);
			}

			showWebMessage(request, JLOWebConstant.SUCCESS_CODE, messageSource.getMessage("lbl.save.success", null, locale), messageSource.getMessage("userManege.detail.topic", null, locale));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			showWebMessage(request, JLOWebConstant.FAIL_CODE, messageSource.getMessage("lbl.save.fail", null, locale), messageSource.getMessage("userManege.detail.topic", null, locale));
		}

		String url = "/initManageUser.htm?id=" + userModelBean.getUserId();
		return JsonUtil.toJSON(url, true );
	}
	
	private void saveUserImgFile(MultipartHttpServletRequest request, UserInfoDTO tpUsrMaster) {
		// Save user image.
		MultipartFile imgFile = request.getFile("imgFile");
		String filename = FileUtil.genFilename("USERINFO") + ".png";
		String path = homePath + JLOWebConstant.ROOT_FOLDER;
		String imagePath = JLOWebConstant.ATTACHMENT+JLOWebConstant.USER_PROFILE_IMAGE_PATH + filename;
		String userPic = tpUsrMaster.getUserPic();
		
		if (imgFile != null) {
			logger.info("[ " + imgFile.getOriginalFilename() + " ][ " + imgFile.getContentType() + " ][ " + imgFile.getSize() + " ]");
			if (!StringUtils.isNullOrEmpty(imgFile.getOriginalFilename())) {
				
				// Delete old image
				try {
					File oldFile = new File(path+userPic);
					oldFile.delete();
					logger.info("delete file "+path+userPic);
				} catch (Exception e) {
					logger.error("Cannot delete "+ path + userPic + " cause " + e.getMessage());
				}
				
				// Save file
				File myFile = new File(path + imagePath);
				logger.info("path : " + (path + imagePath) + " length : " + (path + imagePath).length());
				try {
					File parentDir = myFile.getParentFile();
					if(! parentDir.exists()) {
						parentDir.mkdirs(); 
					}
					imgFile.transferTo(myFile);
					imageUpScale(myFile,128,128);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					logger.info("Cannot write message to file.");
				}
				tpUsrMaster.setUserPic(imagePath);
			} else {
				tpUsrMaster.setUserPic(userPic);
			}
			
		} else {
			logger.info("Image is null !!!");
			
		}
		
	}
	
	private void imageUpScale(File myFile, int width, int height) {
		
		
		try {
			
			BufferedImage originalImage =  ImageIO.read(myFile);
			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			
			BufferedImage resizedImage = new BufferedImage(width, height, type);			
//			img.createGraphics().drawImage(originalImage,0,0,width,height,null);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, width, height, null);
			g.dispose();	
			g.setComposite(AlphaComposite.Src);
		 
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,
			RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
			ImageIO.write(resizedImage, "png", myFile);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		
	}

	private void setUser2Session(UserInfoDTO tpUsrMaster, HttpServletRequest request, Locale locale) {
		UserInfoDTO userLoginInfoModelBean = getUserInfo(request);
		userLoginInfoModelBean.setLoginId(tpUsrMaster.getLoginId());
		userLoginInfoModelBean.setUserId(tpUsrMaster.getUserId());
		userLoginInfoModelBean.setCtiId(tpUsrMaster.getCtiId());
		userLoginInfoModelBean.setFirstName(tpUsrMaster.getFirstName());
		userLoginInfoModelBean.setLastName(tpUsrMaster.getLastName());
		userLoginInfoModelBean.setCtiPassword(tpUsrMaster.getCtiPassword());
		userLoginInfoModelBean.setCtiStation(tpUsrMaster.getCtiStation());
		if(tpUsrMaster.getUserPic()!=null) userLoginInfoModelBean.setImageFile(tpUsrMaster.getUserPic());
		setSessionAttr(request, WebPortalConstant.USER_PROFILE, userLoginInfoModelBean);
	}
	
	@RequestMapping(value = "/userAssignment")
	public ModelAndView userAssignment(Model model, HttpServletRequest request, Locale local) {
		model.addAttribute("pageTitle", messageSource.getMessage("userManagement.title", null, local));
		logger.info("UserManagementController - userAssignment");
		logger.info("*******************************************");
		setMenuId(request);
		UserLoginInfoModelBean userLoginInfoModelBean = new UserLoginInfoModelBean();
		String userId = request.getParameter("userId");
		if (StringUtils.isNotEmpty(userId)) {
			userLoginInfoModelBean.setUserId(Integer.parseInt(userId));
		}
		
		// Drop down 
		Map<Integer, String> userSelect = new HashMap<Integer, String>();
		ServiceResult<Map<Integer, String>> roleServiceResult = dropdownService.getUserSelect();
		if (roleServiceResult.isSuccess()) {
			userSelect = roleServiceResult.getResult();
		}
		
		model.addAttribute("userList", userSelect);
		return  new ModelAndView("userAssignment", USER_MODEL , userLoginInfoModelBean);
	}
	
	@RequestMapping(value = "/getUnderUserList", headers = { "Accept=application/json" })
	public @ResponseBody
	String getUnderUserList(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		logger.info("UserManagementController - getUnderUserList");
		logger.info("*****************************************************");
		
		String requestUserId = request.getParameter("userId");
		Integer userId = Integer.parseInt(requestUserId);
		Integer roleId = null;
		MultiSelectBean multiSelectBean = null;
		List<MultiSelectBean> allUserAvailable = new ArrayList<MultiSelectBean>();
		
		// Get role from user
		ServiceResult<UserInfoDTO> userDto = userManagementService.searchById(userId, getLanguageCode(locale));
		if (userDto.isSuccess()) {
			UserInfoDTO userInfoDTO = userDto.getResult();
			
			roleId = Integer.parseInt(userInfoDTO.getRoleId());
			
			logger.info("userId : " + userId + " roleId : " + roleId);
			
			// Call service
			// Get under user list
			ServiceResult<List<UserInfoDTO>> serviceResult1 = userManagementService.getUnderUserList(userId);
			if (serviceResult1.isSuccess()) {
				List<UserInfoDTO> userInfoDTOList = serviceResult1.getResult();
				if (CollectionUtil.isNotEmpty(userInfoDTOList)) {
					for (UserInfoDTO dto : userInfoDTOList) {
						multiSelectBean = new MultiSelectBean();
						multiSelectBean.setText(dto.getFirstName() + " " + dto.getLastName());
						multiSelectBean.setValue(dto.getUserId().toString());
						multiSelectBean.setSelected(Boolean.TRUE);
						
						allUserAvailable.add(multiSelectBean);
					}
				}
			}
			
			// Get available list
			ServiceResult<List<UserInfoDTO>> serviceResult2 = userManagementService.getUnderUserFreeList(roleId);
			if (serviceResult2.isSuccess()) {
				List<UserInfoDTO> userInfoDTOList = serviceResult2.getResult();
				if (CollectionUtil.isNotEmpty(userInfoDTOList)) {
					for (UserInfoDTO dto : userInfoDTOList) {
						multiSelectBean = new MultiSelectBean();
						multiSelectBean.setText(dto.getFirstName() + " " + dto.getLastName());
						multiSelectBean.setValue(dto.getUserId().toString());
						multiSelectBean.setSelected(Boolean.FALSE);
						
						allUserAvailable.add(multiSelectBean);
					}
				}
			}
			
		}
		
		return JsonUtil.toJSON(allUserAvailable, Boolean.TRUE);
	}
	
	@RequestMapping(value = "saveUserAssignment", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody String saveUserAssignment(@ModelAttribute(USER_MODEL) UserLoginInfoModelBean userModelBean, Model model, MultipartHttpServletRequest request,
			Locale locale, Pageable pageable) {
		logger.info("UserManagementController - saveUserAssignment");
		
		logger.info("User Id :" + userModelBean.getUserId());
		logger.info("Team User Id :" + userModelBean.getMyTeamUserId());
		
		if (userModelBean.getMyTeamUserId() != null) {
			userManagementService.saveUserAssignment(userModelBean.getUserId(), userModelBean.getMyTeamUserId());
		}
		
		userManagementService.saveUserUnAssignment(userModelBean.getUserId(), userModelBean.getMyTeamUserId());
		
		String url = "/userAssignment.htm?userId=" + userModelBean.getUserId();
		return JsonUtil.toJSON(url, true );
	}
	
	
	@RequestMapping(value="updateNewPassword",method = RequestMethod.POST, headers= { "Accept=application/json" })
	public @ResponseBody String updateNewPassword(@ModelAttribute("changePasswordBean") ChangePasswordModelBean changePasswordBean, HttpServletRequest request, Locale locale){
		logger.info("UserManagementController - updateNewPassword");
		ServiceResponse<ChangePasswordModelBean> serviceResponse = null;
		try{
			if(changePasswordBean.getNewpassword().equalsIgnoreCase(changePasswordBean.getRetypenewpassword())){
				ServiceResult<UserInfoDTO> serviceResult = userManagementService.getUserInfo(changePasswordBean.getUsername(), getLanguageCode(locale));
				if(serviceResult.isSuccess()){
					String currentPassword = new String(Base64.decodeBase64(serviceResult.getResult().getPassword().getBytes()));
					if(currentPassword.equalsIgnoreCase(changePasswordBean.getOldpassword())){
						changePasswordBean.setUserId(serviceResult.getResult().getUserId());
						byte[] bytesEncoded = Base64.encodeBase64(changePasswordBean.getNewpassword().getBytes());
						changePasswordBean.setBytePassword(new String(bytesEncoded));
						userManagementService.updateNewPassword(changePasswordBean);
						serviceResponse = new ServiceResponse<ChangePasswordModelBean>(200, "0000", "เปลี่ยนรหัสผ่านสำเร็จ / Change your password success", null);
					}else{
						serviceResponse = new ServiceResponse<ChangePasswordModelBean>(200, "0001", "รหัสผ่าน เดิมไม่ถูกต้อง / Old Password invalid", null);
					}
				}else{
					throw new Exception("Request Invalid.");
				}
			}else{
				throw new Exception("Request Invalid.");
			}
		}catch(Exception e){
			serviceResponse = new ServiceResponse<ChangePasswordModelBean>(500, "9999", "Server Exception", null);
		}
		return JsonUtil.toJSON(serviceResponse, true );
		
	}
}
