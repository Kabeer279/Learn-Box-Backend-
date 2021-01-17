package com.project.LearnBox.Model;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String text;
	private Instant createddate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classId", referencedColumnName = "id")
	private ClassRoom classroom;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
}
