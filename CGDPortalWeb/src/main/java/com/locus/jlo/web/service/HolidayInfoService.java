package com.locus.jlo.web.service;

import java.util.List;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.HolidayDTO;

public interface HolidayInfoService {
	ServiceResult<List<HolidayDTO>> searchHolidayByYear(Integer year, String langCd);
	ServiceResult<HolidayDTO> searchHolidayById(Integer holidayId);
	HolidayDTO insertHoliday(HolidayDTO tpHolidayInfo);
	HolidayDTO updateHoliday(HolidayDTO tpHolidayInfo);
	void deleteHoliday(Integer holidayId);
}
