package cme4408_assigment_1_java;

public class n_gram_array {
	private String n_gram;
	private int value;
	
	public n_gram_array(String n_gram, int value) {
		super();
		this.n_gram = n_gram;
		this.value = value;
	}

	public String getN_gram() {
		return n_gram;
	}

	public void setN_gram(String n_gram) {
		this.n_gram = n_gram;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
