package com.coop.votingsystem.model.interfaces;

import com.coop.votingsystem.dto.response.ResultResponseDTO;

public interface ResultService {
    public ResultResponseDTO getResultBySession(Long sessionId);
}
