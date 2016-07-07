package com.locus.jlo.web.service;

import com.locus.crm.ws.client.thaiquest.SearchResult;
import com.thaiquest.ws.inject.DocumentProperty;
import com.thaiquest.ws.inject.InjectResult;

public interface ThaiQuestWsService {
	SearchResult doSearch2(String strQuery, String strCategories) throws Exception;

	SearchResult doSearch7(String strQuery, String strCategories) throws Exception;

	InjectResult doAdd(DocumentProperty document);

	InjectResult doUpdate(DocumentProperty document);

	InjectResult doDelete(String id);
}
