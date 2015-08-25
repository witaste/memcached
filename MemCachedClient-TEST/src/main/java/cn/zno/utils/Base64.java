package cn.zno.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("restriction")
public class Base64 {
	/**
	 * str ����Ϊbase64
	 * 
	 * @param String s
	 * @return String base64
	 * */
	public static String getBase64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	/**
	 * byte[] ����Ϊbase64
	 * 
	 * @param byte[] ba
	 * @return String base64
	 * */
	public static String getBase64(byte[] ba) {
		if (ba == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(ba);
	}

	/**
	 * base64 ����Ϊ byte[]
	 * 
	 * @param String base64
	 * @return byte[]
	 * @throws IOException
	 * */
	public static byte[] base64ToByteArray(String base64) throws IOException {
		if (base64 == null)
			return null;
		return (new sun.misc.BASE64Decoder()).decodeBuffer(base64);
	}
	
	/**
	 * ��base64 �ַ��������л�Ϊָ������
	 * 
	 * @param base64 ���л�����Base64������ַ���
	 * @param serializedClass ���л������class
	 * 
	 * @return typed object, can be null.
	 * */
	@SuppressWarnings("unchecked")
	public static <T> T deFromBase64(String base64, Class<T> serializedClass) {
		Object o = null;
		byte[] ba = null;
		ByteArrayInputStream bai = null;
		ObjectInputStream oi = null;
		try {
			ba = Base64.base64ToByteArray(base64);
			bai = new ByteArrayInputStream(ba);
			oi = new ObjectInputStream(bai);
			o = oi.readObject();
		} catch (IOException e) {
			System.out.println("�����л�ʧ�ܣ�");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("��δ�ҵ���");
			e.printStackTrace();
		} finally {
			try {
				if (oi != null) {
					oi.close();
				}
				if (bai != null) {
					bai.close();
				}
			} catch (IOException e) {
				System.out.println("�ر����쳣��");
				e.printStackTrace();
			}
		}
		return o == null ? null : (T) o;
	}

	/**
	 * ���������л�ΪBase64 �ַ���
	 * 
	 * @param obj ʵ���˿����л��ӿڵĶ���
	 * @return String �������л�Ϊ�ַ��������ΪBase64
	 * */
	public static String se2base64(Serializable obj) {
		byte[] ba = null;
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			ba = baos.toByteArray();
		} catch (IOException e) {
			System.out.println("���л�ʧ�ܣ�");
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ba == null ? null : Base64.getBase64(ba);
	}
}
