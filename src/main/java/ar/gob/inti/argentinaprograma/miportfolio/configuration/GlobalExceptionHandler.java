// package ar.gob.inti.argentinaprograma.miportfolio.configuration;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.springframework.beans.NotReadablePropertyException;
// import org.springframework.dao.DataIntegrityViolationException;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.http.converter.HttpMessageNotReadableException;
// import org.springframework.security.access.AccessDeniedException;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.MissingServletRequestParameterException;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.context.request.WebRequest;
// import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// import ar.gob.inti.argentinaprograma.miportfolio.exception.NotFoundException;
// import ar.gob.inti.argentinaprograma.miportfolio.model.error.ApiError;

// import javax.servlet.http.HttpServletRequest;
// import javax.validation.ValidationException;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import static java.util.Optional.ofNullable;

// import java.net.BindException;
// import java.net.http.HttpHeaders;
// import java.time.LocalDateTime;

// @ControllerAdvice
// public class GlobalExceptionHandler {

//     private final Logger logger = LogManager.getLogger();

//     /**
//      * 404
//      * Not found
//      */
//     @ExceptionHandler(NotFoundException.class)
//     public ResponseEntity<ApiError> handleNotFoundException(HttpServletRequest request, NotFoundException ex) {

//         final ApiError err = new ApiError(
//                 HttpStatus.NOT_FOUND,
//                 "No se encontraron datos");

//         logger.error("handleNotFoundException: \n", request.getRequestURI(), ex);
//         return new ResponseEntity<>(err, err.getStatus());
//     }

//     @ExceptionHandler(ValidationException.class)
//     public ResponseEntity<ApiError> handleValidationException(HttpServletRequest request, ValidationException ex) {

//         final ApiError err = new ApiError(
//                 HttpStatus.BAD_REQUEST,
//                 "Error de validación");

//         logger.error("handleValidationException: \n", request.getRequestURI(), ex);
//         return new ResponseEntity<>(err, err.getStatus());
//     }

//     @ExceptionHandler(MissingServletRequestParameterException.class)
//     public ResponseEntity<ApiError> handleMissingServletRequestParameterException(HttpServletRequest request,
//             MissingServletRequestParameterException ex) {
//         final ApiError err = new ApiError(
//                 HttpStatus.BAD_REQUEST,
//                 "Error de validación");

//         logger.error("handleMissingServletRequestParameterException: \n", request.getRequestURI(), ex);
//         return new ResponseEntity<>(err, err.getStatus());
//     }

//     /**
//      * 400
//      * Se lanza cuando un parámetro del método tiene un tipo incorrecto
//      */
//     @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//     public ResponseEntity<ApiError> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
//             final HttpServletRequest request) {

//         final ApiError err = new ApiError(
//                 HttpStatus.BAD_REQUEST,
//                 "Falta de correspondencia entre los tipos");

//         err.addValidationError(ex.getName(), "Debe ser de tipo " + ex.getRequiredType().getSimpleName());

//         logger.error("handleMethodArgumentTypeMismatch: \n", request.getRequestURI(), ex);
//         return new ResponseEntity<>(err, err.getStatus());
//     }

//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public ResponseEntity<ApiError> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex,
//             final HttpServletRequest request) {

//         final ApiError err = new ApiError(
//                 HttpStatus.BAD_REQUEST,
//                 "La validación del argumento del método falló");

//         err.addValidationErrors(ex.getBindingResult().getFieldErrors());

//         logger.error("handleMethodArgumentNotValidException: \n", request.getRequestURI(), ex);
//         return new ResponseEntity<ApiError>(err, err.getStatus());
//     }

//     @ExceptionHandler(AccessDeniedException.class)
//     public ResponseEntity<ApiError> handleAccessDeniedException(HttpServletRequest request, AccessDeniedException ex) {
//         final ApiError err = new ApiError(
//                 HttpStatus.FORBIDDEN,
//                 "Acceso denegado");

//         logger.error("handleAccessDeniedException: \n", request.getRequestURI(), ex);
//         return new ResponseEntity<ApiError>(err, err.getStatus());
//     }

//     @ExceptionHandler(DataIntegrityViolationException.class)
//     public ResponseEntity<ApiError> handleDuplicateEmailException(HttpServletRequest request, DataIntegrityViolationException ex) {
//         final ApiError err = new ApiError(
//                 HttpStatus.CONFLICT,
//                 "Correo electronio duplicado.");

//         logger.error("handleDuplicateEmailException: \n", request.getRequestURI(), ex);
//         return new ResponseEntity<ApiError>(err, err.getStatus());
//     }

//     /**
//      * 500
//      * Excepciones que no tienen handlers específicos.
//      */
//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<ApiError> handleInternalServerError(HttpServletRequest request, Exception ex) {
//         final ApiError err = new ApiError(
//                 HttpStatus.INTERNAL_SERVER_ERROR,
//                 ex.getMessage());

//         logger.error("handleInternalServerError: \n", request.getRequestURI(), ex);
//         return new ResponseEntity<ApiError>(err, err.getStatus());
//     }
// }