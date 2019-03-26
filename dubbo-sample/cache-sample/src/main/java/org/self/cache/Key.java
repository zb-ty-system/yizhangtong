package org.self.cache;

import java.io.Serializable;

/**
 *
 * @author zhuwenbing
 * @date 2017年3月13日
 * @version 1.0
 */

public class Key implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5553738915299436792L;
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Key other = (Key) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

}
