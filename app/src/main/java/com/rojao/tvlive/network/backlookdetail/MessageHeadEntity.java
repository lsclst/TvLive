package com.rojao.tvlive.network.backlookdetail;

import java.nio.*;


public class MessageHeadEntity {

	public static final short STMSG_ONLIVE = 0x0785;
	public static final short STMSG_RESEE = 0x0781;
	public static final short STMSG_TIMESHIFT = 0x0783;
	public static final short STMSG_HEARTBEAT = 0x0789;
	
	private final int messageSize = 16;
	private String stMsgHead;
	private String ucVersion;
	private String ucCompressFlag;
	private short usP2P_Type;
	private short usCheck_Sum;
	private int usMsg_Length;
	private String ucReserveEx;

	public MessageHeadEntity() {
		super();
	}

	public MessageHeadEntity(String stMsgHead, String ucVersion, String ucCompressFlag, short usP2P_Type, short usCheck_Sum, int usMsg_Length,
			String ucReserveEx) {
		super();
		this.stMsgHead = stMsgHead;
		this.ucVersion = ucVersion;
		this.ucCompressFlag = ucCompressFlag;
		this.usP2P_Type = usP2P_Type;
		this.usCheck_Sum = usCheck_Sum;
		this.usMsg_Length = usMsg_Length;
		this.ucReserveEx = ucReserveEx;
	}

	public String getStMsgHead() {
		return stMsgHead;
	}

	public void setStMsgHead(String stMsgHead) {
		this.stMsgHead = stMsgHead;
	}

	public String getUcVersion() {
		return ucVersion;
	}

	public void setUcVersion(String ucVersion) {
		this.ucVersion = ucVersion;
	}

	public String getUcCompressFlag() {
		return ucCompressFlag;
	}

	public void setUcCompressFlag(String ucCompressFlag) {
		this.ucCompressFlag = ucCompressFlag;
	}

	public short getUsP2P_Type() {
		return usP2P_Type;
	}

	public void setUsP2P_Type(short usP2P_Type) {
		this.usP2P_Type = usP2P_Type;
	}

	public short getUsCheck_Sum() {
		return usCheck_Sum;
	}

	public void setUsCheck_Sum(short usCheck_Sum) {
		this.usCheck_Sum = usCheck_Sum;
	}

	public int getUsMsg_Length() {
		return usMsg_Length;
	}

	public void setUsMsg_Length(int usMsg_Length) {
		this.usMsg_Length = usMsg_Length;
	}

	public String getUcReserveEx() {
		return ucReserveEx;
	}

	public void setUcReserveEx(String ucReserveEx) {
		this.ucReserveEx = ucReserveEx;
	}

	public static MessageHeadEntity toEntity(byte[] array) {
		MessageHeadEntity rspEntity = null;
		String stMsgHead = FormatTransfer.bytesToString(FormatTransfer.subBytes(array, 0, 4));
		String ucVersion = FormatTransfer.bytesToString(FormatTransfer.subBytes(array, 4, 1));
		String ucCompressFlag = FormatTransfer.bytesToString(FormatTransfer.subBytes(array, 5, 1));
		short usP2P_Type = FormatTransfer.hBytesToShort(FormatTransfer.subBytes(array, 6, 2));
		short usCheck_Sum = FormatTransfer.hBytesToShort(FormatTransfer.subBytes(array, 8, 2));
		String ucReserveEx = FormatTransfer.bytesToString(FormatTransfer.subBytes(array, 10, 2));
		int usMsg_Length = FormatTransfer.hBytesToInt(FormatTransfer.subBytes(array, 12, 4));
		rspEntity = new MessageHeadEntity(stMsgHead, ucVersion, ucCompressFlag, usP2P_Type, usCheck_Sum, usMsg_Length, ucReserveEx);
		return rspEntity;
	}

	public byte[] toByte() {
		ByteBuffer bBuffer = ByteBuffer.allocate(messageSize);
		if (null != stMsgHead && stMsgHead.length() > 0) {
			bBuffer.put(stMsgHead.getBytes());
		}
		if (null != ucVersion && ucVersion.length() > 0) {
			bBuffer.put(ucVersion.getBytes());
		}
		if (null != ucCompressFlag && ucCompressFlag.length() > 0) {
			bBuffer.put(ucCompressFlag.getBytes());
		}
		bBuffer.put(FormatTransfer.toHH(usP2P_Type));
		bBuffer.put(FormatTransfer.toHH(usCheck_Sum));
		if (null != ucReserveEx && ucReserveEx.length() > 0) {
			bBuffer.put(ucReserveEx.getBytes());
		}
		bBuffer.put(FormatTransfer.toHH(usMsg_Length));
		for (int i = 0; i < bBuffer.array().length; i++) {
		//	System.out.print(bBuffer.array()[i]);
		}
		return bBuffer.array();
	}
	// public static MessageHeadEntity toEntity(byte[] array) {
	// MessageHeadEntity rspEntity = null;
	// String stMsgHead = FormatTransfer.bytesToString(FormatTransfer.subBytes(array, 0, 4));
	// String ucVersion = FormatTransfer.bytesToString(FormatTransfer.subBytes(array, 4, 1));
	// String ucCompressFlag = FormatTransfer.bytesToString(FormatTransfer.subBytes(array, 5, 1));
	// short usP2P_Type = FormatTransfer.lBytesToShort(FormatTransfer.subBytes(array, 6, 2));
	// short usCheck_Sum = FormatTransfer.lBytesToShort(FormatTransfer.subBytes(array, 8, 2));
	// String ucReserveEx = FormatTransfer.bytesToString(FormatTransfer.subBytes(array, 10, 2));
	// int usMsg_Length = FormatTransfer.lBytesToInt(FormatTransfer.subBytes(array, 12, 4));
	// rspEntity = new MessageHeadEntity(stMsgHead, ucVersion, ucCompressFlag, usP2P_Type, usCheck_Sum, usMsg_Length, ucReserveEx);
	// return rspEntity;
	// }
	//
	// public byte[] toByte() {
	// ByteBuffer bBuffer = ByteBuffer.allocate(messageSize);
	// if (null != stMsgHead && stMsgHead.length() > 0) {
	// bBuffer.put(stMsgHead.getBytes());
	// }
	// if (null != ucVersion && ucVersion.length() > 0) {
	// bBuffer.put(ucVersion.getBytes());
	// }
	// if (null != ucCompressFlag && ucCompressFlag.length() > 0) {
	// bBuffer.put(ucCompressFlag.getBytes());
	// }
	// bBuffer.put(FormatTransfer.toLH(usP2P_Type));
	// bBuffer.put(FormatTransfer.toLH(usCheck_Sum));
	// if (null != ucReserveEx && ucReserveEx.length() > 0) {
	// bBuffer.put(ucReserveEx.getBytes());
	// }
	// bBuffer.put(FormatTransfer.toLH(usMsg_Length));
	// for (int i = 0; i < bBuffer.array().length; i++) {
	// System.out.print(bBuffer.array()[i]);
	// }
	// return bBuffer.array();
	// }
}
