package me.fumba.kakscalculator.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import me.fumba.kakscalculator.common.ApplicationConstants;

public class KaksCalculationService implements ApplicationConstants {

	private String errorMessage;

	// NG - Method variables.
	private double ngKa;
	private double ngKs;
	private double ngKaKs;

	// LWL - Model variables
	private double lwlKa;
	private double lwlKs;
	private double lwlKaKs;
	private double lwlVKa;
	private double lwlVKs;

	// Both NG and LWL
	private double mlwlKa;
	private double mlwlKs;
	private double mlwlKaKs;

	private Map<String, String> nsxMap;
	private Map<String, String> aminoMap;
	private Map<String, String> codonDegreeMap;

	public KaksCalculationService() {

		// NSX Map
		nsxMap = new HashMap<String, String>();
		nsxMap.put(PHENYLALANINE, "NNX");
		nsxMap.put(LEUCINE, "XNX");
		nsxMap.put(ISOLEUCINE, "NNX");
		nsxMap.put(METHIONINE, "NNN");
		nsxMap.put(VALINE, "NNS");
		nsxMap.put(SERINE1, "NNS");
		nsxMap.put(SERINE2, "XNX");
		nsxMap.put(PROLINE, "NNS");
		nsxMap.put(THREONINE, "NNS");
		nsxMap.put(ALANINE, "NNS");
		nsxMap.put(TYROSINE, "NNX");
		nsxMap.put(STOP_CODON, "NXX");
		nsxMap.put(HISTIDINE, "NNX");
		nsxMap.put(GLUTAMINE, "NNX");
		nsxMap.put(ASPARAGINE, "NNX");
		nsxMap.put(LYSINE, "NNX");
		nsxMap.put(ASPARTIC_ACID, "NNX");
		nsxMap.put(GLUTAMIC_ACID, "NNX");
		nsxMap.put(CYSTEINE, "NNX");
		nsxMap.put(TRYPTOPHAN, "NNN");
		nsxMap.put(ARGININE, "XNX");
		nsxMap.put(GLYCINE, "NNS");

		// Amino acid Map
		aminoMap = new HashMap<String, String>();
		aminoMap.put("UUU", PHENYLALANINE);
		aminoMap.put("UUC", PHENYLALANINE);
		aminoMap.put("UUA", LEUCINE);
		aminoMap.put("UUG", LEUCINE);
		aminoMap.put("CUU", LEUCINE);
		aminoMap.put("CUC", LEUCINE);
		aminoMap.put("CUA", LEUCINE);
		aminoMap.put("CUG", LEUCINE);
		aminoMap.put("AUU", ISOLEUCINE);
		aminoMap.put("AUC", ISOLEUCINE);
		aminoMap.put("AUA", ISOLEUCINE);
		aminoMap.put("AUG", METHIONINE);
		aminoMap.put("GUU", VALINE);
		aminoMap.put("GUC", VALINE);
		aminoMap.put("GUA", VALINE);
		aminoMap.put("GUG", VALINE);
		aminoMap.put("UCU", SERINE1);
		aminoMap.put("UCC", SERINE1);
		aminoMap.put("UCA", SERINE1);
		aminoMap.put("UCG", SERINE1);
		aminoMap.put("CCU", PROLINE);
		aminoMap.put("CCC", PROLINE);
		aminoMap.put("CCA", PROLINE);
		aminoMap.put("CCG", PROLINE);
		aminoMap.put("ACU", THREONINE);
		aminoMap.put("ACC", THREONINE);
		aminoMap.put("ACA", THREONINE);
		aminoMap.put("ACG", THREONINE);
		aminoMap.put("GCU", ALANINE);
		aminoMap.put("GCC", ALANINE);
		aminoMap.put("GCA", ALANINE);
		aminoMap.put("GCG", ALANINE);
		aminoMap.put("UAU", TYROSINE);
		aminoMap.put("UAC", TYROSINE);
		aminoMap.put("UAA", STOP_CODON);
		aminoMap.put("UAG", STOP_CODON);
		aminoMap.put("UGA", STOP_CODON);
		aminoMap.put("CAU", HISTIDINE);
		aminoMap.put("CAC", HISTIDINE);
		aminoMap.put("CAA", GLUTAMINE);
		aminoMap.put("CAG", GLUTAMINE);
		aminoMap.put("AAU", ASPARAGINE);
		aminoMap.put("AAC", ASPARAGINE);
		aminoMap.put("AAA", LYSINE);
		aminoMap.put("AAG", LYSINE);
		aminoMap.put("GAU", ASPARTIC_ACID);
		aminoMap.put("GAC", ASPARTIC_ACID);
		aminoMap.put("GAA", GLUTAMIC_ACID);
		aminoMap.put("GAG", GLUTAMIC_ACID);
		aminoMap.put("UGU", CYSTEINE);
		aminoMap.put("UGC", CYSTEINE);
		aminoMap.put("UGG", TRYPTOPHAN);
		aminoMap.put("CGU", ARGININE);
		aminoMap.put("CGC", ARGININE);
		aminoMap.put("CGA", ARGININE);
		aminoMap.put("CGG", ARGININE);
		aminoMap.put("AGU", SERINE2);
		aminoMap.put("AGC", SERINE2);
		aminoMap.put("AGA", ARGININE);
		aminoMap.put("AGG", ARGININE);
		aminoMap.put("GGU", GLYCINE);
		aminoMap.put("GGC", GLYCINE);
		aminoMap.put("GGA", GLYCINE);
		aminoMap.put("GGG", GLYCINE);

		// Codon Degree Map
		codonDegreeMap = new HashMap<String, String>();
		codonDegreeMap.put(PHENYLALANINE, "002");
		codonDegreeMap.put(LEUCINE, "204");
		codonDegreeMap.put(ISOLEUCINE, "002");
		codonDegreeMap.put(METHIONINE, "000");
		codonDegreeMap.put(VALINE, "004");
		codonDegreeMap.put(SERINE1, "004");
		codonDegreeMap.put(SERINE2, "004");
		codonDegreeMap.put(PROLINE, "004");
		codonDegreeMap.put(THREONINE, "004");
		codonDegreeMap.put(ALANINE, "004");
		codonDegreeMap.put(TYROSINE, "002");
		codonDegreeMap.put(STOP_CODON, "022");
		codonDegreeMap.put(HISTIDINE, "002");
		codonDegreeMap.put(GLUTAMINE, "002");
		codonDegreeMap.put(ASPARAGINE, "002");
		codonDegreeMap.put(LYSINE, "002");
		codonDegreeMap.put(ASPARTIC_ACID, "002");
		codonDegreeMap.put(GLUTAMIC_ACID, "002");
		codonDegreeMap.put(CYSTEINE, "002");
		codonDegreeMap.put(TRYPTOPHAN, "000");
		codonDegreeMap.put(ARGININE, "204");
		codonDegreeMap.put(GLYCINE, "004");
	}

