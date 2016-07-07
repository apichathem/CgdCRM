package com.locus.jlo.web.service;

import com.locus.jlo.web.constant.SequenceType;

public interface SequenceGeneratorService {
	String generateByType(SequenceType sequenceType);
}
