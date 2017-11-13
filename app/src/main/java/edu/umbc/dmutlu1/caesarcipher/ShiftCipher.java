package edu.umbc.dmutlu1.caesarcipher;

public class ShiftCipher
{
    public String cipher(String msg, int shift)
    {
        int key = shift % 26 + 26; //Sets key according to shift.

        //Implemented StringBuilder because it is faster and more efficient.
        StringBuilder encryptedMsg = new StringBuilder();

        //Splits up message into Char array and loops through each Char.
        for (char i : msg.toCharArray())
        {
            //Only changes Char if it is a letter.
            if (Character.isLetter(i))
            {
                if (Character.isUpperCase(i))
                {
                    encryptedMsg.append((char) (((i - 65 + key) % 26) + 65)); //65 is A in ASCII
                } else
                {
                    encryptedMsg.append((char) (((i - 97 + key) % 26) + 97));
                } //97 is a in ASCII

            } else
                {
                    encryptedMsg.append(i);
                }
        }
        return encryptedMsg.toString();
    }

    public String decipher(String msg, int key)
    {
        return cipher(msg, 26 - key);
    }
}