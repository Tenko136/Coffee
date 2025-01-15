package kz.tenko.machine.coffee.service;

import kz.tenko.machine.coffee.dto.PublicHolidayDTO;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class OperationalService {

    public static final String URL = "https://date.nager.at/api/v3/PublicHolidays/2025/KZ";

    private RestTemplate restTemplate;
    private final Set<LocalDate> holidays = new HashSet<>();

    public boolean isWorkingHours() {

        return isCoffeeMachineOperational();

    }

    public boolean isCoffeeMachineOperational() {

        LocalDateTime now = LocalDateTime.now();
        LocalTime currentTime = now.toLocalTime();
        LocalDate currentDate = now.toLocalDate();

        return !currentTime.isBefore(LocalTime.of(8, 0))
                && !currentTime.isAfter(LocalTime.of(17, 0))
                && !isWeekend(currentDate)
                && !isHoliday(currentDate);
    }

    private boolean isWeekend(LocalDate date) {

        return date.getDayOfWeek().getValue() >= 6;

    }

    private boolean isHoliday(LocalDate date) {

        if (holidays.isEmpty()) {
            fetchHolidays();
        }
        return holidays.contains(date);

    }

    @Cacheable("holidays")
    public void fetchHolidays() {

        PublicHolidayDTO[] publicHolidays = restTemplate.getForObject(URL, PublicHolidayDTO[].class);
        if (publicHolidays != null) {
            Arrays.stream(publicHolidays).map(PublicHolidayDTO::getDate).forEach(holidays::add);
        }

    }
}
