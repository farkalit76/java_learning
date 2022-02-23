package com.farkalit.test.security;

public class HashData {
	
	private Integer live;
	private String oid;
	private String inv;
	private String amount;
	private String tel;
	private String eml;
	private String vid;
	private String curr;
	private String p1;
	private String p2;
	private String p3;
	private String p4;
	private Integer cst;
	private String cbk;
	
	public HashData() {}
	
	
	public HashData(Integer live, String oid, String inv, String amount, String tel, String eml, String vid, String curr,
			String p1, String p2, String p3, String p4, Integer cst, String cbk) {
		super();
		this.live = live;
		this.oid = oid;
		this.inv = inv;
		this.amount = amount;
		this.tel = tel;
		this.eml = eml;
		this.vid = vid;
		this.curr = curr;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.cst = cst;
		this.cbk = cbk;
	}


	public Integer getLive() {
		return live;
	}
	public void setLive(Integer live) {
		this.live = live;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getInv() {
		return inv;
	}
	public void setInv(String inv) {
		this.inv = inv;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEml() {
		return eml;
	}
	public void setEml(String eml) {
		this.eml = eml;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getCurr() {
		return curr;
	}
	public void setCurr(String curr) {
		this.curr = curr;
	}
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
	public String getP2() {
		return p2;
	}
	public void setP2(String p2) {
		this.p2 = p2;
	}
	public String getP3() {
		return p3;
	}
	public void setP3(String p3) {
		this.p3 = p3;
	}
	public String getP4() {
		return p4;
	}
	public void setP4(String p4) {
		this.p4 = p4;
	}
	public Integer getCst() {
		return cst;
	}
	public void setCst(Integer cst) {
		this.cst = cst;
	}
	public String getCbk() {
		return cbk;
	}
	public void setCbk(String cbk) {
		this.cbk = cbk;
	}
	
	

}
