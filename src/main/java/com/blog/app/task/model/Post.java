package com.blog.app.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts", schema = "")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "dna_generate")
	private Integer id;
	@Column(name = "views")
	private Integer views;
	@Column(name = "title")
	private String title;
	@Column(name = "body")
	private String body;
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;


}
