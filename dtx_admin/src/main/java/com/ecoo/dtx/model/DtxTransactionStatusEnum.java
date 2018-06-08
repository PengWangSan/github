package com.ecoo.dtx.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum DtxTransactionStatusEnum {

	/**
	 * Commit transaction status enum.
	 */
	Succes(1, "成功"),

	/**
	 * Begin transaction status enum.
	 */
	BEGIN(2, "开始"),

	FAILED(3, "失败");

	private int code;

	private String desc;

	DtxTransactionStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static DtxTransactionStatusEnum acquireByCode(int code) {
		Optional<DtxTransactionStatusEnum> transactionStatusEnum = Arrays.stream(DtxTransactionStatusEnum.values())
				.filter(v -> Objects.equals(v.getCode(), code)).findFirst();
		return transactionStatusEnum.orElse(DtxTransactionStatusEnum.BEGIN);

	}

	public static String acquireDescByCode(int code) {
		return acquireByCode(code).getDesc();
	}

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets code.
	 *
	 * @param code
	 *            the code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Gets desc.
	 *
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Sets desc.
	 *
	 * @param desc
	 *            the desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
