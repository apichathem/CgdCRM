package com.locus.jlo.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.UserLoginHistoryDTO;
import com.locus.jlo.web.bean.dto.UserStaticDTO;


public interface UserLoginHistoryService {
	void insert(UserLoginHistoryDTO userLoginHistoryDTO);
	ServiceResult<Page<UserLoginHistoryDTO>> searchByUserId(String loginId, Pageable pageable);
	ServiceResult<List<UserStaticDTO>> getUserLoginStatByDate(Date dateInput);
}
