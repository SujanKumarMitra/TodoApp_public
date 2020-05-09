package com.herokuapp.skmtodoapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "todos")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "heading")
	private String heading;

	@Column(name = "detail", columnDefinition = "text")
	private String detail;

	@Column(name = "deadline", columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deadLine;

	@Column(name = "completed", columnDefinition = "boolean")
	private boolean completed;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	public Todo() {
		super();
	}

	public Todo(String detail, Date deadLine) {
		super();
		this.detail = detail;
		this.deadLine = deadLine;
	}

	public Todo(Integer id, String detail, Date deadLine) {
		super();
		this.id = id;
		this.detail = detail;
		this.deadLine = deadLine;
	}

	public Todo(Integer id, String detail, Date deadLine, Boolean completed, User user) {
		super();
		this.id = id;
		this.detail = detail;
		this.deadLine = deadLine;
		this.completed = completed;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", detail=" + detail + ", deadLine=" + deadLine + ", completed=" + completed + "]";
	}

}
