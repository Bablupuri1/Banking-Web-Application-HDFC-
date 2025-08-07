package com.hdfc.ApiResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A generic wrapper class for API responses.
 *
 * @param <T> The type of the data payload returned in the response.
 *
 *            This class provides a consistent structure for all HTTP API
 *            responses, including success status, message, HTTP status code,
 *            and optional payload.
 */
@Data
@NoArgsConstructor
public class ApiResponse<T> {

	/**
	 * Indicates if the request was processed successfully.
	 */
	private boolean success;

	/**
	 * A human-readable message, usually describing the result or error.
	 */
	private String message;

	/**
	 * The actual data returned by the API. Can be null in case of errors.
	 */
	private T data;

	public ApiResponse(boolean success, String message, T data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}

}
