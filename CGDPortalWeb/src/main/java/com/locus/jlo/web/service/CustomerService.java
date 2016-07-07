package com.locus.jlo.web.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.criteria.AddressCriteria;
import com.locus.jlo.web.bean.criteria.ContactCriteria;
import com.locus.jlo.web.bean.criteria.CustomerCriteria;
import com.locus.jlo.web.bean.dto.AddressDTO;
import com.locus.jlo.web.bean.dto.ContactDTO;
import com.locus.jlo.web.bean.dto.CustomerAssetDTO;
import com.locus.jlo.web.bean.dto.CustomerComplaintDTO;
import com.locus.jlo.web.bean.dto.CustomerDTO;
//import com.locus.jlo.web.bean.dto.CustomerIncidentDTO;
import com.locus.jlo.web.bean.dto.CustomerServiceRequestDTO;
import com.locus.jlo.web.bean.dto.SlaDTO;

public interface CustomerService {
	ServiceResult<Page<CustomerDTO>> searchCustomer(Pageable pageable, CustomerCriteria criteria, String langCd);
	ServiceResult<CustomerDTO> searchCustomerDetailIndividual(Integer custId);
	ServiceResult<CustomerDTO> searchCustomerDetailCorporate(Integer custId);
	ServiceResult<CustomerDTO> insertIndividual(CustomerDTO dto);
	ServiceResult<CustomerDTO> searchMoreInfoIndividual(Long custId);
	ServiceResult<Integer> updateMoreInfo(CustomerDTO dto);
	ServiceResult<Integer> updateIndividual(CustomerDTO dto);
	ServiceResult<CustomerDTO> insertCorporation(CustomerDTO dto);
	ServiceResult<Integer> updateCorporation(CustomerDTO dto); 
	ServiceResult<CustomerDTO> searchCustomerDetailByCode(String custCode);

	// Contact
	ServiceResult<Page<ContactDTO>> searchContactList(Pageable pageable, ContactCriteria contactCriteria);
	ServiceResult<ContactDTO> searchContactById(Integer custId, Integer contId);
	int countCustomerContact(Integer custId, Integer contId);
	ServiceResult<ContactDTO> insertContact(ContactDTO contactDTO);
	ServiceResult<ContactDTO> insertCustomerContact(ContactDTO contactDTO);
	ServiceResult<Page<ContactDTO>> searchCustomerContactList(Pageable pageable, Integer custId, String langCd);
	Boolean deleteCustomerContact(Integer custId, Integer contId);
	Boolean updateCustomerContact(ContactDTO contactDTO);
	
	// Address
	ServiceResult<Page<AddressDTO>> searchAddressList(Pageable pageable, AddressCriteria addressCriteria, String langCd);
	ServiceResult<Page<AddressDTO>> searchCustomerAddressList(Pageable pageable, Integer custId, String langCd);
	ServiceResult<AddressDTO> searchAddressById(Integer custId, Integer addrId);
	ServiceResult<AddressDTO> insertAddress(AddressDTO addressDTO);
	ServiceResult<AddressDTO> insertCustomerAddress(AddressDTO addressDTO);
	int countCustomerAddress(Integer custId, Integer addrId);
	Boolean deleteCustomerAddress(Integer custId, Integer addrId);
	Boolean updateCustomerAddress(AddressDTO addressDTO);
	Boolean updatePrimaryAddress(Integer custId, Integer addrId);
	
	// SR, CP, INC, ASSET
	ServiceResult<Page<CustomerServiceRequestDTO>> searchCustomerServiceRequestList(Pageable pageable, Integer custId, String langCd);
	ServiceResult<Page<CustomerComplaintDTO>> searchCustomerComplaintList(Pageable pageable, Integer custId, String langCd);
	//ServiceResult<Page<CustomerIncidentDTO>> searchCustomerIncidentList(Pageable pageable, Integer custId, String langCd);
	ServiceResult<Page<CustomerAssetDTO>> searchCustomerAssetList(Pageable pageable, Integer custId, String langCd);
	
	ServiceResult<List<ContactDTO>> getContactByCustIdList(Integer custId);
}
