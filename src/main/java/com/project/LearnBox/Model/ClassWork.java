package com.project.LearnBox.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ClassWork {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String docName;
	private String docType;
	private Long classId;
	
	@Lob
	private byte[] data;
	
	
	public ClassWork(String docName, String docType,Long classId, byte[] data) {
		super();
		
		this.docName = docName;
		this.docType = docType;
		this.classId=classId;
		this.data = data;
	}
}
