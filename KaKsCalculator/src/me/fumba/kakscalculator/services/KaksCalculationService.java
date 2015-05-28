package me.fumba.kakscalculator.services;

import org.apache.commons.lang3.StringUtils;

import me.fumba.kakscalculator.common.ApplicationConstants;

public class KaksCalculationService implements ApplicationConstants {

	public String errorMessage;

	public String computeRatios(String originalSequence, String mutatedSequence) {
		String result = COMPUTE_SUCCESS;
		if (StringUtils
				.isNotBlank(checkForUnconventionalNucleotides(originalSequence))) {
			this.setErrorMessage("Original Sequence has unconventional nucleotides.");
			return COMPUTE_ERROR;
		}
		return result;
	}

	/**
	 * Checks of the sequence provided has conventional nucleotides.
	 * 
	 * @param originalSequence
	 * @return
	 */
	private String checkForUnconventionalNucleotides(String sequence) {
		boolean invalidSequence = false;
		StringBuffer buffer = new StringBuffer();
		for (Character nucleotide : sequence.toCharArray()) {
			if (ADENINE.equals(nucleotide) || GUANINE.equals(nucleotide)
					|| CYTOSINE.equals(nucleotide) || URACIL.equals(nucleotide)) {
				buffer.append(nucleotide);
			} else {
				invalidSequence = true;
				buffer.append("<div style='font-size: 12px font-color: red'>"
						+ nucleotide + "</div>");
			}
		}
		return invalidSequence ? buffer.toString() : EMPTY;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
