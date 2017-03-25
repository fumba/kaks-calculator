package me.fumba.kakscalculator.actions;

import me.fumba.kakscalculator.services.KaksCalculationService;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import me.fumba.kakscalculator.common.ApplicationConstants;
import me.fumba.kakscalculator.common.GetPropertyValues;
import me.fumba.kakscalculator.common.VerifyUtils;

@ResultPath(value = "/")
public class CalculateAction extends ActionSupport implements ApplicationConstants {

	private static final long serialVersionUID = 6269198833217107593L;
	private String pageName;
	private String originalSequence;
	private String mutatedSequence;
	private String errorMessage;

	// NG - Method variables.
	private double ngKa;
	private double ngKs;
	private double ngKaKs;

	// LWL variables
	private double lwlKa;
	private double lwlKs;
	private double lwlKaKs;
	private double lwlVKa;
	private double lwlVKs;

	// Both NG and LWL
	private double mlwlKa;
	private double mlwlKs;
	private double mlwlKaKs;

	@Action(value = "calculate", results = { @Result(name = "success", location = "/results.jsp"),
			@Result(name = "input", location = "/kaksform.jsp"), @Result(name = "error", location = "/error.jsp") })
	@Override
	public String execute() throws Exception {

		String gRecaptchaResponse = ServletActionContext.getRequest().getParameter("g-recaptcha-response");
		boolean valid = VerifyUtils.verify(gRecaptchaResponse);
		if (!valid) {
			addActionError("Captcha Mismatched! Try Again");
			return INPUT;
		}

		String result = ERROR;
		GetPropertyValues propertyValues = new GetPropertyValues();
		KaksCalculationService calculationService = new KaksCalculationService();
		if (pageName != null && calculationService != null) {
			if (pageName.equals(KA_KS_FORM)) {
				result = calculationService.computeRatios(originalSequence, mutatedSequence);
				if (result.equals(COMPUTE_ERROR)) {
					this.setErrorMessage(calculationService.getErrorMessage());
					return ERROR;
				} else {

					// Jukes-Cantor (JC) method
					this.setNgKa(calculationService.getNgKa());
					this.setNgKs(calculationService.getNgKs());
					this.setNgKaKs(calculationService.getNgKaKs());

					// Kimuras - 2 parameter (K2P) model - LWL
					this.setLwlKs(calculationService.getLwlKs());
					this.setLwlVKs(calculationService.getLwlKs());
					this.setLwlKa(calculationService.getLwlKa());
					this.setLwlVKa(calculationService.getLwlVKa());
					this.setLwlKaKs(calculationService.getLwlKaKs());

					// Both JK and K2P Models
					this.setMlwlKa(calculationService.getLwlKa());
					this.setMlwlKs(calculationService.getMlwlKs());
					this.setMlwlKaKs(calculationService.getMlwlKaKs());
					return SUCCESS;
				}
			}
		}
		this.setErrorMessage(propertyValues.getPropValues("access_denied"));
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
	@FieldExpressionValidator(expression = "originalSequence.length() % 3 == 0", message = "Sequence length is not divisible by 3.")
	public void setOriginalSequence(String originalSequence) {
		this.originalSequence = originalSequence;
	}

	public String getMutatedSequence() {
		return mutatedSequence;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Mutated Sequence needs to be provided.")
	@FieldExpressionValidator(expression = "mutatedSequence.length() % 3 == 0", message = "Sequence length is not divisible by 3.")
	public void setMutatedSequence(String mutatedSequence) {
		this.mutatedSequence = mutatedSequence;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public double getNgKa() {
		return ngKa;
	}

	public void setNgKa(double ngKa) {
		this.ngKa = ngKa;
	}

	public double getNgKs() {
		return ngKs;
	}

	public void setNgKs(double ngKs) {
		this.ngKs = ngKs;
	}

	public double getNgKaKs() {
		return ngKaKs;
	}

	public void setNgKaKs(double ngKaKs) {
		this.ngKaKs = ngKaKs;
	}

	public double getLwlKa() {
		return lwlKa;
	}

	public void setLwlKa(double lwlKa) {
		this.lwlKa = lwlKa;
	}

	public double getLwlKs() {
		return lwlKs;
	}

	public void setLwlKs(double lwlKs) {
		this.lwlKs = lwlKs;
	}

	public double getLwlKaKs() {
		return lwlKaKs;
	}

	public void setLwlKaKs(double lwlKaKs) {
		this.lwlKaKs = lwlKaKs;
	}

	public double getLwlVKa() {
		return lwlVKa;
	}

	public void setLwlVKa(double lwlVKa) {
		this.lwlVKa = lwlVKa;
	}

	public double getLwlVKs() {
		return lwlVKs;
	}

	public void setLwlVKs(double lwlVKs) {
		this.lwlVKs = lwlVKs;
	}

	public double getMlwlKa() {
		return mlwlKa;
	}

	public void setMlwlKa(double mlwlKa) {
		this.mlwlKa = mlwlKa;
	}

	public double getMlwlKs() {
		return mlwlKs;
	}

	public void setMlwlKs(double mlwlKs) {
		this.mlwlKs = mlwlKs;
	}

	public double getMlwlKaKs() {
		return mlwlKaKs;
	}

	public void setMlwlKaKs(double mlwlKaKs) {
		this.mlwlKaKs = mlwlKaKs;
	}
}