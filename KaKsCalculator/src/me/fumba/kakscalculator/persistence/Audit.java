package me.fumba.kakscalculator.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "audit")
@Entity
public class Audit implements Serializable {

	private static final long serialVersionUID = -3918234604227009671L;

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "error_details")
	private String errorDetails;

	@Column(name = "original_sequence")
	private String originalSequence;

	@Column(name = "mutated_sequence")
	private String mutatedSequence;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	public String getOriginalSequence() {
		return originalSequence;
	}

	public void setOriginalSequence(String originalSequence) {
		this.originalSequence = originalSequence;
	}

	public String getMutatedSequence() {
		return mutatedSequence;
	}

	public void setMutatedSequence(String mutatedSequence) {
		this.mutatedSequence = mutatedSequence;
	}

}