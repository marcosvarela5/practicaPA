package es.udc.paproject.backend.model.exceptions;

public class DifferentUserException extends Exception{
    private final Long expectedUserId;
    private final Long receivedUserId;

    public DifferentUserException(Long expectedUserId, Long receivedUserId){
        this.expectedUserId = expectedUserId;
        this.receivedUserId = receivedUserId;
    }

    public Long getExpectedUserId() {return expectedUserId;}

    public Long getReceivedUserId() {return receivedUserId;}
}
