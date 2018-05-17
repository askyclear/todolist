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
		// 나중에 등록한 것의 id 값이 더 클것이기 때문에 id 값 이 크면 나중에 등록한 것이 아래로 감
		
		return (id > o.getId()) ? 1 : -1;
	}
	
}
