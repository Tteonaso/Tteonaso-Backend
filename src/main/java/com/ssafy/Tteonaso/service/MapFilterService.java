package com.ssafy.Tteonaso.service;

import java.util.List;

public interface MapFilterService {
    List<String> getGugunNamesBySido(String sidoName);
    List<String> getDongNamesByGugun(String gugunName);
}
