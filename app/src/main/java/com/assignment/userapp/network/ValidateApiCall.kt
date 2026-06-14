package com.assignment.userapp.network

import com.assignment.userapp.data.ErrorMessages
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response


suspend fun <T> validateApiCall(
    apiCall: suspend () -> Response<T>
): Result<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Result.success(body)
            } else {
                Result.failure(Exception(ErrorMessages.ERROR_BODY_NUll))
            }
        } else {
            val error = response.errorBody()?.string()
                ?: response.message()
            Result.failure(Exception(error))

        }
    } catch (e: IOException) {
        Result.failure(Exception(ErrorMessages.ERROR_NO_INTERNET))
    } catch (e: HttpException) {
        Result.failure(Exception(ErrorMessages.ERROR_HTTP))
    } catch (e: Exception) {
        Result.failure(Exception(ErrorMessages.ERROR_UNKNOWN))
    }

}