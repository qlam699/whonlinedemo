package com.donglam.webhoconline.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ndtn")
/*@AssociationOverrides({ @AssociationOverride(name = "ndtnid.nguoigui", joinColumns = @JoinColumn(name = "nguoigui")),
	@AssociationOverride(name = "ndtnid.nguoinhan", joinColumns = @JoinColumn(name = "nguoinhan")),
	@AssociationOverride(name = "ndtnid.tinnhan", joinColumns = @JoinColumn(name = "matn")) })*/
public class NDTN {
	@EmbeddedId
	private NDTNId ndtnid;

	public NDTN() {
	}

	public NDTN(NDTNId ndtnid) {
		super();
		this.ndtnid = ndtnid;
	}

	public NDTNId getNdtnid() {
		return ndtnid;
	}

	public void setNdtnid(NDTNId ndtnid) {
		this.ndtnid = ndtnid;
	}

}
