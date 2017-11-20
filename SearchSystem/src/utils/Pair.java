package utils;

/**
 * Doesn't exist a tuple class in Java...so I create one cause I needed.
 * @author Ailson Forte dos Santos
 *
 * @param <F> A type to the first element of this tuple
 * @param <S> A type to the second element of this tuple
 */
public class Pair<F, S> {
	public F first;
	public S second;
	
	/**
	 * Constructor
	 * 
	 * @param first the first element
	 * @param second the second element
	 */
	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * @return the first element
	 */
	public F getFirst() {
		return first;
	}

	/**
	 * @param first the first element to set
	 */
	public void setFirst(F first) {
		this.first = first;
	}

	/**
	 * @return the second element
	 */
	public S getSecond() {
		return second;
	}

	/**
	 * @param second the second element to set
	 */
	public void setSecond(S second) {
		this.second = second;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + first + ":" + second;
	}
	
}
