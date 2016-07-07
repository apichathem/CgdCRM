package com.locus.jlo.web.view;

import org.springframework.web.servlet.view.AbstractView;

public abstract class BaseCsvAbstractView extends AbstractView {

	protected String setData(Object obj) {
		
		return setData(obj, Boolean.FALSE);
	}
	
	protected String setData(Object obj, Boolean noComma) {
		String seperate = "";
		if (!noComma) {
			seperate = ",";
		}
		String res = "";
		if (obj == null) {
			res = "\"\"" + seperate;
		} else if (obj instanceof String) {
			String text = String.valueOf(obj);
			res = "\"" + text + "\"" + seperate;
		} else if (obj instanceof Integer) {
			Integer number = (Integer) (obj);
			res = number + seperate;
		} else {
			
		}
		
		return res;
	}
}
