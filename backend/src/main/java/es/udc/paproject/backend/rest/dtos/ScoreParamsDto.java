package es.udc.paproject.backend.rest.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ScoreParamsDto {
    private int score;

    public ScoreParamsDto(){}

    @NotNull
    @Min(1)
    @Max(5)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
