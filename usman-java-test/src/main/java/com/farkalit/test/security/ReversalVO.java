/**
 * 
 */
package com.farkalit.test.security;


/**
 * @author farkalitusman
 *
 */
public class ReversalVO {
	
	protected String externalSystemId;
	
	protected String storeId;
	
	protected String reversalAmount;

	protected String orderId;
	
	protected String orderDate;
	
	protected String cnic;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getReversalAmount() {
		return reversalAmount;
	}

	public void setReversalAmount(String reversalAmount) {
		this.reversalAmount = reversalAmount;
	}

	public String getExternalSystemId() {
		return externalSystemId;
	}

	public void setExternalSystemId(String externalSystemId) {
		this.externalSystemId = externalSystemId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getCnic() {
		return cnic;
	}

	public void setCnic(String cnic) {
		this.cnic = cnic;
	}

	@Override
	public String toString() {
		return "ReversalRequest [orderId=" + orderId + ", storeId=" + storeId + ", reversalAmount=" + reversalAmount
				+ ", externalSystemId=" + externalSystemId + ", orderDate=" + orderDate + ", cnic=" + cnic + "]";
	}

}
