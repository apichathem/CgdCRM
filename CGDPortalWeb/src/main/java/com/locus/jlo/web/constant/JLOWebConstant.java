package com.locus.jlo.web.constant;

import java.util.Locale;

public interface JLOWebConstant {

	public interface SECURITYWEB {
		String METHOD_GROUP = "groupsecurity";
		String SESSION_METHOD_GROUP_SECURITY = "SESSION_METHOD_GROUP_SECURITY";
	}

	// CRUD
	String SUCCESS_CODE = "0";
	String SUCCESS_DESC = "Successful";
	String INSERT_SUCCESS_CODE = SUCCESS_CODE;
	String INSERT_SUCCESS_DESC = "Insert record successful";
	String UPDATE_SUCCESS_CODE = SUCCESS_CODE;
	String UPDATE_SUCCESS_DESC = "Update record successful";
	String DELETE_SUCCESS_CODE = SUCCESS_CODE;
	String DELETE_SUCCESS_DESC = "Delete record successful";

	String FAIL_CODE = "-1";
	String FAIL_DESC = "Fail";
	String FAIL_TH_QUEST_CODE = "-2";
	String FAIL_TH_QUEST_DESC = "Thai Quest Fail";
	String INSERT_FAIL_CODE = "INS-01 " + FAIL_DESC;
	String INSERT_FAIL_DESC = "Insert record fail";
	String UPDATE_FAIL_CODE = "UPD-01 " + FAIL_DESC;
	String UPDATE_FAIL_DESC = "Update record fail";
	String DELETE_FAIL_CODE = "DEL-01 " + FAIL_DESC;
	String DELETE_FAIL_DESC = "Delete record fail";

	String DUPLICATE_KEY_CODE = "INS-02 " + FAIL_DESC;
	String DUPLICATE_KEY_DESC = "Cannot insert duplicate key";

	String MODE = "mode";
	String MODE_INSERT = "insert";
	String MODE_UPDATE = "update";
	String MODE_DELETE = "delete";

	String CODEBOOK_ROOT_ID = "1";
	String CODEBOOK_ROOT_NAME = "ROOT";
	String CODEBOOK_ROOT_TYPE = "ROOT";

	String TREE_ROOT_STYLE = "fa fa-folder icon-warning icon-lg";
	String TREE_LEAF_STYLE = "fa fa-file icon-info icon-lg";
	String TREE_ENABLE_STYLE = "fa fa-file icon-info icon-lg";
	String TREE_DISABLE_STYLE = "fa fa-file icon-red icon-lg";
	
	String SOURCE_CALL_CENTER = "00000001";
	String SOURCE_HELPDESK = "000000002";

	String ACTION_VALUE = "actionValue";
	// Attachment
	public interface FILE_EXT{
		String IMAGE="png,gif,jpeg,jpg";
		String VIDEO="mp4,3gpp";
		String DOCUMENT="pdf";
	}
	public interface FILE_TYPE{
		String IMAGE="image/png,image/x-png,image/gif,image/jpeg";
		String VIDEO="video/mp4,video/3gpp";
		String DOCUMENT="application/pdf,application/x-pdf";
	}
	public interface FILE_SIZE{
		// spring-application-context.xml set maxUploadSize 52428800
		// http://www.whatsabyte.com/P1/byteconverter.htm
		// 0.5M = 524288
		// 1M = 1048576
		// 2M = 2097152
		// 5M = 5242880
		// 10M = 10485760
		// 30M = 31457280
		// 50M = 52428800
		int IMAGE=1048576;
		int VIDEO=5242880;
		int DOCUMENT=5242880;
		
		int FIVE_MB = 5242880;
		int TEN_MB = 10485760;
		int TWENTY_MB = 20971520;
		
	}
	String ATTACHMENT_FOLDER = "C:/agentweb";
	String ROOT_FOLDER = "CGD";	
	String ATTACHMENT = "/attachment";
	//String SHARED_URL = "http://192.168.10.47:8089/shared";
	String USER_PROFILE_IMAGE_PATH = "/usr_profile_pic/";
	String CONTENT_ATT_PATH = "/content/";
	String SERVICE_REQUEST_ATT_PATH = "/sr/";
	String INCIDENTAL_ATT_PATH = "/inc/";
	String COMPLAINT_ATT_PATH = "/cp/";
	String ACTIVITY_ATT_PATH = "/activity/";
	String ASSET_ATT_PATH = "/asset/";
	String RISK_ATT_PATH = "/risk/";
	String REPORT_PATH = "/report/";

	Integer recordsPerPage = 10;

	Locale DATELOCALE = Locale.US;
	String LANGUAGE_PARAM = "lang";

	String DATE_PATTERN_SEARCH = "dd/MM/yyyy";
	String DATE_PATTERN_INSERT_UPDATE = "dd/MM/yyyy HH:mm";

	String SESSION_MENU_ID = "menuSessionId";
	
	String WEB_CHENNEL = "W";
	String MOBILE_CHANNEL = "M";
	
}
