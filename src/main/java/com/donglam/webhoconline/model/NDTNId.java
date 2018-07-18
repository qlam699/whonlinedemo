package com.donglam.webhoconline.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class NDTNId implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="nguoigui",referencedColumnName="mand")
    private NguoiDung nguoigui;
 
	@ManyToOne
	@JoinColumn(name="nguoinhan",referencedColumnName="mand")
    private NguoiDung nguoinhan;
    
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="matn")
	private TinNhan tinnhan;
    
    public NDTNId() {}

	public NDTNId(NguoiDung nguoigui, NguoiDung nguoinhan, TinNhan tinnhan) {
		super();
		this.nguoigui = nguoigui;
		this.nguoinhan = nguoinhan;
		this.tinnhan = tinnhan;
	}

	public NguoiDung getNguoigui() {
		return nguoigui;
	}

	public void setNguoigui(NguoiDung nguoigui) {
		this.nguoigui = nguoigui;
	}

	public NguoiDung getNguoinhan() {
		return nguoinhan;
	}

	public void setNguoinhan(NguoiDung nguoinhan) {
		this.nguoinhan = nguoinhan;
	}

	public TinNhan getTinnhan() {
		return tinnhan;
	}

	public void setTinnhan(TinNhan tinnhan) {
		this.tinnhan = tinnhan;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NDTNId)) return false;
        NDTNId that = (NDTNId) o;
        return Objects.equals(getNguoigui(), that.getNguoigui()) &&
                Objects.equals(getNguoinhan(), that.getNguoinhan());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getNguoigui(), getNguoinhan());
    }
}
