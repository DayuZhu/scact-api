package com.sc.act.api.commons.web.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @ClassName ServiceIpUtil
 * @Description 获取单网卡IP
 */
public class ServiceIpUtil {

	/**
	 * @Title: getLocalIp
	 * @Description:
	 * @param
	 * @return String
	 */
	public static String getLocalIp() {
		InetAddress inetAddress = getInetAddressNotLoopback();
		if (inetAddress != null) {
			return inetAddress.getHostAddress();
		}
		return null;

	}

	private static InetAddress getInetAddressNotLoopback() {

		List<InetAddress> colInetAddress = getAllHostAddress();
		for (InetAddress address : colInetAddress) {
			if (!address.isLoopbackAddress()) {
				if (address.getHostAddress().length() > 15) {
					continue;
				}
				return address;
			}
		}
		return null;
	}

	private static List<InetAddress> getAllHostAddress() {
		List<InetAddress> addresses = new ArrayList<InetAddress>();
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = inetAddresses.nextElement();
					addresses.add(inetAddress);
				}
			}
			return addresses;
		} catch (SocketException e) {
			// swallow
		}
		return addresses;
	}
}
