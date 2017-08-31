package com.zscat.marketing.web.converter;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

/**
* @Title: MultiDateParseEditor.java 
* @Package com.enker.core.converter.formEditer 
* @Description: 日期转换器
* @author liuyang   
* @date 2016年8月4日 下午3:25:55 
* @version V1.0   
*/
public class MultiDateParseEditor extends PropertyEditorSupport {
	//日期格式转换器集合
	private List<DateFormat> dateFormats;
	//是否允许为空
	private boolean allowEmpty;
	//可解析的日期字符串允许的最大长度
	private final int exactDateLength;
	
	public MultiDateParseEditor(List<DateFormat> dateFormats, boolean allowEmpty){
		 if (dateFormats== null || dateFormats.size() == 0){  
	            throw new IllegalArgumentException("Param dateFormats could not be empty");  
	     }  
	     this.dateFormats = dateFormats;  
	     this.allowEmpty = allowEmpty;  
	     this.exactDateLength = -1;  
	}
	
	public MultiDateParseEditor(List<DateFormat> dateFormats, boolean allowEmpty, int exactDateLength){  
        if (dateFormats== null || dateFormats.size() == 0){  
            throw new IllegalArgumentException("Param dateFormats could not be empty");  
        }  
        this.dateFormats = dateFormats;  
        this.allowEmpty = allowEmpty;  
        this.exactDateLength = exactDateLength;  
    }  
	
	@Override  
    public void setAsText(String text) throws IllegalArgumentException {  
        if (this.allowEmpty && StringUtils.isBlank(text)) {  
            // Treat empty String as null value.  
            setValue(null);  
        }else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {  
            throw new IllegalArgumentException(  
                    "Could not parse date: it is not exactly" + this.exactDateLength + "characters long");  
        }else {  
            ParseException lastException = null;  
            for (DateFormat dateFormat : dateFormats) {  
                try {  
                    setValue(dateFormat.parse(text));  
                    return;  
                } catch (ParseException e) {  
                    lastException = e;  
                }  
            }  
            throw new IllegalArgumentException("Could not parse date: " + lastException.getMessage(), lastException);  
        }  
    }  
	
	
}
