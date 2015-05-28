package me.fumba.kakscalculator.actions;

import me.fumba.kakscalculator.services.KaksCalculationService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import me.fumba.kakscalculator.common.ApplicationConstants;

@ResultPath(value = "/")
public class CalculateAction extends ActionSupport implements
		ApplicationConstants {

	private static final long serialVersionUID = 6269198833217107593L;
	private String pageName;
	private String originalSequence;
	private String mutatedSequence;
	private String errorMessage;

	@Action(value = "calculate", results = {
			@Result(name = "success", location = "/results.jsp"),
			@Result(name = "input", location = "/kaksform.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	@Override
	public String execute() throws Exception {

		String result = ERROR;
		KaksCalculationService calculationService = new KaksCalculationService();
		if (pageName != null && calculationService != null) {
			if (pageName.equals(KA_KS_FORM)) {
				result = calculationService.computeRatios(originalSequence,
						mutatedSequence);
				if (result.equals(COMPUTE_ERROR)) {
					this.setErrorMessage(calculationService.getErrorMessage());
					return "error";
				} else {
					return "success";
				}
			}
		}
		return result;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getOriginalSequence() {
		return originalSequence;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Original Sequence needs to be provided.")
	public void setOriginalSequence(String originalSequence) {
		this.originalSequence = originalSequence;
	}

	public String getMutatedSequence() {
		return mutatedSequence;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Mutated Sequence needs to be provided.")
	public void setMutatedSequence(String mutatedSequence) {
		this.mutatedSequence = mutatedSequence;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}