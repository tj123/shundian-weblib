package com.shundian.weblib.bean.validate;

import java.util.ArrayList;
import java.util.List;

/**
 * 错误时报的异常
 * Created by TJ on 2016/3/2.
 */
public class NotValidException extends Exception{

	private static final long serialVersionUID = 1L;

	public NotValidException() {
        this(ValidateUtil.NOT_VALID_MESSAGE);
        this.message = ValidateUtil.NOT_VALID_MESSAGE;
    }

    public NotValidException(String message) {
        super(ValidateUtil.isBlank(message)?ValidateUtil.NOT_VALID_MESSAGE:message);
        this.message = message;
    }
    
    private String message;
	private List<Error> errors = new ArrayList<>();
	
    public List<Error> getErrors() {
		return errors;
	}

    public NotValidException addError(Error error){
    	errors.add(error);
    	return this;
    }

    public NotValidException setMessage(String message){
    	if(ValidateUtil.isBlank(message)) message = ValidateUtil.NOT_VALID_MESSAGE;
    	this.message = message;
    	return this;
    }
    
	@Override
	public String getMessage() {
		return message;
	}
	
	public boolean hasError(){
		return !errors.isEmpty();
	}
    
}
