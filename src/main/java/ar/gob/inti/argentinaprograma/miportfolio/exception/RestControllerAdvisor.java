package ar.gob.inti.argentinaprograma.miportfolio.exception;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.gob.inti.argentinaprograma.miportfolio.model.error.ApiError;

@ControllerAdvice
@RestController
public class RestControllerAdvisor extends ResponseEntityExceptionHandler {

        /**
         * 400
         * Esta excepción se activará si el cuerpo de la solicitud no es válido
         */
        @Override
        protected ResponseEntity<Object> handleHttpMessageNotReadable(
                        final HttpMessageNotReadableException ex,
                        final HttpHeaders headers,
                        final HttpStatus status,
                        final WebRequest request) {

                final ApiError err = new ApiError(
                                HttpStatus.BAD_REQUEST,
                                "Solicitud JSON con formato incorrecto");

                err.addValidationError(null, ex.getMessage());

                return new ResponseEntity<>(err, err.getStatus());
        }

        /**
         * 400
         * Se produce cuando un argumento de método de controlador anotado con la
         * anotación @Valid falló en la validación.
         */
        protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
                        final HttpStatus status,
                        final WebRequest request) {

                final ApiError err = new ApiError(
                                HttpStatus.BAD_REQUEST,
                                "Errores de validación");
                err.addValidationErrors(ex.getBindingResult().getFieldErrors());
                return new ResponseEntity<>(err, err.getStatus());
        }

        /**
         * 400
         * Se lanza cuando un parámetro del método tiene un tipo incorrecto
         */
        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
                        final MethodArgumentTypeMismatchException ex, final WebRequest request) {

                final ApiError err = new ApiError(
                                HttpStatus.BAD_REQUEST,
                                "Falta de correspondencia entre los tipos");
                err.addValidationError(ex.getName(), "Debe ser de tipo " + ex.getRequiredType().getSimpleName());
                return new ResponseEntity<>(err, err.getStatus());
        }

        /**
         * 400
         * Informa del resultado de las violaciones de las restricciones.
         */
        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<?> handleConstraintViolationException(
                        final Exception ex,
                        final WebRequest request) {

                final ApiError err = new ApiError(
                                HttpStatus.BAD_REQUEST,
                                "Violaciones de las restricciones");
                err.addValidationError(null, ex.getMessage());
                return new ResponseEntity<>(err, err.getStatus());
        }

        /**
         * 400
         * Indica que un método del controlador no recibe un parámetro requerido
         */
        @Override
        protected ResponseEntity<Object> handleMissingServletRequestParameter(
                        final MissingServletRequestParameterException ex, final HttpHeaders headers,
                        final HttpStatus status, final WebRequest request) {

                final ApiError err = new ApiError(
                                HttpStatus.BAD_REQUEST,
                                "Falta de parámetros");
                err.addValidationError(null, ex.getMessage());
                return new ResponseEntity<>(err, err.getStatus());
        }

        /**
         * 404
         * DispatcherServlet envía una respuesta 404 si no hay ningún handler para una
         * solicitud concreta
         */
        @Override
        protected ResponseEntity<Object> handleNoHandlerFoundException(
                        final NoHandlerFoundException ex,
                        final HttpHeaders headers,
                        final HttpStatus status,
                        final WebRequest request) {

                final ApiError err = new ApiError(
                                HttpStatus.NOT_FOUND,
                                "Método no encontrado");

                err.addValidationError(null, String.format("No se ha podido encontrar el método %s para la URL %s",
                                ex.getHttpMethod(), ex.getRequestURL()));
                return new ResponseEntity<>(err, err.getStatus());
        }

        /**
         * 404
         * Not found
         */
        @ExceptionHandler({ NotFoundException.class })
        public ResponseEntity<Object> handleNotFoundException(final Exception ex, final WebRequest request) {
                final ApiError err = new ApiError(
                                HttpStatus.NOT_FOUND,
                                ex.getLocalizedMessage());

                return new ResponseEntity<Object>(err, new HttpHeaders(), err.getStatus());
        }

        /**
         * 415
         * Informa de que el tipo de medio de solicitud especificado (tipo de contenido)
         * no es compatible.
         */
        @Override
        protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
                        final HttpMediaTypeNotSupportedException ex,
                        final HttpHeaders headers,
                        final HttpStatus status,
                        final WebRequest request) {

                final StringBuilder builder = new StringBuilder();
                builder.append(ex.getContentType());
                builder.append(" no se admite el tipo de soporte. Los tipos de soporte admitidos son ");
                ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

                final ApiError err = new ApiError(
                                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                                "Tipo de medio no compatible");

                err.addValidationError(null, builder.toString());
                return new ResponseEntity<>(err, err.getStatus());
        }

        @ExceptionHandler(UsernameNotFoundException.class)
        public ResponseEntity<ApiError> handleUsernameNotFoundException(WebRequest request, UsernameNotFoundException ex) {

                final ApiError err = new ApiError(
                                HttpStatus.CONFLICT,
                                ex.getMessage());

                return new ResponseEntity<>(err, err.getStatus());
        }

        @ExceptionHandler(ValidationException.class)
        public ResponseEntity<ApiError> handleValidationException(WebRequest request, ValidationException ex) {

                final ApiError err = new ApiError(
                                HttpStatus.BAD_REQUEST,
                                ex.getMessage());

                return new ResponseEntity<>(err, err.getStatus());
        }

        /**
         * 500
         * Excepciones que no tienen handlers específicos.
         */
        @ExceptionHandler({ Exception.class })
        public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
                final ApiError err = new ApiError(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                ex.getLocalizedMessage());

                return new ResponseEntity<Object>(err, new HttpHeaders(), err.getStatus());
        }
}