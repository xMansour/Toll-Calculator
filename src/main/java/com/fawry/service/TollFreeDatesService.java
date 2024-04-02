package com.fawry.service;

import java.time.LocalDateTime;

public interface TollFreeDatesService {

    boolean isTollFreeDate(LocalDateTime date);

}