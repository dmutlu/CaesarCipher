package edu.umbc.dmutlu1.caesarcipher;

public class Message
{
    private final String userMessage;
    private final String cipherMessage;
    private final int key;

    public Message(String userMessage, String cipherMessage, int key)
    {
        this.userMessage = userMessage;
        this.cipherMessage = cipherMessage;
        this.key = key;
    }

    public String getUserMessage()
    {
        return userMessage;
    }

    public String getCipherMessage()
    {
        return cipherMessage;
    }

    public int getKey()
    {
        return key;
    }
}