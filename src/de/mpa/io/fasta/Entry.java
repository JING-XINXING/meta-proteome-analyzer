package de.mpa.io.fasta;


public class Entry {
	/**
	 * The index of the entry.
	 */
	private long index;
	
	/**
	 * Name of the entry (ProteinID).
	 */
	private String name;
	
	/**
	 * Character string of the sequence.
	 */
	private char[] sequence;	
	
	/**
	 * Start position of the sequence.
	 */
	private int start;
	
	/**
	 * End position of the sequence.
	 */
	private int end;
	
	/**
	 * Copy Constructor
	 * @param entry
	 */
	public Entry(Entry entry){
        index = entry.getIndex();
        name = entry.getName();
        sequence = entry.getSequence();
        start = entry.getStart();
        end = entry.getEnd();
	}
	
	/**
	 * Constructor for the entry implementation, where
	 * sequence is given as string
	 * @param name
	 * @param sequence
	 * @param start
	 * @param end
	 */
	public Entry(int index, String name, String sequence, int start, int end) {
		this.index = index;
		this.name = name;
		this.sequence = sequence.toCharArray();
		this.start = start;
		this.end = end;
	}

	/**
	 * Constructor for the sequence implementation.
	 * The whole sequence string is taken from start to end position.
	 * @param name
	 * @param sequence
	 */
	public Entry(int index, String name, String sequence) {
		this(index, name, sequence, 1, -1);
	}
	
	/**
	 * Returns the letter (amino acid) at a certain position.
	 */
	public char getLetterAt(int i) {
	    if (i < this.sequence.length) {
	      return this.sequence[i];
	    } else  {
	      return ' ';
	    }
	  }
	
	/**
	 * Returns the sequence start position of the sequence.
	 */
	public int getStart() {
		return this.start;
	}
	
	/**
	 * Finds the i'th position in the sequence, skipping the gaps.
	 * @param i
	 * @return pos Integer
	 */
	public int findPosition(int i) {
		int j = 0;
		int pos = this.start;
		int seqlen = this.sequence.length;
		while ((j < i) && (j < seqlen)) {
			pos++;
			j++;
		}

	    return pos;
	}
	
	/**
	 * Returns the index of the entry.
	 * @return
	 */
	public long getIndex() {
		return this.index;
	}
	
	/**
	 * Set the index of the entry.
	 * @param index
	 */
	public void setIndex(long index) {
		this.index = index;
	}

	/**
	 * Sets the start position of the sequence.
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * Returns the end position of the sequence.
	 */
	public int getEnd() {
		return end;
	}
	
	/**
	 * Sets the end position of the sequence.
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * Returns the sequence length.
	 */
	public int getLength() {
		return sequence.length;
	}
	
	/**
	 * Returns the formatted name, which means only the first part of the proteinID.
	 * @return String
	 */
	public String getFormattedName() {
		String[] split = name.split(" ");
		return split[0];
	}
	
	/**
	 * Returns the sequence name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the sequence name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the sequence (for start and end position) as char array.
	 */
	public char[] getSequence(int start, int end) {		
		if (start < 0) {
			start = 0;
		}
		
		if (start >= this.sequence.length) {
			return new char[0];
		}

		if (end >= this.sequence.length) {
			end = this.sequence.length;
		}

		char[] charseq = new char[end - start];
		System.arraycopy(this.sequence, start, charseq, 0, end - start);

		return charseq;
	}
	
	/**
	 * Sets the sequence as string.
	 */
	public void setSequence(String sequence) {
		 this.sequence = sequence.toCharArray();

	}
	
	/**
	 * Sets the sequence as char array.
	 * @param sequence
	 */
	public void setSequence(char[] sequence) {
		this.sequence = sequence;
	}
	
	/**
	 * Returns the sequence as a string.
	 */
	public String getSequenceAsString() {
		return new String(this.sequence);
	}
	
	/**
	 * Returns the sequence as char array.
	 */
	public char[] getSequence() {
		return this.sequence;
	}
}