	/**
	 * Entry point for calculations.
	 * 
	 * @param originalSequence
	 * @param mutatedSequence
	 * @return
	 */
	public String computeRatios(String originalSequence, String mutatedSequence) {
		String markedUnconventionalNucleotides = checkForUnconventionalNucleotides(originalSequence);
		if (StringUtils.isNotBlank(markedUnconventionalNucleotides)) {
			this.setErrorMessage("Original Sequence has unconventional nucleotides.<br>"
					+ markedUnconventionalNucleotides);
			return COMPUTE_ERROR;
		}

		markedUnconventionalNucleotides = checkForUnconventionalNucleotides(mutatedSequence);
		if (StringUtils.isNotBlank(markedUnconventionalNucleotides)) {
			this.setErrorMessage("Mutated Sequence has unconventional nucleotides.<br>"
					+ markedUnconventionalNucleotides);
			return COMPUTE_ERROR;
		}

		String cleanOriginalSequence = this.convertDnaToRna(originalSequence);
		String cleanMutatedSequence = this.convertDnaToRna(mutatedSequence);

		// Calculation using the Jukes-Cantor (JC) model
		this.kaKsCalcNG(cleanOriginalSequence, cleanMutatedSequence);

		// Calculation using the Kimuras- two parameter (K2P) model
		this.kaKsCalcLWL(cleanOriginalSequence, cleanMutatedSequence);

		// Calculation using both JK and K2P models
		this.kaKsCalcMLWL(cleanOriginalSequence, cleanMutatedSequence);

		return COMPUTE_SUCCESS;
	}

