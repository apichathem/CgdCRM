package com.locus.jlo.rest.bean;

public class OpportunityInvestmentBean {

	private String oppt_invest_id;
	private String oppt_id;
	private String invest_code;
	private String benefit;
	private String create_date;
	private String update_date;
	private String deleted;
	private String sync_status;

	public String getOppt_invest_id() {
		return oppt_invest_id;
	}

	public void setOppt_invest_id(String oppt_invest_id) {
		this.oppt_invest_id = oppt_invest_id;
	}

	public String getOppt_id() {
		return oppt_id;
	}

	public void setOppt_id(String oppt_id) {
		this.oppt_id = oppt_id;
	}

	public String getInvest_code() {
		return invest_code;
	}

	public void setInvest_code(String invest_code) {
		this.invest_code = invest_code;
	}

	public String getBenefit() {
		return benefit;
	}

	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getSync_status() {
		return sync_status;
	}

	public void setSync_status(String sync_status) {
		this.sync_status = sync_status;
	}

}
