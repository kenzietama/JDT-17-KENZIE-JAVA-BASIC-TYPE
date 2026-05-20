package com.indivaragroup.movie.dto;

import java.util.Date;

public class PlayListDto {
    String releaseCode;
    Date releaseDate;

    public void setReleaseCode(String releaseCode) {
        this.releaseCode = releaseCode;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseCode() {
        return releaseCode;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
}
