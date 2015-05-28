package me.fumba.kakscalcusadslator.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Namespace("")
@ResultPath(value="/")

public class CalculateAction extends ActionSupport{

	private static final long serialVersionUID = 6269198833217107593L;
	private String pageName;
	private String userName;
	private String password;

	
	@Action(value = "calculate", results = {
	        @Result(name = "success", location = "/results.jsp"),
	        @Result(name = "error", location = "/error.jsp") })
	
	@Override
	public String execute() throws Exception {
		dsaasda
		sdadsa
		dsadsa
		
/*
		if (pageName != null && studentService != null) {
			if (pageName.equals("login")) {
				result = studentService.findByLogin(userName, password);
				if (result.equals("LoginFailure")) {
					return "failure";
				} else {
					return "success";
				}
			}
		}*/
		return SUCCESS;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getUserName() {
		return userName;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "UserName is a required field")
	@StringLengthFieldValidator(type = ValidatorType.FIELD, maxLength = "12", minLength = "6", message = "UserName must be of length between 6 and 12")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Password is a required field")
	@StringLengthFieldValidator(type = ValidatorType.FIELD, maxLength = "12", minLength = "6", message = "Password must be of length between 6 and 12")
	public void setPassword(String password) {
		this.password = password;
	}

}