package me.fumba.kakscalculator.common;

public interface ApplicationConstants {

	/**
	 * The name of the form used used to submit the sequences provided by the
	 * user.
	 */
	String KA_KS_FORM = "kaksform";
	String COMPUTE_ERROR = "ComputeError";
	String COMPUTE_SUCCESS = "ComputeSuccess";
	String EMPTY = "";

	String SYNONYMOUS = "S";
	String NON_SYNONYMOUS = "N";
	String P_VALUES = "pValues";
	String Q_VALUES = "qValues";

	// Nucleotides
	String ADENINE = "A";
	String GUANINE = "G";
	String CYTOSINE = "C";
	String URACIL = "U";
	String THYMINE = "T";

	// Amino Acids
	String PHENYLALANINE = "F";
	String ISOLEUCINE = "L";
	String METHIONINE = "I";
	String VALINE = "V";
	String SERINE1 = "S1";
	String SERINE2 = "S2";
	String PROLINE = "P";
	String THREONINE = "T";
	String ALANINE = "A";
	String TYROSINE = "Y";
	String STOP_CODON = ".";
	String HISTIDINE = "H";
	String GLUTAMINE = "Q";
	String ASPARAGINE = "N";
	String LYSINE = "K";
	String ASPARTIC_ACID = "D";
	String GLUTAMIC_ACID = "E";
	String CYSTEINE = "C";
	String TRYPTOPHAN = "W";
	String ARGININE = "R";
	String GLYCINE = "G";
	String LEUCINE = "L";

	// Degeneracy counts
	String COUNT_0 = "Count0";
	String COUNT_2 = "Count2";
	String COUNT_4 = "Count4";
	Character CHAR_0 = '0';
	Character CHAR_2 = '2';
	Character CHAR_4 = '4';
	String L0_AVERAGE_COUNT = "AverageCount0";
	String L2_AVERAGE_COUNT = "AverageCount2";
	String L4_AVERAGE_COUNT = "AverageCount4";

	String TRANSITIONAL_SUBSTITUTIONS = "Ai";
	String TRANSITIONAL_SUBSTITUTIONS_ERROR_VARIANCE = "VAi";
	String TRANSVERSION_SUBSTITUTIONS = "Bi";
	String TRANSVERSION_SUBSTITUTIONS_ERROR_VARIANCE = "VBi";
	String TOTAL_SUBSTITUTIONS = "Ki";
	String TOTAL_SUBSTITUTIONS_ERROR_VARIANCE = "VKi";
	String TRANSITION = "Pi";
	String TRANSVERSION = "Qv";

	// NSX
	Character CHAR_N = 'N';
	Character CHAR_S = 'S';
	Character CHAR_X = 'X';

}
