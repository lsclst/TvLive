package com.rojao.tvlive.network.backlookdetail;

import java.nio.*;


public class RewindEntity {
	private int ErrorCode;
	private String ucPad;
	private String ChannelID;
	private String ProgramID;
	private String ProgramURL;
	private MessageHeadEntity messageHead;
	private final int messageHeadSize = 16;
	private int ContentLen = 0;
	private int totalLen = 0;
	private String SegmentIPAddr;
	private short SegmentPort;

	public RewindEntity() {
		super();
	}

	public RewindEntity(String channelID, String programID, int contentLen) {
		super();
		ChannelID = channelID;
		ProgramID = programID;
		ContentLen = contentLen;
		totalLen = contentLen + messageHeadSize;
	}

	public RewindEntity(int errorCode, String ucPad, String programURL, String SegmentIPAddr, short SegmentPort, MessageHeadEntity messageHead) {
		super();
		ErrorCode = errorCode;
		this.ucPad = ucPad;
		ProgramURL = programURL;
		this.messageHead = messageHead;
		this.SegmentIPAddr = SegmentIPAddr;
		this.SegmentPort = SegmentPort;
	}

	public String getSegmentIPAddr() {
		return SegmentIPAddr;
	}

	public void setSegmentIPAddr(String segmentIPAddr) {
		SegmentIPAddr = segmentIPAddr;
	}

	public short getSegmentPort() {
		return SegmentPort;
	}

	public void setSegmentPort(short segmentPort) {
		SegmentPort = segmentPort;
	}

	public int getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(int errorCode) {
		ErrorCode = errorCode;
	}

	public String getUcPad() {
		return ucPad;
	}

	public void setUcPad(String ucPad) {
		this.ucPad = ucPad;
	}

	public String getChannelID() {
		return ChannelID;
	}

	public void setChannelID(String channelID) {
		ChannelID = channelID;
	}

	public String getProgramID() {
		return ProgramID;
	}

	public void setProgramID(String programID) {
		ProgramID = programID;
	}

	public String getProgramURL() {
		return ProgramURL;
	}

	public void setProgramURL(String programURL) {
		ProgramURL = programURL;
	}

	public MessageHeadEntity getMessageHead() {
		return messageHead;
	}

	public void setMessageHead(MessageHeadEntity messageHead) {
		this.messageHead = messageHead;
	}

	public int getContentLen() {
		return ContentLen;
	}

	public byte[] toByte() {
		if (totalLen > 0) {
			ByteBuffer bBuffer = ByteBuffer.allocate(totalLen);
			if (null != messageHead) {
				bBuffer.put(messageHead.toByte());
				if (null != ChannelID && !"".equals(ChannelID)) {
					byte[] channelByte = ChannelID.getBytes();
					bBuffer.put(channelByte);
					if (channelByte.length < 32) {
						for (int i = 0; i < 32 - channelByte.length; i++) {
							bBuffer.put((byte) '\0');
						}
					}
				}
				if (null != ProgramID && !"".equals(ProgramID)) {
					byte[] deviceIdByte = ProgramID.getBytes();
					bBuffer.put(deviceIdByte);
					if (deviceIdByte.length < 32) {
						for (int i = 0; i < 32 - deviceIdByte.length; i++) {
							bBuffer.put((byte) '\0');
						}
					}
				}
				return bBuffer.array();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static RewindEntity toEntity(byte[] array) {
		for (int i = 0; i < array.length; i++) {
		//	System.out.print(array[i] + "_");
		}
		RewindEntity rspEntity = null;
		MessageHeadEntity headEntity = MessageHeadEntity.toEntity(FormatTransfer.subBytes(array, 0, 16));
		int errorCode = FormatTransfer.hBytesToInt(FormatTransfer.subBytes(array, 16, 4));
		short port = FormatTransfer.hBytesToShort(FormatTransfer.subBytes(array, 20, 2));
		String ucpad = FormatTransfer.bytesToString(FormatTransfer.subBytes(array, 22, 2));
		String ip = FormatTransfer.bytesToString(FormatTransfer.subBytes(array, 24, 32));
		String url = FormatTransfer.bytesToString(FormatTransfer.subBytes(array, 56, 256));
		rspEntity = new RewindEntity(errorCode, ucpad, url, ip, port, headEntity);
		return rspEntity;
	}
	// public byte[] toByte() {
	// if (totalLen > 0) {
	// ByteBuffer bBuffer = ByteBuffer.allocate(totalLen);
	// if (null != messageHead) {
	// bBuffer.put(messageHead.toByte());
	// if (null != ChannelID && !"".equals(ChannelID)) {
	// byte[] channelByte = ChannelID.getBytes();
	// bBuffer.put(channelByte);
	// if (channelByte.length < 32) {
	// for (int i = 0; i < 32 - channelByte.length; i++) {
	// bBuffer.put((byte) '\0');
	// }
	// }
	// }
	// if (null != ProgramID && !"".equals(ProgramID)) {
	// byte[] deviceIdByte = ProgramID.getBytes();
	// bBuffer.put(deviceIdByte);
	// if (deviceIdByte.length < 32) {
	// for (int i = 0; i < 32 - deviceIdByte.length; i++) {
	// bBuffer.put((byte) '\0');
	// }
	// }
	// }
	// return bBuffer.array();
	// } else {
	// return null;
	// }
	// } else {
	// return null;
	// }
	// }
	//
	// public static RewindEntity toEntity(byte[] array) {
	// for (int i = 0; i < array.length; i++) {
	// System.out.print(array[i] + "_");
	// }
	// RewindEntity rspEntity = null;
	// MessageHeadEntity headEntity = MessageHeadEntity.toEntity(FormatTransfer.subBytes(array, 0, 16));
	// int errorCode = FormatTransfer.lBytesToInt(FormatTransfer.subBytes(array, 16, 4));
	// short port = FormatTransfer.lBytesToShort(FormatTransfer.subBytes(array, 20, 2));
	// String ucpad = FormatTransfer.bytesToString(FormatTransfer.subBytes(array, 22, 2));
	// String ip = FormatTransfer.bytesToString(FormatTransfer.subBytes(array, 24, 32));
	// String url = FormatTransfer.bytesToString(FormatTransfer.subBytes(array, 56, 256));
	// rspEntity = new RewindEntity(errorCode, ucpad, url, ip, port, headEntity);
	// return rspEntity;
	// }
}
