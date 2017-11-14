package edu.umbc.dmutlu1.caesarcipher;

public class Message
{
    private final String userMessage;
    private final String cipherMessage;

    public Message(String userMessage, String cipherMessage)
    {
        this.userMessage = userMessage;
        this.cipherMessage = cipherMessage;
    }

    public String getUserMessage()
    {
        return userMessage;
    }

    public String getCipherMessage()
    {
        return cipherMessage;
    }
}