	/**
	 * Calculation of Ka/Ks using both JK and K2P models.
	 * 
	 * Ks - JC
	 * 
	 * Ka = K2P
	 * 
	 * Correcting - K2P
	 * 
	 * @param cleanOriginalSequence
	 * @param cleanMutatedSequence
	 */
	private void kaKsCalcMLWL(String cleanOriginalSequence,
			String cleanMutatedSequence) {

		final double piQvRation = this.calcPiQvRatio(cleanOriginalSequence,
				cleanMutatedSequence);

		Map<String, Object> pQValues = this.calculatePQValues(
				cleanOriginalSequence, cleanMutatedSequence);
		final double L0 = (double) pQValues.get(L2_AVERAGE_COUNT);
		final double L2 = (double) pQValues.get(L2_AVERAGE_COUNT);
		final double L4 = (double) pQValues.get(L4_AVERAGE_COUNT);

		Map<String, Double> calculatedValues = this.calculateMeanVariance(
				CHAR_0, cleanOriginalSequence, cleanMutatedSequence, pQValues);
		final double K0 = calculatedValues.get(TOTAL_SUBSTITUTIONS);

		calculatedValues = this.calculateMeanVariance(CHAR_2,
				cleanOriginalSequence, cleanMutatedSequence, pQValues);
		final double A2 = calculatedValues.get(TRANSITIONAL_SUBSTITUTIONS);
		final double B2 = calculatedValues.get(TRANSVERSION_SUBSTITUTIONS);

		calculatedValues = this.calculateMeanVariance(CHAR_4,
				cleanOriginalSequence, cleanMutatedSequence, pQValues);
		final double K4 = calculatedValues.get(TOTAL_SUBSTITUTIONS);

		if (piQvRation >= 2) {
			this.setMlwlKa((L2 * B2 + L0 * K0)
					/ (((2 * L2) / ((piQvRation - 1) + 2)) + L0));
			this.setMlwlKs((L2 * A2 + L4 * K4)
					/ (((piQvRation - 1) * L2 / (piQvRation - 1) + 2) + L4));
		} else {
			this.setMlwlKa((L2 * B2 + L0 * K0) / (((2 * L2) / 3) + L0));
			this.setMlwlKs((L2 * A2 + L4 * K4) / ((L2 / 3) + L4));
		}
		this.setMlwlKaKs(this.mlwlKa / this.mlwlKs);
	}

	/**
	 * Calculates the transition / transversion mutation rate ratio.
	 * 
	 * @param cleanOriginalSequence
	 * @param cleanMutatedSequence
	 * @return
	 */
	private double calcPiQvRatio(String cleanOriginalSequence,
			String cleanMutatedSequence) {
		double countPi = 0; // transitions
		double countQv = 0; // transversions

		String changeType;
		for (int index = 0; index < cleanOriginalSequence.length(); index++) {
			if (index <= cleanMutatedSequence.length()) {
				changeType = this.computeTransversionVsTransitionChange(
						cleanOriginalSequence.charAt(index),
						cleanMutatedSequence.charAt(index));
				switch (changeType) {
				case TRANSITION:
					countPi++;
					break;
				case TRANSVERSION:
					countQv++;
					break;
				}
			}
		}
		return countPi / countQv;
	}

