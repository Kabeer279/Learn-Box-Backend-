package com.project.LearnBox.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	
	private long id;
	private String text;
	private Instant createddate;
	private Long classId;
	private String username;
}
