/**
 * 
 */
package com.farkalit.test.security;

/**
 * @author farkalitusman
 *
 */
public class ReversalResVO {

	protected String responseCode;

	protected String responseDesc;

	//protected String transactionId;

	//protected String merchantName;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

	@Override
	public String toString() {
		return "ReversalResVO [responseCode=" + responseCode + ", responseDesc=" + responseDesc + "]";
	}

	
//	public String getTransactionId() {
//		return transactionId;
//	}
//
//	public void setTransactionId(String transactionId) {
//		this.transactionId = transactionId;
//	}
//
//	public String getMerchantName() {
//		return merchantName;
//	}
//
//	public void setMerchantName(String merchantName) {
//		this.merchantName = merchantName;
//	}
//
//	@Override
//	public String toString() {
//		return "ReversalResponseVO [responseCode=" + responseCode + ", responseDesc=" + responseDesc
//				+ ", transactionId=" + transactionId + ", merchantName=" + merchantName + "]";
//	}

}
