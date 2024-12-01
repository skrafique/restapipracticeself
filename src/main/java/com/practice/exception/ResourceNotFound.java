package com.practice.exception;

public class ResourceNotFound extends RuntimeException
{
	public ResourceNotFound(String msg)
	{
		super(msg);
	}
}
