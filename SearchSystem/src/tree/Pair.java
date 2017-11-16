package tree;

/**
 * @author Ailson Forte dos Santos
 *
 * @param <F>
 * @param <S>
 * @param <T>
 */
public class Pair<F, S> {
	public F first;
	public S second;
	
	/**
	 * @param first
	 * @param second
	 * @param third
	 */
	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * @return the first
	 */
	public F getFirst() {
		return first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(F first) {
		this.first = first;
	}

	/**
	 * @return the second
	 */
	public S getSecond() {
		return second;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(S second) {
		this.second = second;
	}

//	/* (non-Javadoc)
//	 * @see java.lang.Object#toString()
//	 */
//	@Override
//	public String toString() {
//		return "" + first + ":" + second;
//	}
	
}
