package com.google.engedu.ghost;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private boolean isWord;
    private String actualWord;

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
                trieNode.actualWord=s;
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

    private void getAllChildren(List<String > goodWords){

        Map<Character,TrieNode> nodes =this.children;
        Collection<TrieNode> sets=nodes.values();
        for(TrieNode trieNode : sets){
            if(trieNode.isWord){
                goodWords.add(trieNode.actualWord);
            }
            trieNode.getAllChildren(goodWords);
        }
    }


    // Good Word: just try that the game continues... and we don't lose
    public String getGoodWordStartingWith(String s) {

        List<String> goodWords =new ArrayList<>();
        TrieNode curr=searchNode(s);
        if(curr!=null){

            if(curr.isWord){
                goodWords.add(s);
            }

            curr.getAllChildren(goodWords);

        }else{
            return null;
        }

        Collections.sort(goodWords, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                if (s.length()!=t1.length()) {
                    return s.length()-t1.length(); //overflow impossible since lengths are non-negative
                }
                return s.compareTo(t1);
            }
        });

        String result=goodWords.get(goodWords.size()-1);
        int len=s.length(),size =result.length();
        int i=2;

        while(!((len % 2 == 0 && size%2 ==0)||(len%2!=0 && size%2!=0))&&(i<=goodWords.size())){

            Log.d("result",result);
            result = goodWords.get(goodWords.size()-i);
            i++;
            size=result.length();
        }

        if(i>goodWords.size()){
            result=goodWords.get(goodWords.size()-1);
        }
        return result;
    }
}
