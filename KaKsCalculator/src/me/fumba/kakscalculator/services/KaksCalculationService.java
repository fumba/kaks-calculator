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

	private final Map<String, String> nsxMap;
	private final Map<String, String> aminoMap;

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
	}

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

		final String cleanOriginalSequence = this
				.convertDnaToRna(originalSequence);
		final String cleanMutatedSequence = this
				.convertDnaToRna(mutatedSequence);

		// Calculation using the Jukes-Cantor (JC) method
		this.kaKsCalcNG(cleanOriginalSequence, cleanMutatedSequence);

		return COMPUTE_SUCCESS;
	}

	/**
	 * Calculate KaKs using the Jukes-Cantor (JC) method.
	 */
	private void kaKsCalcNG(final String originalSequence,
			final String mutatedSequence) {
		final String nsxSequence = translateToNSXSequence(originalSequence);
		final String nsxCodonMatchSequence = getEvolutionCode(originalSequence,
				mutatedSequence);

		// Count the occurrence of N and S from the NSX code of the given
		// sequences
		int nSeq = 0, sSeq = 0, nEvo = 0, sEvo = 0;
		for (Character code : nsxSequence.toCharArray()) {
			if (code.equals('S') || code.equals('X')) {
				sSeq++;
			}
			if (code.equals('N') || code.equals('X')) {
				nSeq++;
			}
		}

		for (Character code : nsxCodonMatchSequence.toCharArray()) {
			if (code.equals('S') || code.equals('X')) {
				sEvo++;
			}
			if (code.equals('N') || code.equals('X')) {
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

		final String[] originalSeqCodons = this.getCodonList(originalSequence);
		final String[] mutatedSeqCodons = this.getCodonList(mutatedSequence);

		boolean match;
		for (int index = 0; index < originalSeqCodons.length; index++) {
			if (index <= mutatedSeqCodons.length) {
				match = StringUtils.equals(
						this.getAmino(originalSeqCodons[index]),
						this.getAmino(mutatedSeqCodons[index]));
				if (match) {
					buffer.append("S");
				} else {
					buffer.append("N");
				}
			}
		}

		return buffer.toString();
	}

	/**
	 * Provides the complete NSX code for a given complete sequence. Used to
	 * analyse both evolved and original sequences.
	 * 
	 * @param originalSequence
	 * @return
	 */
	private String translateToNSXSequence(final String sequence) {
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
	private Object getNSXForCodon(final String codon) {
		return nsxMap.get(this.getAmino(codon));
	}

	/**
	 * Gets the amino acid coded by the 3-char codons.
	 * 
	 * @param codon
	 * @return
	 */
	private String getAmino(final String codon) {
		return aminoMap.get(codon);
	}

	/**
	 * Extracts codons from a sequence
	 * 
	 * @param sequence
	 * @return
	 */
	private String[] getCodonList(final String sequence) {
		return Iterables.toArray(Splitter.fixedLength(3).split(sequence),
				String.class);
	}

	/**
	 * Converts DNA sequence to RNA. Thymine is replaced by Uracil
	 * 
	 * @param originalSequence
	 * @return
	 */
	private String convertDnaToRna(final String sequence) {
		return StringUtils.replaceChars(sequence, THYMINE, URACIL);
	}

	/**
	 * Checks of the sequence provided has conventional nucleotides.
	 * 
	 * @param originalSequence
	 * @return
	 */
	private String checkForUnconventionalNucleotides(final String sequence) {
		boolean invalidSequence = false;
		StringBuffer buffer = new StringBuffer();
		for (Character nucleotide : sequence.toCharArray()) {
			if (ADENINE.equals(nucleotide) || GUANINE.equals(nucleotide)
					|| THYMINE.equals(nucleotide)
					|| CYTOSINE.equals(nucleotide) || URACIL.equals(nucleotide)) {
				buffer.append(nucleotide);
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

}