	/**
	 * Calculates Ka/Ks ration using the Kimuras- two parameter (K2P) model.
	 * 
	 * @param cleanOriginalSequence
	 * @param cleanMutatedSequence
	 */
	private void kaKsCalcLWL(String cleanOriginalSequence,
			String cleanMutatedSequence) {

		Map<String, Object> pQValues = this.calculatePQValues(
				cleanOriginalSequence, cleanMutatedSequence);
		final double L0 = (double) pQValues.get(L2_AVERAGE_COUNT);
		final double L2 = (double) pQValues.get(L2_AVERAGE_COUNT);
		final double L4 = (double) pQValues.get(L4_AVERAGE_COUNT);

		Map<String, Double> calculatedValues = this.calculateMeanVariance(
				CHAR_0, cleanOriginalSequence, cleanMutatedSequence, pQValues);
		final double K0 = calculatedValues.get(TOTAL_SUBSTITUTIONS);
		final double VK0 = calculatedValues
				.get(TOTAL_SUBSTITUTIONS_ERROR_VARIANCE);

		calculatedValues = this.calculateMeanVariance(CHAR_2,
				cleanOriginalSequence, cleanMutatedSequence, pQValues);
		final double A2 = calculatedValues.get(TRANSITIONAL_SUBSTITUTIONS);
		final double VA2 = calculatedValues
				.get(TRANSITIONAL_SUBSTITUTIONS_ERROR_VARIANCE);
		final double B2 = calculatedValues.get(TRANSVERSION_SUBSTITUTIONS);
		final double VB2 = calculatedValues
				.get(TRANSVERSION_SUBSTITUTIONS_ERROR_VARIANCE);

		calculatedValues = this.calculateMeanVariance(CHAR_4,
				cleanOriginalSequence, cleanMutatedSequence, pQValues);
		final double K4 = calculatedValues.get(TOTAL_SUBSTITUTIONS);
		final double VK4 = calculatedValues
				.get(TOTAL_SUBSTITUTIONS_ERROR_VARIANCE);

		this.setLwlKs(3 * ((L2 * A2) + (L4 * K4)) / (L2 + (3 * L4)));
		this.setLwlVKs(9 * (L2 * L2 * VA2 + L4 * L4 * VK4)
				/ Math.pow((L2 + 3 * L4), 2));
		this.setLwlKa(3 * (L2 * B2 + L0 * K0) / (2 * L2 + 3 * L0));
		this.setLwlVKa(9 * (L2 * L2 * VB2 + L0 * L0 * VK0)
				/ Math.pow((2 * L2 + 3 * L0), 2));
		this.setLwlKaKs(this.lwlKa / this.lwlKs);
	}

	/**
	 * Calculates mean and approximate error variance. Reference Li, Wu, and Luo
	 * P. 152-153.
	 * 
	 * @param i
	 * @param cleanOriginalSequence
	 * @param cleanMutatedSequence
	 * @param pQValues
	 * @return
	 */
	private Map<String, Double> calculateMeanVariance(
			final Character degenerationType, String cleanOriginalSequence,
			String cleanMutatedSequence, Map<String, Object> pQValues) {

		final double L0 = (double) pQValues.get(L2_AVERAGE_COUNT);
		final double L2 = (double) pQValues.get(L2_AVERAGE_COUNT);
		final double L4 = (double) pQValues.get(L4_AVERAGE_COUNT);
		final double[] pValues = (double[]) pQValues.get(P_VALUES);
		final double[] qValues = (double[]) pQValues.get(Q_VALUES);

		double L = 0;
		double P = 0;
		double Q = 0;

		double ai = 0;
		double bi = 0;
		double ci = 0;
		double di = 0;

		if (CHAR_0.equals(degenerationType)) {
			L = L0;
			P = pValues[0];
			Q = qValues[0];
		} else if (CHAR_2.equals(degenerationType)) {
			L = L2;
			P = pValues[1];
			Q = qValues[1];
		} else if (CHAR_4.equals(degenerationType)) {
			L = L4;
			P = pValues[2];
			Q = qValues[2];
		}

		ai = 1 / (1 - (2 * P) - Q);
		if (ai < 0) {
			ai = 0.1; // FIXME (deal with negative values)
		}
		bi = 1 / (1 - (2 * Q));
		if (bi < 0) {
			bi = 0.1; // FIXME (deal with negative values)
		}
		ci = (ai - bi) / 2;
		if (ci < 0) {
			ci = 0.1; // FIXME (deal with negative values)
		}
		di = bi + ci;

		// Mean of transitional substitutions per i-th site.
		final double Ai = 0.5 * Math.log(ai) - 0.25 * Math.log(bi); // Natural
																	// log (e)

		// Mean if transversional substitutions per i-th site
		final double Bi = 0.5 * Math.log(bi);

		// Approx error variance (transitional substitutions)
		final double VAi = (((ai * ai) * P + (ci * ci)) - Math.pow((ai * P)
				+ (ci * Q), 2))
				/ L;

		// Approx error variance (transversion substitutions)
		final double VBi = (((bi * bi) * Q) * (1 - Q)) / L;

		// total number of substitutions per i-th site
		final double Ki = Ai + Bi;

		// Variance
		final double VKi = (((ai * ai) * P + (di * di) * Q) - Math.pow((ai * P)
				+ (ci * Q), 2))
				/ L;

		Map<String, Double> resultMap = new HashMap<String, Double>();
		resultMap.put(TRANSITIONAL_SUBSTITUTIONS, Ai);
		resultMap.put(TRANSITIONAL_SUBSTITUTIONS_ERROR_VARIANCE, VAi);
		resultMap.put(TRANSVERSION_SUBSTITUTIONS, Bi);
		resultMap.put(TRANSVERSION_SUBSTITUTIONS_ERROR_VARIANCE, VBi);
		resultMap.put(TOTAL_SUBSTITUTIONS, Ki);
		resultMap.put(TOTAL_SUBSTITUTIONS_ERROR_VARIANCE, VKi);

		return resultMap;
	}

