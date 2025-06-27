package es.udc.paproject.backend.rest.controllers;

import es.udc.paproject.backend.model.entities.Inscription;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.InscriptionService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Locale;

import static es.udc.paproject.backend.rest.dtos.InscriptionConversor.toInscriptionDto;
import static es.udc.paproject.backend.rest.dtos.InscriptionConversor.toInscriptionDtos;
import static es.udc.paproject.backend.rest.dtos.InscriptionSimpleConversor.toInscriptionSimpleDto;

@RestController
@RequestMapping("/inscriptions")
public class InscriptionController {

    private final static String NUMBER_ALREADY_DELIVERED_EXCEPTION_CODE =
            "project.exceptions.NumberAlreadyDeliveredException";

    private final static String INVALID_CREDIT_CARD_EXCEPTION_CODE =
            "project.exceptions.InvalidCreditCardException";

    private final static String DATE_EXPIRED_EXCEPTION_CODE =
            "project.exceptions.DateExpiredException";

    private final static String MAX_INSCRIPTIONS_REACHED_EXCEPTION_CODE =
            "project.exceptions.MaxInscriptionsReachedException";

    private final static String ALREADY_SCORED_EXCEPTION_CODE =
            "project.exceptions.AlreadyScoredException";

    private final static String INSTANCE_NOT_FOUND_EXCEPTION_CODE =
            "project.exceptions.InstanceNotFoundException";

    private final static String DIFFERENT_USER_EXCEPTION_CODE =
            "project.exceptions.DifferentUserException";

    private final static String INCORRECT_TRIAL_EXCEPTION_CODE =
            "project.exceptions.IncorrectTrialException";

    private final static String DUPLICATE_INSCRIPTION_EXCEPTION_CODE =
            "project.exceptions.DuplicateInscriptionException";


    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NumberAlreadyDeliveredException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleNumberAlreadyDeliveredException(NumberAlreadyDeliveredException exception, Locale locale) {
        String errorMessage = messageSource.getMessage(NUMBER_ALREADY_DELIVERED_EXCEPTION_CODE,
                new Object[]{exception.getInscriptionId()}, NUMBER_ALREADY_DELIVERED_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(InvalidCreditCardException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleInvalidCreditCardException(InvalidCreditCardException exception, Locale locale) {
        String errorMessage = messageSource.getMessage(INVALID_CREDIT_CARD_EXCEPTION_CODE,
                new Object[]{exception.getInscriptionId(), exception.getCreditCard()},
                INVALID_CREDIT_CARD_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(DateExpiredException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleDateExpiredException(DateExpiredException exception, Locale locale) {
        String errorMessage = messageSource.getMessage(DATE_EXPIRED_EXCEPTION_CODE,
                null, DATE_EXPIRED_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(MaxInscriptionsReachedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleMaxInscriptionsReachedException(MaxInscriptionsReachedException exception, Locale locale) {
        String errorMessage = messageSource.getMessage(MAX_INSCRIPTIONS_REACHED_EXCEPTION_CODE,
                null, MAX_INSCRIPTIONS_REACHED_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(DuplicateInscriptionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleDuplicateInscriptionException(DuplicateInscriptionException exception, Locale locale) {
        String errorMessage = messageSource.getMessage(DUPLICATE_INSCRIPTION_EXCEPTION_CODE,
                new Object[]{exception.getInscriptionId()},
                DUPLICATE_INSCRIPTION_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(AlreadyScoredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleAlreadyScoredException(AlreadyScoredException exception, Locale locale) {
        String errorMessage = messageSource.getMessage(ALREADY_SCORED_EXCEPTION_CODE,
                null, ALREADY_SCORED_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(InstanceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleInstanceNotFoundException(InstanceNotFoundException exception, Locale locale) {
        String errorMessage = messageSource.getMessage(INSTANCE_NOT_FOUND_EXCEPTION_CODE,
                new Object[]{messageSource.getMessage(exception.getName(), null, locale) , exception.getKey()},
                INSTANCE_NOT_FOUND_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(DifferentUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleDifferentUserException(DifferentUserException exception, Locale locale) {
        String errorMessage = messageSource.getMessage(DIFFERENT_USER_EXCEPTION_CODE,
                null, DIFFERENT_USER_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(IncorrectTrialException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleIncorrectTrialException(IncorrectTrialException exception, Locale locale) {
        String errorMessage = messageSource.getMessage(INCORRECT_TRIAL_EXCEPTION_CODE,
                new Object[]{exception.getInscriptionTrialId(), exception.getTrialId()},
                INCORRECT_TRIAL_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);
    }

    @PostMapping("/{trialId}/giveNumber")
    @ResponseStatus(HttpStatus.OK)
    public int giveNumber(@Validated @RequestBody GiveNumberParamsDto params, @PathVariable Long trialId)
            throws NumberAlreadyDeliveredException, InvalidCreditCardException,
            InstanceNotFoundException, DateExpiredException, IncorrectTrialException {

        return inscriptionService.giveNumber(params.getInscriptionId(), trialId, params.getCardNumber());
    }

    @PostMapping("/{trialId}/inscribe")
    @ResponseStatus(HttpStatus.OK)
    public InscriptionSimpleDto inscribeUser(
            @RequestAttribute Long userId,
            @PathVariable Long trialId,
            @Validated @RequestBody InscribeParamsDto cardNumber)
            throws MaxInscriptionsReachedException, DateExpiredException,
            DuplicateInscriptionException, InstanceNotFoundException {

        return toInscriptionSimpleDto(inscriptionService.inscribeUser(userId, trialId, cardNumber.getCardNumber()));
    }

    @GetMapping("/inscriptionHistory")
    @ResponseStatus(HttpStatus.OK)
    public BlockDto<InscriptionDto> findInscriptionsByUser(
            @RequestAttribute Long userId,
            @RequestParam(defaultValue = "0") int page) {

        int size = 2;

        Block<Inscription> inscriptionBlock = inscriptionService.findInscriptionsByUser(userId, page, size);

        return new BlockDto<>(toInscriptionDtos(inscriptionBlock.getItems()), inscriptionBlock.getExistMoreItems());
    }

    @PostMapping("/{inscriptionId}/rateTrial")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rateTrial(
            @PathVariable Long inscriptionId,
            @RequestAttribute Long userId,
            @Validated @RequestBody ScoreParamsDto params)
            throws InstanceNotFoundException, DateExpiredException, AlreadyScoredException, DifferentUserException {

        inscriptionService.rateTrial(inscriptionId, userId, params.getScore());
    }
}
