package service;

import java.time.LocalDateTime;

public class DateServiceImpl implements DateService {
    @Override
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
