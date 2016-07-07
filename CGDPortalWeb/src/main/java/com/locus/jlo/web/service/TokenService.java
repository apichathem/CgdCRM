package com.locus.jlo.web.service;

import com.locus.common.domain.ServiceResult;
import com.locus.jlo.web.bean.dto.TokenDTO;


public interface TokenService {

	ServiceResult<TokenDTO> generateToken(TokenDTO tokenDTO);

	ServiceResult<Integer> destroyToken(TokenDTO token);

	ServiceResult<Integer> validateToken(TokenDTO tokenRequest);
	
	ServiceResult<Integer> updatePasscode(TokenDTO token);

	ServiceResult<TokenDTO> getPasscode(TokenDTO token);

}
