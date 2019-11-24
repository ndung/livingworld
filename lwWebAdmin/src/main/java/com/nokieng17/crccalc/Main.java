package com.nokieng17.crccalc;

import static java.lang.System.out;

public class Main {

	public static void main(String[] args) {
		Check(Crc8.Params);

		Check(Crc16.Params);

		Check(Crc32.Params);

		Check(Crc64.Params);
	}

	private static void Check(AlgoParams[] params) {
		for (int i = 0; i < params.length; i++) {
			CrcCalculator calculator = new CrcCalculator(params[i]);
			byte[] bytes = "00020101021126670016COM.NOBUBANK.WWW01189360050300000030810214082000000002270303UBE51440014ID.CO.QRIS.WWW0215ID82000000002270303UBE520454995802ID5920BRIGHT PAYMENT POINT6015Jakarta Selatan610512120530336062290508cibinong06064784080703A016304".getBytes();
			long result = calculator.Calc(bytes, 0, bytes.length);
			out.println(Long.toHexString(result).toUpperCase());
			//if (result != calculator.Parameters.Check)
			//	out.println(calculator.Parameters.Name + " - BAD ALGO!!! " + Long.toHexString(result).toUpperCase());
		}
	}
}