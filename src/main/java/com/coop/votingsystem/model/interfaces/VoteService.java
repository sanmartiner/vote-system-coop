package com.coop.votingsystem.model.interfaces;

import com.coop.votingsystem.dto.response.VoteResponseDTO;

public interface VoteService {

    VoteResponseDTO register (Long topicsId, Long associateID, String vote);
}
