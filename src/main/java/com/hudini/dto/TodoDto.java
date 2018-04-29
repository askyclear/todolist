package com.hudini.dto;

public class TodoDto implements Comparable<TodoDto>{
	private long id;
	private String name;
	private String regdate;
	private int sequence;
	private String title;
	private String type;
	
	public TodoDto(long id, String name, String regdate, int sequence, String title, String type) {
		super();
		this.id = id;
		this.name = name;
		this.regdate = regdate;
		this.sequence = sequence;
		this.title = title;
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getRegdate() {
		return regdate;
	}
	public int getSequence() {
		return sequence;
	}
	public String getTitle() {
		return title;
	}
	public String getType() {
		return type;
	}
	@Override
	public int compareTo(TodoDto o) {
		// TODO Auto-generated method stub
		
		return (int)(o.getId() - this.id);
	}
	
	
}
