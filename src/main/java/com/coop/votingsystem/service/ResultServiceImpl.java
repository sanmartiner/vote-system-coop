package com.coop.votingsystem.service;

import com.coop.votingsystem.dto.response.ResultResponseDTO;
import com.coop.votingsystem.model.entitiy.Vote;
import com.coop.votingsystem.model.interfaces.ResultService;
import com.coop.votingsystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    private final VoteRepository voteRepository;

    @Autowired
    public ResultServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }
    @Override
    public ResultResponseDTO getResultBySession(Long sessionId) {
        List<Vote> allVotes = voteRepository.findAllBySessionId(sessionId);

        long totalVotes = allVotes.size();
        long votesYes = allVotes.stream().filter(vote -> "Yes".equalsIgnoreCase(vote.getValue())).count();
        long votesNo = allVotes.stream().filter(vote -> "No".equalsIgnoreCase(vote.getValue())).count();

        return ResultResponseDTO.builder()
                .topicId(sessionId)
                .totalVote(totalVotes)
                .votesYes(votesYes)
                .votesNo(votesNo)
                .build();
    }
}
