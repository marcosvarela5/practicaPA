package es.udc.paproject.backend.model.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.ZoneOffset;


@Entity
public class Trial{

    private Long id;                        // Id
    private String trialName;               // Nombre de la prueba
    private String trialDescription;        // Descripcion de la prueba
    private LocalDateTime dateTime;         // Hora de la prueba
    private BigDecimal inscriptionPrice;    // Precio de inscripcion
    private int maxParticipants;            // Participantes maximos
    private int numParticipants;            // Participantes actuales
    private String place;                   // Localidad
    private TrialType trialType;            // Tipo de prueba
    private Province province;              // Provincia
    private int sumScores;                  // sumScores / numScores = avgScore
    private int numScores;

    private long version;


    public Trial() {

    }

    public Trial(String trialName, String trialDescription, LocalDateTime dateTime, BigDecimal inscriptionPrice,
                  int maxParticipants, String place, TrialType trialType, Province province, int numScores, int sumScores) {
        this.trialName = trialName;
        this.trialDescription = trialDescription;
        this.dateTime = dateTime;
        this.inscriptionPrice = inscriptionPrice;
        this.maxParticipants = maxParticipants;
        this.place = place;
        this.trialType = trialType;
        this.province = province;
        this.sumScores = sumScores;
        this.numScores = numScores;
    }

    @Transient
    public boolean getShowRegister(){
        return (dateTime.toEpochSecond(ZoneOffset.ofHours(1)) - LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(1))) > 86400 && (numParticipants < maxParticipants);
    }

    @Transient
    public boolean getShowGiveNumber(){
        return (dateTime.isAfter(LocalDateTime.now()));
    }

    @Transient
    public float getAvgScore(){
        return numScores == 0 ? 0 : (float) sumScores /numScores;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public String getTrialName() {
        return this.trialName;
    }

    public void setTrialName(final String trialName) {
        this.trialName = trialName;
    }

    public String getTrialDescription() {
        return this.trialDescription;
    }

    public void setTrialDescription(final String trialDescription) {
        this.trialDescription = trialDescription;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getInscriptionPrice() {
        return this.inscriptionPrice;
    }

    public void setInscriptionPrice(final BigDecimal inscriptionPrice) {
        this.inscriptionPrice = inscriptionPrice;
    }

    public int getMaxParticipants() {
        return this.maxParticipants;
    }

    public void setMaxParticipants(final int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public int getNumParticipants() {
        return this.numParticipants;
    }

    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(final String place) {
        this.place = place;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "trialTypeId")
    public TrialType getTrialType() {
        return this.trialType;
    }

    public void setTrialType(final TrialType trialType) {
        this.trialType = trialType;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "ProvinceId")
    public Province getProvince() {
        return this.province;
    }

    public void setProvince(final Province province) {
        this.province = province;
    }

    public int getSumScores() {
        return sumScores;
    }

    public void setSumScores(int sumScores) {
        this.sumScores = sumScores;
    }

    public int getNumScores() {
        return numScores;
    }

    public void setNumScores(int numScores) {
        this.numScores = numScores;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}