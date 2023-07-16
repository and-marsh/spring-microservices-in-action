//package com.optimagrowth.license.controller
//
//import com.optimagrowth.license.model.utils.ErrorMessage
//import com.optimagrowth.license.model.utils.RestErrorList
//import jakarta.servlet.http.HttpServletRequest
//import org.apache.catalina.filters.AddDefaultCharsetFilter.ResponseWrapper
//import org.springframework.http.HttpStatus.NOT_ACCEPTABLE
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.ControllerAdvice
//import org.springframework.web.bind.annotation.ExceptionHandler
//import org.springframework.web.bind.annotation.ResponseBody
//import org.springframework.web.servlet.config.annotation.EnableWebMvc
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
//
//@ControllerAdvice
//@EnableWebMvc
//class ExceptionController : ResponseEntityExceptionHandler() {
//
//    @ExceptionHandler(value = [Exception::class])
//    @ResponseBody
//    fun handleException(
//        request: HttpServletRequest?,
//        responseWrapper: ResponseWrapper?
//    ): ResponseEntity<ResponseWrapper> =
//        ResponseEntity.ok(responseWrapper)
//
//    @ExceptionHandler(RuntimeException::class)
//    fun handleIOException(request: HttpServletRequest?, e: RuntimeException): ResponseEntity<ResponseWrapper> {
//        val errorList = RestErrorList(NOT_ACCEPTABLE, ErrorMessage(e.message, e.message))
//        val responseWrapper = ResponseWrapper(null, null)
//        return ResponseEntity.ok(responseWrapper)
//    }
//}
