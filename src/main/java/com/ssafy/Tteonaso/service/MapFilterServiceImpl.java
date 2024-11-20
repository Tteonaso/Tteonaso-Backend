package com.ssafy.Tteonaso.service;

import com.ssafy.Tteonaso.domain.Address;
import com.ssafy.Tteonaso.repository.MapFilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapFilterServiceImpl implements MapFilterService {
    private final MapFilterRepository mapFilterRepository;

    @Override
    public List<String> getGugunNamesBySido(String sidoName) {
        return mapFilterRepository.findDistinctBySidoName(sidoName)
                .stream()
                .map(Address::getGugunName)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDongNamesByGugun(String gugunName) {
        return mapFilterRepository.findByGugunName(gugunName)
                .stream()
                .map(Address::getDongName)
                .distinct()
                .collect(Collectors.toList());
    }
}
