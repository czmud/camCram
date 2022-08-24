package com.czmud.camcram.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProtectionKey implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "van_id")
	private Long vanId;
	
	@Column(name = "protection_id")
	private Long protectionId;
	
	public ProtectionKey() {}
	
	public ProtectionKey(Long vanId, Long protectionId) {
		this.vanId = vanId;
		this.protectionId = protectionId;
	}

	public Long getVanId() {
		return vanId;
	}

	public void setVanId(Long vanId) {
		this.vanId = vanId;
	}

	public Long getProtectionId() {
		return protectionId;
	}

	public void setProtectionId(Long protectionId) {
		System.out.println(protectionId);
		this.protectionId = protectionId;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o) {
		System.out.println("equals");
		if(o == this) {
			return true;
		}
		if ( !(o instanceof ProtectionKey )) {
			return false;
		}
		ProtectionKey other = (ProtectionKey) o;
		boolean vanIdEquals = ( this.vanId == null && other.vanId == null )
			|| (this.vanId != null && this.vanId.equals(other.vanId));
		boolean protectionIdEquals = ( this.protectionId == null && other.protectionId == null )
				|| (this.protectionId != null && this.protectionId.equals(other.protectionId));
		return vanIdEquals && protectionIdEquals;
	}
	
	@Override
	public int hashCode() {
		System.out.println("hash");
		int hash = 7;
		hash = 31 * hash + (vanId == null ? 0 : vanId.hashCode() );
		hash = 31 * hash + (vanId == null ? 0 : protectionId.hashCode() );
		return hash;
	}
	
	
}
