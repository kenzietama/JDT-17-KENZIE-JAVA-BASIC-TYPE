package com.indivaragroup.movie.logic;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import com.indivaragroup.movie.dto.PlayListDto;

public class PlayLIstEveryYear {
        /*
    1. Disini saya punya list kode JAV dari actor remu suzmori
    2. Masukkan list tersebut ke dalam array list kalau tanggal dibawah 2026 masuk ke list udah gak ke update
    3. List kan kode file nya dideklarasi
        releaseCode : "STAR-4124"
        releaseDate : '2025-05-04; Date
     */
    public void getFavouritePlaylist() {
        ArrayList<PlayListDto> listCode = new ArrayList<>();

        for (int i = 0; i <= 20; i++) {
            PlayListDto playListDTO = new PlayListDto();
            playListDTO.setReleaseCode("REL-" + i);

            LocalDate start = LocalDate.of(2024, 1, 1);
            LocalDate end = LocalDate.now();
            long daysBetween = ChronoUnit.DAYS.between(start, end);
            long randomDays = ThreadLocalRandom.current().nextLong(daysBetween + 1);
            // 1. Generate the random date using modern java.time
            LocalDate randomLocalDate = start.plusDays(randomDays);
            // 2. Convert it into a legacy java.util.Date data type
            Date randomDate = Date.from(randomLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            playListDTO.setReleaseDate(randomDate);
            listCode.add(playListDTO);
        }

        ArrayList<PlayListDto> filteredList = new ArrayList<>();

        for (int j = 0 ; j < listCode.size(); j++) {
            PlayListDto playListDTO = listCode.get(j);

            int year = playListDTO.getReleaseDate().toInstant().atZone(ZoneId.systemDefault()).getYear();
            if (year == 2026) {
                filteredList.add(playListDTO);
            }
        }

        System.out.println("INI PLAYLIST FAVORIT GUE: ");
        for (int k = 0; k < filteredList.size(); k++) {
            PlayListDto dto = filteredList.get(k);
            System.out.println(dto.getReleaseCode() + " - " + dto.getReleaseDate());
        }
    }
}
