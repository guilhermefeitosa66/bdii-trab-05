package task;

import java.io.Serializable;

public class Request implements Serializable
{
    public String operation; /* valores: create, update, destroy */
    public Task task;
    
    public Request(String operation, Task task)
    {
        this.operation = operation;
        this.task = task;
    }
}
