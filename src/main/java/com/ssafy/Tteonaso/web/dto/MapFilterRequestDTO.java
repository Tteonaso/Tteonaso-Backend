package com.ssafy.Tteonaso.web.dto;

import lombok.Getter;
import lombok.Setter;

public class MapFilterRequestDTO {
    @Getter
    @Setter
    public static class SidoDTO {
        String sidoName;
    }

    @Getter
    @Setter
    public static class GugunDTO {
        String gugunName;
    }
}
