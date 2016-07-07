package com.locus.jlo.web.bean.dto;

import java.util.Date;
import java.util.List;

public class RiskDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2841133740346651701L;
	
	private Long id;
	private Integer type;
	private String title;
	private Integer ageFrom;
	private Integer ageTo;
	private String sex;
	private Integer salaryMin;
	private Integer salaryMax;
	
	// มะเร็ง
	private Integer cancerMin;
	private Integer cancerMax;
	// โรคอื่นๆ
	private Integer otherMin;
	private Integer otherMax;
	// รักษาเจ็บป่วย
	private Integer sicknessMin;
	private Integer sicknessMax;
	// อุบัติเหตุ
	private Integer accidentMin;
	private Integer accidentMax;
	// พิการ
	private Integer disabledMin;
	private Integer disabledMax;
	// ตาย
	private Integer deathMin;
	private Integer deathMax;
	
	private Integer enabled;
	private String regId;
	private Date regDt;
	private String chgId;
	private Date chgDt;
	
	private RiskAttDTO riskAtt;
	private String regDtText;
	private String chgDtText;
	
	private List<String> childCd;
	private List<String> hraCd;
	private List<String> maritalStatusCd;
	private List<String> occupationCd;
	private List<String> regionCode;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getAgeFrom() {
		return ageFrom;
	}
	public void setAgeFrom(Integer ageFrom) {
		this.ageFrom = ageFrom;
	}
	public Integer getAgeTo() {
		return ageTo;
	}
	public void setAgeTo(Integer ageTo) {
		this.ageTo = ageTo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getSalaryMin() {
		return salaryMin;
	}
	public void setSalaryMin(Integer salaryMin) {
		this.salaryMin = salaryMin;
	}
	public Integer getSalaryMax() {
		return salaryMax;
	}
	public void setSalaryMax(Integer salaryMax) {
		this.salaryMax = salaryMax;
	}
	public Integer getCancerMin() {
		return cancerMin;
	}
	public void setCancerMin(Integer cancerMin) {
		this.cancerMin = cancerMin;
	}
	public Integer getCancerMax() {
		return cancerMax;
	}
	public void setCancerMax(Integer cancerMax) {
		this.cancerMax = cancerMax;
	}
	public Integer getOtherMin() {
		return otherMin;
	}
	public void setOtherMin(Integer otherMin) {
		this.otherMin = otherMin;
	}
	public Integer getOtherMax() {
		return otherMax;
	}
	public void setOtherMax(Integer otherMax) {
		this.otherMax = otherMax;
	}
	public Integer getSicknessMin() {
		return sicknessMin;
	}
	public void setSicknessMin(Integer sicknessMin) {
		this.sicknessMin = sicknessMin;
	}
	public Integer getSicknessMax() {
		return sicknessMax;
	}
	public void setSicknessMax(Integer sicknessMax) {
		this.sicknessMax = sicknessMax;
	}
	public Integer getAccidentMin() {
		return accidentMin;
	}
	public void setAccidentMin(Integer accidentMin) {
		this.accidentMin = accidentMin;
	}
	public Integer getAccidentMax() {
		return accidentMax;
	}
	public void setAccidentMax(Integer accidentMax) {
		this.accidentMax = accidentMax;
	}
	public Integer getDisabledMin() {
		return disabledMin;
	}
	public void setDisabledMin(Integer disabledMin) {
		this.disabledMin = disabledMin;
	}
	public Integer getDisabledMax() {
		return disabledMax;
	}
	public void setDisabledMax(Integer disabledMax) {
		this.disabledMax = disabledMax;
	}
	public Integer getDeathMin() {
		return deathMin;
	}
	public void setDeathMin(Integer deathMin) {
		this.deathMin = deathMin;
	}
	public Integer getDeathMax() {
		return deathMax;
	}
	public void setDeathMax(Integer deathMax) {
		this.deathMax = deathMax;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getChgId() {
		return chgId;
	}
	public void setChgId(String chgId) {
		this.chgId = chgId;
	}
	public Date getChgDt() {
		return chgDt;
	}
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
	public RiskAttDTO getRiskAtt() {
		return riskAtt;
	}
	public void setRiskAtt(RiskAttDTO riskAtt) {
		this.riskAtt = riskAtt;
	}
	public String getRegDtText() {
		return regDtText;
	}
	public void setRegDtText(String regDtText) {
		this.regDtText = regDtText;
	}
	public String getChgDtText() {
		return chgDtText;
	}
	public void setChgDtText(String chgDtText) {
		this.chgDtText = chgDtText;
	}
	public List<String> getChildCd() {
		return childCd;
	}
	public void setChildCd(List<String> childCd) {
		this.childCd = childCd;
	}
	public List<String> getHraCd() {
		return hraCd;
	}
	public void setHraCd(List<String> hraCd) {
		this.hraCd = hraCd;
	}
	public List<String> getMaritalStatusCd() {
		return maritalStatusCd;
	}
	public void setMaritalStatusCd(List<String> maritalStatusCd) {
		this.maritalStatusCd = maritalStatusCd;
	}
	public List<String> getOccupationCd() {
		return occupationCd;
	}
	public void setOccupationCd(List<String> occupationCd) {
		this.occupationCd = occupationCd;
	}
	public List<String> getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(List<String> regionCode) {
		this.regionCode = regionCode;
	}
	
}
