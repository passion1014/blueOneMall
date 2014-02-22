package com.blueone.common.util;

public enum MsgEnum {
	MsgEnum_SUCCESS(1),
	MsgEnum_FAIL(0),
	MsgEnum_NONE(100);

	private final int msg;

	MsgEnum(int value) { this.msg = value; }
	public int value() { return msg; }

	static MsgEnum fromValue(int value) {
		for (MsgEnum each : MsgEnum.values()) {   
			if (each.msg == value) {   
				return each;
			}
		}
		return MsgEnum.MsgEnum_NONE;
	} 
}
