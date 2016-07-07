package com.locus.jlo.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.criteria.ContactCriteria;
import com.locus.jlo.web.bean.dto.ConsultingDTO;
import com.locus.jlo.web.bean.dto.ConsultingRelDTO;
import com.locus.jlo.web.bean.dto.ContactDTO;

public interface ConsultingService {

	ServiceResult<ConsultingDTO> insertInital(ConsultingDTO inital);
	ServiceResult<Long> update(ConsultingDTO dto);
	ServiceResult<Page<ConsultingDTO>> find(ConsultingDTO obj, Pageable pageable);
	ServiceResult<Long> insertTpRelConsulting(ConsultingRelDTO inital);
	ServiceResult<ConsultingDTO> searchConsultingCustomerById(String custId);
	ServiceResult<Page<ContactDTO>> searchContactList(Pageable pageable,
			ContactCriteria contactCriteria, String custId);
}
