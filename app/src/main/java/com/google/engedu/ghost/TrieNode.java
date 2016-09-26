package com.google.engedu.ghost;

import java.util.HashMap;


public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public void add(String s) {
        HashMap<Character, TrieNode> temp = this.children;
        for(int i=0;i<s.length();i++){
            char a = s.charAt(i);
            TrieNode trieNode;
            if(temp.containsKey(a)){
                trieNode = temp.get(a);
            }
            else{
                trieNode =new TrieNode();
                temp.put(a,trieNode);
            }
            temp = trieNode.children;
            if(i==s.length()-1){
                trieNode.isWord = true;
            }
        }
    }

    // This will always return child of last character of the passed string. We will get to know if any word with this prefix exists
    private TrieNode searchNode(String s){

        HashMap<Character, TrieNode> temp =this.children;
        TrieNode trieNode = null;
        for(int i=0;i<s.length();i++){
            char a = s.charAt(i);
            if(temp.containsKey(a)){
                trieNode = temp.get(a);
                temp = trieNode.children;
            }else{
                return null;
            }
        }
        return trieNode;
    }

    public boolean isWord(String s) {

        TrieNode trieNode=searchNode(s);

        if(trieNode!=null && trieNode.isWord)
            return true;
        else
            return false;

    }

    public String getAnyWordStartingWith(String s) {

        TrieNode trieNode = searchNode(s);
        String result= s;
        HashMap<Character, TrieNode> temp ;
        if(trieNode ==null)
            return null;
        else{
            while (!trieNode.isWord){
                temp = trieNode.children;
                //keySet.toArray will convert set of keys into array
                Character next= (Character)temp.keySet().toArray()[0];
                result += next;
                trieNode = temp.get(next);
            }
        }
        return result;
    }

    // Good Word: just try that the game continues... and we don't lose
    public String getGoodWordStartingWith(String s) {

        return null;
    }
}
