package cn.jeeweb.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class SerializeUtil {
	
	public static byte[] toByteKey(String key) {
		if(StringUtils.isBlank(key)) {
			return null;
		}
		return key.getBytes();
	}
	
	
	/**
	 * 描述 : <Object转byte[]>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
		
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			close(oos);
            close(bos);
        }
		return bytes;
	}

	/**
	 * 描述 : <byte[]转Object>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object toObject(byte[] bytes) {
		if(bytes == null) {
			return null;
		}
		Object obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {
            close(bis);
            close(bis);
        }
		return obj;
	}
	
	/**
	 * 得到多个Key byte
	 * @param keySet
	 * @return
	 */
	public static byte[][] getKeySetBytes(Set<String> keySet) {
		if(keySet == null || keySet.isEmpty()) {
			return null;
		}
		final byte[][] rawValues = new byte[keySet.size()][];
		int i = 0;
		for (String key : keySet) {
			if(StringUtils.isBlank(key)) {
				continue;
			}
			rawValues[i++] = key.getBytes();
		}
		return rawValues;
	}
	
	
	/**
	 * listBytes -> List<Object>
	 * @param listBytes
	 * @return
	 */
	public static List<Object> toObjectList(List<byte[]> listBytes) {
		if(listBytes == null || listBytes.isEmpty()) {
			return null;
		}
		List<Object> objList = new ArrayList<Object>();
		for(byte[] bytes : listBytes) {
			Object obj = null;
			try {
				ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bis);
				obj = ois.readObject();
				ois.close();
				bis.close();
				objList.add(obj);
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		return objList;
	}
	
	
    private static void close(Closeable closeable) {
        if (closeable != null)
            try {
                closeable.close();
            } catch (IOException e) {
            	 LoggerUtils.fmtError(SerializeUtil.class, "close stream error");
            }
    }
}
