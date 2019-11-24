package com.nokieng17.emvcoqr.encoding;

public interface IPayloadEncoding<T> {

	String GeneratePayload(T instance) throws Exception;
}