	/**
	 * Calculates the observed transition( Pi) and transversion (Qv)
	 * differences.
	 * 
	 * @param cleanMutatedSequence
	 * @param cleanOriginalSequence
	 * @return
	 */
	private Map<String, Object> calculatePQValues(String cleanOriginalSequence,
			String cleanMutatedSequence) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		double[] pValues = new double[3]; // [L0, L2, L4]
		double[] qValues = new double[3]; // Same as above

		String degeneracySequence = this
				.calculateDegeneracySequence(cleanOriginalSequence);
		double L0, L2, L4;

		Map<String, Double> calculatedAvg024 = this.calculateAverage024(
				cleanOriginalSequence, cleanMutatedSequence);
		L0 = calculatedAvg024.get(L2_AVERAGE_COUNT);
		L2 = calculatedAvg024.get(L2_AVERAGE_COUNT);
		L4 = calculatedAvg024.get(L4_AVERAGE_COUNT);

		char originalNucleotide;
		char mutatedNucleotide;
		String changeType;
		Character degeneracyCode;

		for (int index = 0; index < cleanOriginalSequence.length(); index++) {
			if (index <= cleanMutatedSequence.length()) {
				originalNucleotide = cleanOriginalSequence.charAt(index);
				mutatedNucleotide = cleanMutatedSequence.charAt(index);
				if (originalNucleotide != mutatedNucleotide) {

					changeType = this.computeTransversionVsTransitionChange(
							originalNucleotide, mutatedNucleotide);
					degeneracyCode = degeneracySequence.charAt(index);

					switch (changeType) {
					case TRANSITION:
						if (degeneracyCode.equals(CHAR_0)) {
							pValues[0] = 1 / L0;
						} else if (degeneracyCode.equals(CHAR_2)) {
							pValues[1] = 1 / L2;
						} else if (degeneracyCode.equals(CHAR_4)) {
							pValues[2] = 1 / L4;
						}
						break;
					case TRANSVERSION:
						if (degeneracyCode.equals(CHAR_0)) {
							qValues[0] = 1 / L0;
						} else if (degeneracyCode.equals(CHAR_2)) {
							qValues[1] = 1 / L2;
						} else if (degeneracyCode.equals(CHAR_4)) {
							qValues[2] = 1 / L4;
						}
					}// --switch
				}
			}
		}

