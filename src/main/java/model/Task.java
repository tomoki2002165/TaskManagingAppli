package model;

import java.io.Serializable;

public class Task implements Serializable {
	
	private int id;
	private String text;
	private boolean isCleared = false;
	
	public Task (String text)
	{
		this.text = text;
	}
	
	public Task (int id , String text , boolean isCleared)
	{
		this.id = id;
		this.text = text;
		this.isCleared = isCleared;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getText()
	{
		return text;
	}
	
	public boolean getCleared()
	{
		return isCleared;
	}
	
	public void setText (String text)
	{
		this.text = text;
	}
	
	public void setCleared (boolean isCleared)
	{
		this.isCleared = isCleared;
	}
}