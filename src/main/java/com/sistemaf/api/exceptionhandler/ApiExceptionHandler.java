package com.sistemaf.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.sistemaf.api.exceptionhandler.Problem.Field;
import com.sistemaf.core.SistemFApiProperty;
import com.sistemaf.domain.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private SistemFApiProperty sistemaProperty;
	
	@Autowired
	private MessageSource messageSource;

	private final String MSG_ERRO_GENERICO_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. "
			+ "Tente novamente e se o problema persistir, entre em contato " + "com o administrador do sistema.";

	@ExceptionHandler({EntityNotFoundException.class, FileServiceException.class})
	public ResponseEntity<?>  handlerEntidadeNaoEncontrado(BusinessException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		Problem problem = this
				.createProblemBuilder(status, ProblemType.RECURSO_NAO_ENCONTRADO, ex.getMessage(), ex.getMessage());
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handlerNegocioException(BusinessException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problem problem = this.createProblemBuilder(status, ProblemType.ERRO_NEGOCIO, ex.getMessage(), ex.getMessage());
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler({EntityUsedException.class})
	public ResponseEntity<?> handlerEntidadeEmUsoException(EntityUsedException ex, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		Problem problem = this.createProblemBuilder(status, ProblemType.ENTIDADE_EM_USO, ex.getMessage(), ex.getMessage());
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handlerUncaught(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String detail = "Ocorreu um erro interno inesperado no sistema. "
				+ "Tente novamente e se o problema persistir, entre em contato " + "com o administrador do sistema.";
		Problem problem = this
				.createProblemBuilder(status, ProblemType.ERRO_DE_SISTEMA, detail, MSG_ERRO_GENERICO_USUARIO_FINAL);
		// ex.printStackTrace();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(StorageException.class)
	public ResponseEntity<?> handleStorageException(StorageException ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Problem problem = this
				.createProblemBuilder(status, ProblemType.ERRO_DE_SISTEMA, ex.getMessage(), MSG_ERRO_GENERICO_USUARIO_FINAL);
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		Problem problem = this.createProblemBuilder(status, ProblemType.ACESSO_NEGADO, ex.getMessage(),
				messageSource.getMessage("acesso-negado", null, Locale.getDefault()));
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(status).headers(headers).build();
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		String detail = "O corpo da requisição esta invalido. Verifique erro na sintaxe";
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
		}
		Problem problem = this
				.createProblemBuilder(status, ProblemType.MENSAGEM_INCOMPREENSIVEL, detail, MSG_ERRO_GENERICO_USUARIO_FINAL);
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return this.handlerInternalValidation(ex, ex.getBindingResult(), headers, status, request);

	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		return handlerInternalValidation(ex, ex.getBindingResult(), headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return this.handlerMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status,
					request);
		}
		return super.handleTypeMismatch(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String detail = String.format("O recurso %s , que voce tentou acessar, é inexistente", ex.getRequestURL());
		Problem problem = this
				.createProblemBuilder(status, ProblemType.RECURSO_NAO_ENCONTRADO, detail, MSG_ERRO_GENERICO_USUARIO_FINAL);
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null) {
			body = Problem.builder().timestamp(OffsetDateTime.now()).title(status.getReasonPhrase()).status(status.value())
					.userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL).build();
		} else if (body instanceof String) {
			body = Problem.builder().timestamp(OffsetDateTime.now()).title((String) body).status(status.value())
					.userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL).build();

		}
		log.error(ex.getMessage(), ex);
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String path = joinPath(ex.getPath());
		String detail = String
				.format("A propriedade '%s' não existe. " + "Corrija ou remova essa propriedade e tente novamente.", path);
		Problem problem = this
				.createProblemBuilder(status, ProblemType.MENSAGEM_INCOMPREENSIVEL, detail, MSG_ERRO_GENERICO_USUARIO_FINAL);
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String path = joinPath(ex.getPath());
		String detail = String.format(
				"A propriedate '%s' recebeu o valor '%s'"
						+ " que é de um tipo invalido. Corrija e informe um valor compativel com o tipo '%s'.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		Problem problem = this
				.createProblemBuilder(status, ProblemType.MENSAGEM_INCOMPREENSIVEL, detail, MSG_ERRO_GENERICO_USUARIO_FINAL);
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	private Problem createProblemBuilder(HttpStatus status, ProblemType problemType, String detail,
			String userMessage) {
		String uri = this.sistemaProperty.getApiUrl() +problemType.getPath();
		return Problem.builder().status(status.value()).type(uri).title(problemType.getTitle())
				.userMessage(userMessage).timestamp(OffsetDateTime.now()).detail(detail).build();
	}

	private ResponseEntity<Object> handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String detail = String.format(
				"O parâmetro de URL '%s' recebeu o valor '%s', "
						+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		Problem problem = this
				.createProblemBuilder(status, ProblemType.PARAMETRO_INVALIDO, detail, MSG_ERRO_GENERICO_USUARIO_FINAL);

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	private List<Field> buildFields(List<ObjectError> list) {
		return list.stream().map(objectError -> {
			String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
			String nome = objectError.getObjectName();
			if (objectError instanceof FieldError) {
				nome = ((FieldError) objectError).getField();
			}

			return Field.builder().nome(nome).userMessage(message).build();
		}).collect(Collectors.toList());
	}

	private ResponseEntity<Object> handlerInternalValidation(Exception ex, BindingResult bindingResult,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String detail = "Um ou mais campos estão invalidos. Faça o preenchimento correto e tente novamente";
		List<Problem.Field> fields = this.buildFields(bindingResult.getAllErrors());
		String uri = this.sistemaProperty.getApiUrl() +ProblemType.DADOS_INVALIDOS.getPath();

		Problem problem = Problem.builder().status(status.value())
				.type(uri).title(ProblemType.DADOS_INVALIDOS.getTitle()).detail(detail)
							.userMessage(detail).objects(fields).build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private String joinPath(List<Reference> list) {
		return list.stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
	}
}
