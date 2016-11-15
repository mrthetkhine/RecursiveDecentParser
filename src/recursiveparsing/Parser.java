/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursiveparsing;

/**
 *
 *  <sentence> ::= <subject> <predicate> 
<subject> ::= <article> <noun> 
<predicate> ::= <verb> <direct-object> 
<direct-object> ::= <article> <noun> 
<article> ::= THE | A 
<noun> ::= MAN | DOG 
<verb> ::= BITES | PETS
 */
public class Parser {
    String inputSentence;
    String[]tokens ;
    int currentTokenIndex=0;
    
    public Parser(String input)
    {
        this.inputSentence = input;
        this.tokens = this.inputSentence.split(" ");
        
        for (int i=0; i< this.tokens.length;i++) {
            this.tokens[i] = this.tokens[i].trim().toUpperCase();
        }
        
    }
    public void parse()
    {
        try
        {
            sentence();
            if(currentTokenIndex == tokens.length)
            {
                System.out.println("Grammar Correct");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error "+ e.getMessage());
        }
        
        
    }
    //<sentence> ::= <subject> <predicate> 
    public void sentence()
    {
        subject();
        predicate();
    }
    //<subject> ::= <article> <noun> 
    public void subject()
    {
        article();
        noun();
    }
    //<predicate> ::= <verb> <direct-object> 
    public void predicate()
    {
        verb();
        directObject();
    }
    //<article> ::= THE | A 
    public void article()
    {
        String currentToken = this.tokens[currentTokenIndex];
        if(currentToken.equals("THE")||
           currentToken.equals("A")     )
        {
            currentTokenIndex++;
        }
        else
        {
            error("Excepting <article> THE or A but found "+ currentToken);
        }
    }
    //<noun> ::= MAN | DOG 
    public void noun()
    {
        String currentToken = this.tokens[currentTokenIndex];
        if(currentToken.equals("MAN")||
           currentToken.equals("DOG")     )
        {
            currentTokenIndex++;
        }
        else
        {
            error("Excepting <noun> MAN or DOG but found "+ currentToken);
        }
    }
    //<verb> ::= BITES | PETS
    public void verb()
    {
        String currentToken = this.tokens[currentTokenIndex];
        if(currentToken.equals("BITES")||
           currentToken.equals("PETS")     )
        {
            currentTokenIndex++;
        }
        else
        {
            error("Excepting <verb> BITES or PETS but found "+ currentToken);
        }
    }
    //<direct-object> ::= <article> <noun> 
    public void directObject()
    {
        article();
        noun();
                
    }
    public void error(String message)
    {
        throw new Error(message);
    }
    public static void main(String[] args) {
        Parser parser = new Parser("A DOG BITES THE MAN");
        parser.parse();
    }
}
