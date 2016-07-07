package com.locus.jlo.web.service.impl;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.locus.common.jdbc.DynamicJdbcDao;
import com.locus.common.utils.DateTimeUtils;
import com.locus.common.utils.StringUtils;
import com.locus.jlo.web.constant.SequenceType;
import com.locus.jlo.web.service.SequenceGeneratorService;

@Service
public class SequenceGeneratorServiceImpl extends BaseService implements SequenceGeneratorService{
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("dynamicJdbcDao")
	private DynamicJdbcDao dynamicJdbcDao;
	
	@Override
	public String generateByType(SequenceType sequenceType) {
		String sequence = "";
		String dateformat = "";
		String order = "";
		
		// Remind this SQL using only SQL 2012 server
		String sql = "select next value for " + sequenceType + "_SEQ";
		// MySQL 
		// String sql = "CALL nextval('"+sequenceType+"',@OSEQ)";
		logger.info("sql : " + sql);
		
		Long seq = dynamicJdbcDao.getSequence(sql);
		logger.info("SEQ = "+seq);
		Calendar cal = Calendar.getInstance();
		
		switch (sequenceType) {
			case BRANCH:
				// BRANCH-0000
				order = StringUtils.leftPad(seq.toString(), 4, "0");
				sequence = "BRANCH-" + order;
				break;
			case NEWS:
				order = StringUtils.leftPad(seq.toString(), 5, "0");
				sequence = "NEWS-" + order;
				break;
			case HOSPITAL:
				order = StringUtils.leftPad(seq.toString(), 5, "0");
				sequence = "HOSPITAL-" + order;
				break;
			case SERVICE_REQUEST:
				//SR-yyyyMMdd-0000000
				dateformat = DateTimeUtils.formatDate(cal.getTime(), DateTimeUtils.DATE_FORMAT_YYYYMMDD);
				order = StringUtils.leftPad(seq.toString(), 7, "0");
				sequence = "SR-" + dateformat + "-" + order;
				break;
			case INCIDENT:
				// INC-yyyyMMdd-0000000
				dateformat = DateTimeUtils.formatDate(cal.getTime(), DateTimeUtils.DATE_FORMAT_YYYYMMDD);
				order = StringUtils.leftPad(seq.toString(), 7, "0");
				sequence = "INC-" + dateformat + "-" + order;
				break;
			case COMPLAINT:
				// CP-yyyyMMdd-0000000
				dateformat = DateTimeUtils.formatDate(cal.getTime(), DateTimeUtils.DATE_FORMAT_YYYYMMDD);
				order = StringUtils.leftPad(seq.toString(), 7, "0");
				sequence = "CP-" + dateformat + "-" + order;
				break;
			case ACTIVITY:
				// ACT-yyyyMMdd-0000000
				dateformat = DateTimeUtils.formatDate(cal.getTime(), DateTimeUtils.DATE_FORMAT_YYYYMMDD);
				order = StringUtils.leftPad(seq.toString(), 7, "0");
				sequence = "ACT-" + dateformat + "-" + order;
				break;
			case ACTIVITY_CP:
				// ACT-CP-yyyyMMdd-0000000
				dateformat = DateTimeUtils.formatDate(cal.getTime(), DateTimeUtils.DATE_FORMAT_YYYYMMDD);
				order = StringUtils.leftPad(seq.toString(), 7, "0");
				sequence = "ACT-CP-" + dateformat + "-" + order;
				break;	
			case ACTIVITY_INC:
				// ACT-INC-yyyyMMdd-0000000
				dateformat = DateTimeUtils.formatDate(cal.getTime(), DateTimeUtils.DATE_FORMAT_YYYYMMDD);
				order = StringUtils.leftPad(seq.toString(), 7, "0");
				sequence = "ACT-INC-" + dateformat + "-" + order;
				break;	
			case ACTIVITY_SR:
				// ACT-SR-yyyyMMdd-0000000
				dateformat = DateTimeUtils.formatDate(cal.getTime(), DateTimeUtils.DATE_FORMAT_YYYYMMDD);
				order = StringUtils.leftPad(seq.toString(), 7, "0");
				sequence = "ACT-SR-" + dateformat + "-" + order;
				break;		
			case ASSET:
				order = StringUtils.leftPad(seq.toString(), 8, "0");
				sequence = "ASST-" + order;
				break;
			case CONSULTING:
				// CT-yyyyMMdd-000000
				dateformat = DateTimeUtils.formatDate(cal.getTime(), DateTimeUtils.DATE_FORMAT_YYYYMMDD);
				order = StringUtils.leftPad(seq.toString(), 6, "0");
				sequence = "CT-" + dateformat + "-" + order;
				break;
			case SELFI:
				order = StringUtils.leftPad(seq.toString(), 3, "0");
				sequence = "SELFINTRO-" + order;
				break;
			case CATEGORY1 :
				order = StringUtils.leftPad(seq.toString(), 8, "0");
				sequence = "CAT1-" + order;
				break;
			case CATEGORY2 :
				order = StringUtils.leftPad(seq.toString(), 8, "0");
				sequence = "CAT2-" + order;
				break;
			case CATEGORY3 :
				order = StringUtils.leftPad(seq.toString(), 8, "0");
				sequence = "CAT3-" + order;
				break;
			case CATEGORY4 :
				order = StringUtils.leftPad(seq.toString(), 8, "0");
				sequence = "CAT4-" + order;
				break;
			case CATEGORY5 :
				order = StringUtils.leftPad(seq.toString(), 8, "0");
				sequence = "CAT5-" + order;
				break;
			case KB :
				// KB-00000000
				order = StringUtils.leftPad(seq.toString(), 8, "0");
				sequence = "KB-" + order;
				break;
			case FAQ:
				// FAQ-00000000
				order = StringUtils.leftPad(seq.toString(), 8, "0");
				sequence = "FAQ-" + order;
				break;
			case GEN:
				// GEN-00000000
				order = StringUtils.leftPad(seq.toString(), 8, "0");
				sequence = "GEN-" + order;
				break;
			default:
				order = StringUtils.leftPad(seq.toString(), 6, "0");
				sequence = sequenceType.name()+"-" + order;
				break;
		}
		
		return sequence;
	}
	
}
