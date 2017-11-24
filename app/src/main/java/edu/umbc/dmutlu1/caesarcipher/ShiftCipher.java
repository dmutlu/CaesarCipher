package edu.umbc.dmutlu1.caesarcipher;

/**
 * A simple shift cipher that ciphers text based off a given shift
 */

public class ShiftCipher
{
    public String cipher(String msg, int shift)
    {
        int key = shift % 95 + 95; //Sets key according to shift.

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
                    encryptedMsg.append((char) (((i - 65 + key) % 95) + 65)); //65 is A in ASCII
                } else
                {
                    encryptedMsg.append((char) (((i - 97 + key) % 95) + 97));
                } //97 is a in ASCII
            }
            else
            {
                encryptedMsg.append((char) (((i - 33 + key) % 95) + 33));
            }
        }
        return encryptedMsg.toString();
    }

    public String decipher(String msg, int key)
    {
        return cipher(msg, 95 - key);
    }
}