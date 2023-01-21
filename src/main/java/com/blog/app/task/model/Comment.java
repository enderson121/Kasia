package com.blog.app.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * TODO:
 * - Add the proper Spring annotations to the entity and its fields
 * - Include relations between entities
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments", schema = "")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "dna_generate")
	private Integer id;
	@Column(name = "body")
	private String body;
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private User author;

}
