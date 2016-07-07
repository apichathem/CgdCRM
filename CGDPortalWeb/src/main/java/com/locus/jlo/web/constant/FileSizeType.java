package com.locus.jlo.web.constant;

public enum FileSizeType {
	FIVE(5, 5242880), TEN(10, 10485760), TWENTY(20, 20971520);
	
	private FileSizeType(Integer i, Integer j) {
		value = i;
		sizing = j;
	}
	
	private Integer value;
	private Integer sizing;
	
	public Integer getValue() {
		return value;
	}

	public Integer getSizing() {
		return sizing;
	}
	
	
}