		resultMap.put(L2_AVERAGE_COUNT, L0);
		resultMap.put(L2_AVERAGE_COUNT, L2);
		resultMap.put(L4_AVERAGE_COUNT, L4);
		resultMap.put(P_VALUES, pValues);
		resultMap.put(Q_VALUES, qValues);
		return resultMap;
	}

	/**
	 * Test if the change is transversion or transition. KEY:
	 * 
	 * Pi = TRANSITION (A > G or viceversa | A > C or viceversa ) Qv =
	 * TRANSVERSION ( other combinations )
	 * 
	 * @param originalNucleotide
	 * @param mutatedNucleotide
	 * @return
	 */
	private String computeTransversionVsTransitionChange(
			char originalNucleotide, char mutatedNucleotide) {

		String original = EMPTY + originalNucleotide;
		String mutated = EMPTY + mutatedNucleotide;

		if ((StringUtils.equals(original, ADENINE) && StringUtils.equals(
				mutated, GUANINE))
				|| (StringUtils.equals(original, GUANINE) && StringUtils
						.equals(mutated, ADENINE))) {
			return TRANSITION;
		} else if ((StringUtils.equals(original, URACIL) && StringUtils.equals(
				mutated, CYTOSINE))
				|| (StringUtils.equals(original, CYTOSINE) && StringUtils
						.equals(mutated, URACIL))) {
			return TRANSITION;
		}
		return TRANSVERSION;
	}

	/**
	 * Calculate average 0,2,4 degeneracy values for 2 sequences.
	 * 
	 * @param cleanOriginalSequence
	 * @param cleanMutatedSequence
	 * @return
	 */
	private Map<String, Double> calculateAverage024(
			String cleanOriginalSequence, String cleanMutatedSequence) {

		Map<String, Double> resultMap = new HashMap<String, Double>();

		String originalSeqDegCode = this
				.calculateDegeneracySequence(cleanOriginalSequence);
		String mutatedSeqDegCode = this
				.calculateDegeneracySequence(cleanMutatedSequence);

		double originalCount0, originalCount2, originalCount4;
		Map<String, Double> counted024 = this.count024(originalSeqDegCode);
		originalCount0 = counted024.get(COUNT_0);
		originalCount2 = counted024.get(COUNT_2);
		originalCount4 = counted024.get(COUNT_4);

		double mutatedCount0, mutatedCount2, mutatedCount4;
		counted024 = this.count024(mutatedSeqDegCode);
		mutatedCount0 = counted024.get(COUNT_0);
		mutatedCount2 = counted024.get(COUNT_2);
		mutatedCount4 = counted024.get(COUNT_4);

		resultMap.put(L2_AVERAGE_COUNT, (originalCount0 + mutatedCount0) / 2);
		resultMap.put(L2_AVERAGE_COUNT, (originalCount2 + mutatedCount2) / 2);
		resultMap.put(L4_AVERAGE_COUNT, (originalCount4 + mutatedCount4) / 2);

		return resultMap;
	}

	/**
	 * Calculates the frequencies for the three different types of degeneracy
	 * types (0,2,4)
	 * 
	 * @param originalSeqDegCode
	 * @return
	 */
	private Map<String, Double> count024(String seqDegCode) {
		double count0 = 0;
		double count2 = 0;
		double count4 = 0;

		Map<String, Double> resultMap = new HashMap<String, Double>();
		for (Character code : seqDegCode.toCharArray()) {
			if (code.equals(CHAR_0)) {
				count0++;
			} else if (code.equals(CHAR_2)) {
				count2++;
			} else if (code.equals(CHAR_4)) {
				count4++;
			}
		}
		resultMap.put(COUNT_0, count0);
		resultMap.put(COUNT_2, count2);
		resultMap.put(COUNT_4, count4);
		return resultMap;
	}

	/**
	 * Provides the complete sequence in degeneracy code.
	 * 
	 * @param cleanOriginalSequence
	 * @return
	 */
	private String calculateDegeneracySequence(String cleanOriginalSequence) {
		StringBuffer buffer = new StringBuffer();

		String[] codonList = getCodonList(cleanOriginalSequence);
		for (String codon : codonList) {
			buffer.append(this.codonDegreeMap.get(this.getAmino(codon)));
		}
		return buffer.toString();
	}

	/**
	 * Calculate KaKs using the Jukes-Cantor (JC) method.
	 */
	private void kaKsCalcNG(String originalSequence, String mutatedSequence) {
		String nsxSequence = translateToNSXSequence(originalSequence);
		String nsxCodonMatchSequence = getEvolutionCode(originalSequence,
				mutatedSequence);

		// Count the occurrence of N and S from the NSX code of the given
		// sequences
		double nSeq = 0, sSeq = 0, nEvo = 0, sEvo = 0;
		for (Character code : nsxSequence.toCharArray()) {
			if (code.equals(CHAR_S) || code.equals(CHAR_X)) {
				sSeq++;
			}
			if (code.equals(CHAR_N) || code.equals(CHAR_X)) {
				nSeq++;
			}
		}

		for (Character code : nsxCodonMatchSequence.toCharArray()) {
			if (code.equals(CHAR_S) || code.equals(CHAR_X)) {
				sEvo++;
			}
			if (code.equals(CHAR_N) || code.equals(CHAR_X)) {
				nEvo++;
			}
		}

		this.setNgKa(sEvo / sSeq);
		this.setNgKs(nEvo / nSeq);
		this.setNgKaKs(this.ngKa / this.ngKs);
	}

	/**
	 * Detects mutations in codons from 2 sequences.
	 * 
	 * @param originalSequence
	 * @param mutatedSequence
	 * @return
	 */
	private String getEvolutionCode(String originalSequence,
			String mutatedSequence) {
		StringBuffer buffer = new StringBuffer();

		String[] originalSeqCodons = this.getCodonList(originalSequence);
		String[] mutatedSeqCodons = this.getCodonList(mutatedSequence);

		boolean match;
		for (int index = 0; index < originalSeqCodons.length; index++) {
			if (index <= mutatedSeqCodons.length) {
				match = StringUtils.equals(
						this.getAmino(originalSeqCodons[index]),
						this.getAmino(mutatedSeqCodons[index]));
				if (match) {
					buffer.append(SYNONYMOUS);
				} else {
					buffer.append(NON_SYNONYMOUS);
				}
			}
		}

		return buffer.toString();
	}

	/**
	 * Provides the complete NSX code for a given complete sequence. Used to
	 * Analyze both evolved and original sequences.
	 * 
	 * @param originalSequence
	 * @return
	 */
	private String translateToNSXSequence(String sequence) {
		StringBuffer buffer = new StringBuffer();
		String[] codonList = getCodonList(sequence);
		for (String codon : codonList) {
			buffer.append(this.getNSXForCodon(codon));
		}
		return buffer.toString();
	}

	/**
	 * Returns the Synonymity state of a given codon.
	 * 
	 * @param codon
	 * @return
	 */
	private Object getNSXForCodon(String codon) {
		return nsxMap.get(this.getAmino(codon));
	}

	/**
	 * Gets the amino acid coded by the 3-char codons.
	 * 
	 * @param codon
	 * @return
	 */
	private String getAmino(String codon) {
		return aminoMap.get(codon);
	}

	/**
	 * Extracts codons from a sequence
	 * 
	 * @param sequence
	 * @return
	 */
	private String[] getCodonList(String sequence) {
		return Iterables.toArray(Splitter.fixedLength(3).split(sequence),
				String.class);
	}

	/**
	 * Converts DNA sequence to RNA. Thymine is replaced by Uracil
	 * 
	 * @param originalSequence
	 * @return
	 */
	private String convertDnaToRna(String sequence) {
		return StringUtils.replaceChars(sequence, THYMINE, URACIL);
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
			if (ADENINE.equals(nucleotide.toString())
					|| GUANINE.equals(nucleotide.toString())
					|| THYMINE.equals(nucleotide.toString())
					|| CYTOSINE.equals(nucleotide.toString())
					|| URACIL.equals(nucleotide.toString())) {
				buffer.append(nucleotide.toString());
			} else {
				invalidSequence = true;
				buffer.append("<span class='error'>" + nucleotide + "</span>");
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
