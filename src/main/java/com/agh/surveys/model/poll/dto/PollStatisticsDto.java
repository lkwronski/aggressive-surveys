package com.agh.surveys.model.poll.dto;


import com.agh.surveys.model.user.dto.UserStatisticsDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PollStatisticsDto {

    List<UserStatisticsDto>  respondedUser;
    List<UserStatisticsDto> notRespondedUser;

    public PollStatisticsDto(List<UserStatisticsDto> respondedUser, List<UserStatisticsDto> notRespondedUser) {
        this.respondedUser = respondedUser;
        this.notRespondedUser = notRespondedUser;
    }
}
