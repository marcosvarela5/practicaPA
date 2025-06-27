package es.udc.paproject.backend.model.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
public class Inscription {
    private Long id;                    // Id de inscripcion
    private User user;                  // Usuario
    private Trial trial;                // Prueba
    private int number;                 // Numero de dorsal
    private String cardNumber;          // Numero de tarjeta bancaria
    private boolean numberDelivered;    // Dorsal entregado?
    private int score;                  // Puntuacion
    private boolean scored;             // Tiene puntuacion?
    private LocalDateTime dateTime;     // Fecha y hora

    public Inscription(){
    }

    public Inscription(User user, Trial trial, int number, String cardNumber) {
        this(user, trial, number, cardNumber, false, 0,
                false, LocalDateTime.now());
    }

    public Inscription(User user, Trial trial, int number, String cardNumber,
                       boolean numberDelivered, int score, boolean scored, LocalDateTime dateTime) {
        this.user = user;
        this.trial = trial;
        this.number = number;
        this.cardNumber = cardNumber;
        this.numberDelivered = numberDelivered;
        this.score = score;
        this.scored = scored;
        this.dateTime = dateTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public boolean getShowRateTrial(){
        return ((!scored) && (trial.getDateTime().toEpochSecond(ZoneOffset.ofHours(1)) < LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(1)) &&
                (LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(1)) - trial.getDateTime().toEpochSecond(ZoneOffset.ofHours(1)) < 86400*15 )));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "trialId")
    public Trial getTrial() {
        return trial;
    }

    public void setTrial(Trial trial) {
        this.trial = trial;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isNumberDelivered() {
        return numberDelivered;
    }

    public void setNumberDelivered(boolean numberDelivered) {
        this.numberDelivered = numberDelivered;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }
}
